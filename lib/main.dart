import 'package:flutter/material.dart';
import 'package:vault448/root_page_screens/root_page.dart';
import 'package:responsive_sizer/responsive_sizer.dart';
import 'package:device_preview/device_preview.dart';

void main() {
  runApp(DevicePreview(
    enabled: false, //!kReleaseMode,
    builder: (context) => const MyApp(), // Wrap your app
  ));
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return ResponsiveSizer(builder: (context, orientation, deviceType) {
      return MaterialApp(
        title: 'VAULT448',
        initialRoute: "/",
        routes: {
          "/": (context) => const RootPage(),
          "/root_page": (context) => const RootPage(),
        },
      );
    });
  }
}


/*
const Breakpoint(start: 0, end: 450, name: MOBILE),
          const Breakpoint(start: 451, end: 800, name: TABLET),
          const Breakpoint(start: 801, end: 1920, name: DESKTOP),
          const Breakpoint(start: 1921, end: double.infinity, name: '4K'),
          */