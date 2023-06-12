use crate::{pretty_version, PKG_VERSION};

pub fn ffi_info() -> String {
    PKG_VERSION.to_string()
}

pub fn app_version() -> String {
    pretty_version().to_string()
}

pub fn num_format(value: u64) -> String {
    if value > 1000 {
        let formatted = human_format::Formatter::new()
            .with_decimals(1)
            .format(value as f64);
        let formatted = formatted.replace(".", " ");
        let collected = formatted.split_whitespace().collect::<Vec<&str>>();

        (collected[0].to_owned() + collected[2] + "+").to_uppercase()
    } else {
        human_format::Formatter::new()
            .with_decimals(0)
            .format(value as f64)
            .to_uppercase()
    }
}
