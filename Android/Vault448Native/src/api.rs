use crate::{App, CurrentDirMetadata};
use core::fmt;
use once_cell::sync::OnceCell;
use vault448_common::VaultError;

pub(crate) static APP: OnceCell<App> = OnceCell::new();

#[uniffi::export]
pub fn ffi_version() -> String {
    crate::PKG_VERSION.to_string()
}

#[uniffi::export]
pub fn init_app_static(path: String) -> bool {
    if APP
        .get_or_try_init(|| Ok::<App, ()>(App::new(&path)))
        .is_ok()
    {
        true
    } else {
        false
    }
}

#[uniffi::export]
pub fn current_dir_metadata() -> Option<CurrentDirMetadata> {
    APP.get().map(|data| data.user_dir_metadata().clone())
}

#[derive(Debug, uniffi::Error)]
pub enum OutcomeError {
    Failure { failure: String },
}

impl fmt::Display for OutcomeError {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        match self {
            OutcomeError::Failure { failure } => write!(f, "OutcomeError::Failure {}", failure),
        }
    }
}

impl std::error::Error for OutcomeError {}

impl From<VaultError> for OutcomeError {
    fn from(value: VaultError) -> Self {
        OutcomeError::Failure {
            failure: value.to_string(),
        }
    }
}

#[uniffi::export]
pub fn to_human_format(value: u64, decimal_places: u64) -> String {
    use human_format::Formatter;

    Formatter::new()
        .with_decimals(decimal_places as usize)
        .format(value as f64)
}
