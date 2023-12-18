use std::{io::ErrorKind, path::StripPrefixError};

pub type VaultResult<T> = Result<T, VaultError>;

#[derive(Debug, thiserror::Error)]
pub enum VaultError {
    #[error("Encountered IO Error - {0}")]
    Io(ErrorKind),
    #[error("Filesystem Error - Invalid Folder")]
    InvalidFolder,
    #[error("Filesystem Error - Invalid File")]
    InvalidFile,
    #[error("Filesystem Error - Invalid FileName")]
    InvalidFileName,
    #[error("Filesystem Error - Invalid Path")]
    InvalidPath,
    #[error("Filesystem Error - {0}")]
    OsString(String),
    #[error("Path Error - {0}")]
    StripPrefix(StripPrefixError),
    #[error("Unable to get RwLock")]
    RwLock,
}

impl From<std::io::Error> for VaultError {
    fn from(value: std::io::Error) -> Self {
        VaultError::Io(value.kind())
    }
}
