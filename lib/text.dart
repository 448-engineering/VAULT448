import 'package:flutter/material.dart';
import 'package:vault448/constants.dart';

class CustomText extends StatelessWidget {
  CustomText(
      {Key? key,
      required this.textContent,
      required this.fontSize,
      this.fontWeight = FontWeight.normal,
      this.fontColor = themeColorWhite,
      this.fontFamily = "CarolloPlayscript"})
      : super(key: key);

  String textContent;
  double fontSize;
  FontWeight fontWeight;
  Color fontColor;
  String fontFamily;

  @override
  Widget build(BuildContext context) {
    return Text(
      textContent,
      style: TextStyle(
        color: fontColor,
        fontSize: fontSize,
        fontWeight: fontWeight,
        fontFamily: fontFamily,
      ),
    );
  }
}

class ExpansivaText extends StatelessWidget {
  ExpansivaText({
    Key? key,
    required this.textContent,
    required this.fontSize,
    this.fontWeight = FontWeight.normal,
    this.fontColor = themeColorWhite,
  }) : super(key: key);

  String textContent;
  double fontSize;
  FontWeight fontWeight;
  Color fontColor;

  @override
  Widget build(BuildContext context) {
    return CustomText(
      textContent: textContent,
      fontSize: fontSize,
      fontColor: fontColor,
      fontFamily: "Expansiva",
      fontWeight: fontWeight,
    );
  }
}

class LogoText extends StatelessWidget {
  LogoText({
    Key? key,
    required this.fontSize,
    this.fontWeight = FontWeight.normal,
    this.fontColor = themeColorWhite,
  }) : super(key: key);

  double fontSize;
  FontWeight fontWeight;
  Color fontColor;

  @override
  Widget build(BuildContext context) {
    return CustomText(
      textContent: "VAULT448",
      fontSize: fontSize,
      fontColor: fontColor,
      fontFamily: "Centauri",
      fontWeight: fontWeight,
    );
  }
}
