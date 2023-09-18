use jni::{
    objects::{JClass, JString},
    sys::jstring,
    JNIEnv,
};

mod calc_files;
mod constants;
pub(crate) use constants::*;

#[allow(non_snake_case)]
#[no_mangle]
pub extern "system" fn Java_e448_productivity_vault448_Vault448Native_RootPath<'local>(
    env: JNIEnv<'local>,
    _java_class: JClass<'local>,
    _input: JString<'local>,
) -> jstring {
    let output = env.new_string("RustyHellow").unwrap();

    output.into_raw()
}

#[allow(non_snake_case)]
#[no_mangle]
pub extern "system" fn Java_e448_productivity_vault448_Vault448Native_ffiVersion<'local>(
    env: JNIEnv<'local>,
    _java_class: JClass<'local>,
) -> jstring {
    let output = env.new_string(PKG_VERSION).unwrap();

    output.into_raw()
}