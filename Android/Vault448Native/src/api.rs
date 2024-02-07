#[uniffi::export]
pub fn ffi_version() -> String {
    crate::PKG_VERSION.to_string()
}

#[uniffi::export]
pub async fn receiver() {}
