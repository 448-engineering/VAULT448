use crate::{fs_ops::InternalStorageDetails, App, DirOutcome, VaultError};
use core::fmt;
use once_cell::sync::OnceCell;

pub(crate) static APP: OnceCell<App> = OnceCell::new();

#[uniffi::export]
pub fn ffi_version() -> String {
    crate::PKG_VERSION.to_string()
}

#[uniffi::export]
pub async fn init(path: String) -> DirOutcome {
    App::init(&path).await
}

pub fn update_details(details: InternalStorageDetails) {
    if let Some(app) = APP.get_mut() {
        app.details = details;
    }
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
