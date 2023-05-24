import 'package:flutter/material.dart';
import 'constants.dart';
import 'package:flutter/services.dart';

ValueNotifier<bool> appTheme = ValueNotifier(true);

Widget xorScaffold(Widget widget) {
  return ValueListenableBuilder(
      valueListenable: appTheme,
      builder: (context, themeValue, _) {
        return AnnotatedRegion<SystemUiOverlayStyle>(
            value: SystemUiOverlayStyle(
                statusBarColor: selectColor(),
                statusBarIconBrightness: selectIconBrightness(),
                statusBarBrightness: selectIconBrightness(),
                systemNavigationBarColor: selectColor(),
                systemNavigationBarIconBrightness: selectIconBrightness()),
            child: SafeArea(
              child: Scaffold(
                backgroundColor: selectColor(),
                body: widget,
              ),
            ));
      });
}

Color selectColor() {
  if (appTheme.value) {
    return themeColorDark;
  } else {
    return themeColorWhite;
  }
}

Brightness selectIconBrightness() {
  if (appTheme.value) {
    return Brightness.light;
  } else {
    return Brightness.dark;
  }
}
