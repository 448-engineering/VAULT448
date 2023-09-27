use easy_jni::jni::{
    objects::{JClass, JString},
    sys::{jlong, jstring},
    JNIEnv,
};

mod constants;
mod fs_reader;
pub(crate) use constants::*;
mod errors;
pub(crate) use errors::*;
use fs_reader::FsUtils;

pub fn result_with_string(value: &str) -> jobject {}

#[allow(non_snake_case)]
#[no_mangle]
pub extern "system" fn Java_e448_productivity_vault448_Vault448Native_ffiVersion<'local>(
    env: JNIEnv<'local>,
    _java_class: JClass<'local>,
) -> jstring {
    let output = env.new_string(PKG_VERSION).unwrap();

    output.into_raw()
}
