#[uniffi::export]
pub fn ffi_version() -> String {
    crate::PKG_VERSION.to_string()
}

#[uniffi::export]
pub async fn exec_timer(delay: u64) -> String {
    use smol::Timer;
    use std::time::Duration;

    let period = Duration::from_secs(delay);
    Timer::after(period).await;

    "Successfully run async function delay".to_owned()
}
