use crate::errors::VaultResult;
use blocking::unblock;
use camino::{Utf8Path, Utf8PathBuf};
use file_format::{FileFormat, Kind as FormatKind};
use fs_extra::dir::{get_dir_content2, DirOptions};
use smol::fs::metadata;
use tai64::Tai64N;

pub struct FsUtils;

impl FsUtils {
    pub async fn calc_size(path: Utf8PathBuf) -> VaultResult<usize> {
        let size = unblock(move || fs_extra::dir::get_size(path)).await;
        let size = size? as usize;

        Ok(size)
    }
}

#[derive(Debug, PartialEq, Eq, Clone, Default)]
pub struct CurrentDirMetadata {
    directories: Vec<FsMetadata>,
    files: Vec<FsFile>,
    total_dirs: usize,
    total_files: usize,
    path: Utf8PathBuf,
}

impl CurrentDirMetadata {
    pub fn new(root_dir: &str) -> Self {
        CurrentDirMetadata {
            path: Utf8Path::new(root_dir).to_path_buf(),
            ..Default::default()
        }
    }

    pub async fn read_dir(&mut self, dir: &Utf8Path) -> VaultResult<&mut Self> {
        let cloned_path = self.path.clone();

        let content =
            unblock(move || get_dir_content2(cloned_path, &DirOptions { depth: 0 })).await;
        let content = content?;

        for directory in content.directories {
            let path = Utf8Path::new(&directory);
            let metadata = metadata(path).await?;
            let name = match path.file_name() {
                Some(file_name) => file_name.to_owned(),
                None => self.path.to_string(),
            };
            let name = name.to_owned();
            let size = FsUtils::calc_size(path.to_path_buf()).await?;

            let built_metadata = FsMetadata {
                size: size as usize,
                size_string: byte_prefix::calc_bytes(size as f32),
                name,
                created: Tai64N::from_system_time(&metadata.created()?),
                modified: Tai64N::from_system_time(&metadata.modified()?),
                accessed: Tai64N::from_system_time(&metadata.accessed()?),
                symlink: metadata.is_symlink(),
                path: path.to_path_buf(),
                readonly: metadata.permissions().readonly(),
                dir: metadata.is_dir(),
            };

            self.directories.push(built_metadata);
        }

        self.files
            .extend_from_slice(&self.read_files(&content.files).await?);

        Ok(self)
    }

    pub async fn read_files(&self, files: &[String]) -> VaultResult<Vec<FsFile>> {
        let mut all_files = Vec::<FsFile>::new();
        for file in files {
            let path = Utf8Path::new(&file).to_path_buf();
            let metadata = metadata(&path).await?;
            let stem = match path.file_stem() {
                Some(stem) => stem.to_owned(),
                None => file.to_owned(),
            };
            let name = match path.file_name() {
                Some(file_name) => file_name.to_string(),
                None => file.to_owned(),
            };
            let name = name.to_owned();
            let cloned_path = path.clone();

            let file_format = unblock(move || FileFormat::from_file(cloned_path)).await;
            let file_format = file_format?;

            let size = FsUtils::calc_size(path.to_path_buf()).await?;

            let built_metadata = FsMetadata {
                size: size as usize,
                size_string: byte_prefix::calc_bytes(size as f32),
                name,
                created: Tai64N::from_system_time(&metadata.created()?),
                modified: Tai64N::from_system_time(&metadata.modified()?),
                accessed: Tai64N::from_system_time(&metadata.accessed()?),
                symlink: metadata.is_symlink(),
                path,
                readonly: metadata.permissions().readonly(),
                dir: metadata.is_dir(),
            };

            let build_file_metadata = FsFile {
                metadata: built_metadata,
                media: file_format.media_type().to_string(),
                stem,
                extension: file_format.extension().to_owned(),
                category: file_format.kind(),
            };

            all_files.push(build_file_metadata);
        }

        Ok(all_files)
    }

    pub fn get_directories(&self) -> &[FsMetadata] {
        self.directories.as_ref()
    }

    pub fn get_files(&self) -> &[FsFile] {
        self.files.as_ref()
    }

    pub fn path(&self) -> &Utf8PathBuf {
        &self.path
    }

    pub fn total_dirs(&self) -> usize {
        self.total_dirs
    }

    pub fn total_files(&self) -> usize {
        self.total_files
    }
}

#[derive(Debug, PartialEq, Eq, Clone)]
pub struct FsFile {
    metadata: FsMetadata,
    media: String,
    stem: String,
    extension: String,
    category: FormatKind,
}

impl FsFile {
    pub fn metadata(&self) -> &FsMetadata {
        &self.metadata
    }

    pub fn media(&self) -> &str {
        self.media.as_str()
    }

    pub fn stem(&self) -> &str {
        self.stem.as_str()
    }

    pub fn extension(&self) -> &str {
        self.extension.as_str()
    }

    pub fn category(&self) -> FormatKind {
        self.category
    }
}

#[derive(Debug, PartialEq, Eq, PartialOrd, Ord, Clone)]
pub struct FsMetadata {
    size: usize,
    size_string: String,
    name: String,
    created: Tai64N,
    modified: Tai64N,
    accessed: Tai64N,
    symlink: bool,
    path: Utf8PathBuf,
    readonly: bool,
    dir: bool,
}

impl FsMetadata {
    pub fn size(&self) -> usize {
        self.size
    }

    pub fn size_string(&self) -> &str {
        self.size_string.as_str()
    }

    pub fn name(&self) -> &str {
        self.name.as_str()
    }

    pub fn created(&self) -> &Tai64N {
        &self.created
    }

    pub fn modified(&self) -> &Tai64N {
        &self.modified
    }

    pub fn accessed(&self) -> &Tai64N {
        &self.accessed
    }

    pub fn is_symlink(&self) -> &bool {
        &self.symlink
    }

    pub fn path(&self) -> &Utf8PathBuf {
        &self.path
    }

    pub fn readonly(&self) -> &bool {
        &self.readonly
    }

    pub fn is_dir(&self) -> &bool {
        &self.dir
    }
}
