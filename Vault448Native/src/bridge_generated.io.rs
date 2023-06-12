use super::*;
// Section: wire functions

#[no_mangle]
pub extern "C" fn wire_ffi_info(port_: i64) {
    wire_ffi_info_impl(port_)
}

#[no_mangle]
pub extern "C" fn wire_app_version(port_: i64) {
    wire_app_version_impl(port_)
}

#[no_mangle]
pub extern "C" fn wire_num_format(port_: i64, value: u64) {
    wire_num_format_impl(port_, value)
}

// Section: allocate functions

// Section: related functions

// Section: impl Wire2Api

// Section: wire structs

// Section: impl NewWithNullPtr

pub trait NewWithNullPtr {
    fn new_with_null_ptr() -> Self;
}

impl<T> NewWithNullPtr for *mut T {
    fn new_with_null_ptr() -> Self {
        std::ptr::null_mut()
    }
}

// Section: sync execution mode utility

#[no_mangle]
pub extern "C" fn free_WireSyncReturn(ptr: support::WireSyncReturn) {
    unsafe {
        let _ = support::box_from_leak_ptr(ptr);
    };
}
