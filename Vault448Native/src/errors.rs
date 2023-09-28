use fs_extra::error::ErrorKind as FsErrorkind;
use std::io::ErrorKind;

pub type VaultResult<T> = Result<T, VaultError>;

pub enum VaultError {
    Io(ErrorKind),
    Dir(FsErrorkind),
}

impl From<fs_extra::error::Error> for VaultError {
    fn from(value: fs_extra::error::Error) -> Self {
        VaultError::Dir(value.kind)
    }
}

impl From<std::io::Error> for VaultError {
    fn from(value: std::io::Error) -> Self {
        VaultError::Io(value.kind())
    }
}
