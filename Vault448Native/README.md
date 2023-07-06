# Kotlin - Rust interop
This repository contains some sample code for using Rust together with Kotlin.

## Contents
The app runs a simple micro-benchmark between Kotlin and Rust.
It consists of sorting a random string lexicographically.

Both the Kotlin and Rust implementation are using functional-programming,
with Kotlin using [streams][streams] and Rust using [iterators][iter].

[streams]: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.streams/index.html
[iter]: https://doc.rust-lang.org/std/iter/

## Dependencies
You will need:
- a Java runtime (such as Oracle's JRE on Windows or OpenJDK on Linux)
- [Kotlin](https://github.com/JetBrains/kotlin)
- [Rust](https://www.rust-lang.org)
- GNU [Make](https://www.gnu.org/software/make/)

## Building / Running
Just run `make`.

## Results
Rust is consistently 3-5 times faster than the equivalent Kotlin code.

## Support libraries
The [jni-rs](https://github.com/prevoty/jni-rs) crate is used in Rust to access JVM types,
and a simple Kotlin app loads and runs the Rust code.


#### Tutorial

1. Create a library in Rust called `kotlin-rust`
    ```sh
    cargo new kotlin-rust --lib
    ```
2. Edit the `Cargo.toml` file and add the `[lib]` part with the name of the library and the `crate-type` of `cdylib` so that the crate builds as a dynamic library
    ```toml
    [package]
    name = "kotlin-example"
    ... # Pre-existing code here

    [lib]
    name = "rust_kotlin"
    crate-type = ["cdylib"]
    ```
3. Add `jni` Rust crate as a dependency
    ```sh
    cargo add jni
    ```
4. In the `src/lib.rs` file, add the code to add two unsigned integer 32 numbers
    ```rust
    extern crate jni;

    use jni::objects::JClass;
    use jni::sys::jint;
    use jni::JNIEnv;

    fn add(a: u32, b: u32) -> u32 {
        a + b
    }

    #[allow(non_snake_case)]
    #[no_mangle]
    pub extern "system" fn Java_RustLibrary_rustyAdd(
        // Java environment.
        _env: JNIEnv,
        // Static class which owns this method.
        _class: JClass,
        a: jint,
        b: jint,
    ) -> i32 {
        if a < 0 || b < 0 {
            panic!(); // Handle this differently in production
        }

        println!("INTS ARE: {}, {}", &a, &b);

        add(a as u32, b as u32) as i32
    }
    ```
5. Create a directory for the kotlin files called `kotlin` and within it create two kotlin files called `Main.kt` and `Rust.kt` 
6. In the `Rust.kt` file add the code to call the exported Rust function
   ```kt
    /** This file is used as a namespace for all the exported Rust functions. */
    @file:JvmName("RustLibrary")

    external fun rustyAdd(a: Int, b: Int): Int;
    ```
7. In the `Main.kt` call the function to add two numbers of your choice
   ```kt
    fun main() {
        System.loadLibrary("rust_kotlin")

        val a: Int = 4;
        val b: Int = 2;

        val foo = rustyAdd(a, b);

        println("The Rusty Value of Foo is $foo");

    }
   ```
8. Install `cargo-make` to automate building the project
    ```sh
    cargo install cargo-make
    ```
9. Run the project by invoking `cargo make bar_build` in the commandline
    ```sh
    cargo make bar_build
    ```