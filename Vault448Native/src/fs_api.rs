#[derive(Debug, uniffi::Record)]
pub struct ApiCurrentDirMetadata {
    directories: Vec<FfiFsMetadata>,
    files: Vec<FfiFsFile>,
    total_dirs: String,
    total_files: String,
    path: String,
    errors: Vec<String>,
}

#[derive(Debug, uniffi::Record)]
pub struct FfiFsMetadata {
    size: String,
    size_string: String,
    name: String,
    created: String,
    modified: String,
    accessed: String,
    symlink: bool,
    path: String,
    readonly: bool,
    dir: bool,
}

#[derive(Debug, uniffi::Record)]
pub struct FfiFsFile {
    metadata: FfiFsMetadata,
    media: String,
    stem: String,
    extension: String,
    category: ApiFormatKind,
}

#[derive(Debug, uniffi::Record)]
pub struct ApiFormatKind {
    name: String,
    extension: String,
    media_type: String,
    short_name: String,
}
