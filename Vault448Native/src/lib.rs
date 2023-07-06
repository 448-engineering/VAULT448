use jni::{
    objects::{JClass, JString,},
    sys::{jstring},
    JNIEnv,
};

#[allow(non_snake_case)]
#[no_mangle]
pub extern "system" fn Java_engineering448_vault448_Vault448Native_sillyString<'local>(
    env: JNIEnv<'local>,
    _java_class: JClass<'local>,
    _input: JString<'local>,
)-> jstring {
     let output = env.new_string("RustyHellow").unwrap();

     output.into_raw()
}
