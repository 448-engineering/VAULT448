import 'dart:ffi' as dart_ffi;
import 'dart:io';
import 'bridge_generated.dart';
import 'dart:ffi';

const libray_name = 'libvault448_native';
final path = Platform.isWindows ? '$libray_name.dll' : '$libray_name.so';
late final dylib = Platform.isIOS
    ? DynamicLibrary.process()
    : Platform.isMacOS
        ? DynamicLibrary.executable()
        : DynamicLibrary.open(path);
late final api = Vault448NativeImpl(dylib);
