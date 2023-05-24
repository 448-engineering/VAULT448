use crate::{pretty_version, PKG_VERSION};

pub fn ffi_info() -> String {
    PKG_VERSION.to_string()
}

pub fn app_version() -> String {
    pretty_version().to_string()
}
