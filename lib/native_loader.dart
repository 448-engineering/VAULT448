import 'dart:io';
import 'bridge_generated.dart';
import 'dart:ffi';

const libray_name = 'libvault448_native';
final path = Platform.isWindows ? '$libray_name.dll' : '$libray_name.so';
final dylib = Platform.isIOS
    ? DynamicLibrary.process()
    : Platform.isMacOS
        ? DynamicLibrary.executable()
        : DynamicLibrary.open(path);
final api = Vault448NativeImpl(dylib);
