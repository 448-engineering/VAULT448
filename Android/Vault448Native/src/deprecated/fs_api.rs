use dir_meta::{file_format::FileFormat, DateTimeString, DirError, DirMetadata, FileMetadata};

#[derive(Debug, PartialEq, Eq, PartialOrd, Ord, Clone, Default, uniffi::Record)]
pub struct CurrentDirMetadata {
    pub dir_name: String,
    pub dir_path: String,
    pub directories: Vec<String>,
    pub files: Vec<FfiFileMetadata>,
    pub size: String,
    pub errors: Vec<FfiDirError>,
}

#[derive(Debug, PartialEq, Eq, PartialOrd, Ord, Clone, Default, uniffi::Record)]
pub struct FfiFileMetadata {
    pub name: String,
    pub path: String,
    pub size: String,
    pub read_only: bool,
    pub created: Option<FfiDateTimeString>,
    pub accessed: Option<FfiDateTimeString>,
    pub modified: Option<FfiDateTimeString>,
    pub symlink: bool,
    pub file_format: FfiFileFormat,
}

#[derive(Debug, PartialEq, Eq, PartialOrd, Ord, Clone, Default, uniffi::Record)]
pub struct FfiDirError {
    pub path: String,
    pub error: String,
    pub display: String,
}

#[derive(Debug, PartialEq, Eq, PartialOrd, Ord, Clone, Default, uniffi::Record)]
pub struct FfiFileFormat {
    pub extension: String,
    pub name: String,
    pub short_name: Option<String>,
    pub media_type: String,
}

#[derive(Debug, PartialEq, Eq, PartialOrd, Ord, Clone, Default, uniffi::Record)]
pub struct FfiDateTimeString {
    pub date: String,
    pub time: String,
}

pub struct ConversionUtils;

impl ConversionUtils {
    pub fn from_dir_metdata(value: &DirMetadata<'_>) -> CurrentDirMetadata {
        let directories = value
            .directories()
            .iter()
            .map(|directory| directory.to_string_lossy().to_string())
            .collect::<Vec<String>>();

        let files = value
            .files()
            .iter()
            .map(|file_meta| ConversionUtils::from_file_metadata(file_meta))
            .collect::<Vec<FfiFileMetadata>>();

        let errors = value
            .errors()
            .iter()
            .map(|error| ConversionUtils::from_dir_error(error))
            .collect::<Vec<FfiDirError>>();

        CurrentDirMetadata {
            dir_name: value.dir_name().to_string(),
            dir_path: value.dir_path().to_string_lossy().to_string(),
            directories,
            files,
            size: value.size_formatted(),
            errors,
        }
    }

    pub fn from_date_time_string(value: Option<&DateTimeString<'_>>) -> Option<FfiDateTimeString> {
        value.map(|date_time| FfiDateTimeString {
            date: date_time.date.to_string(),
            time: date_time.time.to_string(),
        })
    }

    pub fn from_dir_error(value: &DirError<'_>) -> FfiDirError {
        FfiDirError {
            path: value.path.to_string_lossy().to_string(),
            error: value.error.to_string(),
            display: value.display.to_string(),
        }
    }

    pub fn from_file_format(value: &FileFormat) -> FfiFileFormat {
        let short_name = value.short_name().map(|value| value.to_owned());
        FfiFileFormat {
            extension: value.extension().to_owned(),
            name: value.name().to_owned(),
            short_name,
            media_type: value.media_type().to_owned(),
        }
    }

    pub fn from_file_metadata(value: &FileMetadata<'_>) -> FfiFileMetadata {
        let file_format = ConversionUtils::from_file_format(value.file_format());

        let created = ConversionUtils::from_date_time_string(value.created_24hr().as_ref());
        let accessed = ConversionUtils::from_date_time_string(value.accessed_24hr().as_ref());
        let modified = ConversionUtils::from_date_time_string(value.modified_24hr().as_ref());

        FfiFileMetadata {
            name: value.name().to_string(),
            path: value.path().to_string_lossy().to_string(),
            size: value.formatted_size(),
            read_only: value.read_only(),
            created,
            accessed,
            modified,
            symlink: value.symlink(),
            file_format,
        }
    }
}

#[derive(Debug, Default, uniffi::Record)]
pub struct ApiCurrentDirMetadata {
    directories: Vec<FfiFsMetadata>,
    files: Vec<FfiFsFile>,
    total_dirs: String,
    total_files: String,
    path: String,
    errors: Vec<String>,
}

#[derive(Debug, Default, uniffi::Record)]
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

#[derive(Debug, Default, uniffi::Record)]
pub struct FfiFsFile {
    metadata: FfiFsMetadata,
    media: String,
    stem: String,
    extension: String,
    category: ApiFormatKind,
}

#[derive(Debug, Default, uniffi::Record)]
pub struct ApiFormatKind {
    name: String,
    extension: String,
    media_type: String,
    short_name: String,
}
