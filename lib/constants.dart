import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:responsive_sizer/responsive_sizer.dart';
import 'package:vault448/text.dart';
import '../native_loader.dart';

/*

XS - ( Screens    0px - 767px  wide ) - Generally Phones / Small Tablets
SM - ( Screens  768px - 991px  wide ) - Generally Tablets
MD - ( Screens  992px - 1199px wide ) - Generally Computers / Landscape Tablets
LG - ( Screens 1200px+         wide ) - Generally Widescreen Computers

*/

const Color themeColorDarker = Color(0xFF100C17);
const Color themeColorDark = Color(0xFF1B1720);
const Color themeColorLight = Color(0xFF9575e0);
const Color themeColorLighter = Color(0xFF93A5F8);
const Color themeColorWhite = Color(0xFFFFFFFF);

double label20sp = 20.sp;
double label18sp = 18.sp;
double label16sp = 16.sp;
double label14sp = 14.sp;
double label12sp = 12.sp;
double label10sp = 10.sp;
double label8sp = 8.sp;

Widget devText = CustomText(textContent: "PLACEHOLDER TEXT", fontSize: 15.sp);

EdgeInsetsGeometry paddingXS = const EdgeInsets.all(5);
EdgeInsetsGeometry paddingS = const EdgeInsets.all(10);
EdgeInsetsGeometry paddingM = const EdgeInsets.all(15);
EdgeInsetsGeometry paddingL = const EdgeInsets.all(20);
EdgeInsetsGeometry paddingXL = const EdgeInsets.all(25);
EdgeInsetsGeometry paddingXXL = const EdgeInsets.all(30);

Future<String> numConverter(int value) async {
  String convertedValue = await api.numFormat(value: value);

  return convertedValue;
}

BoxDecoration customBoxDecoration({Color color = themeColorDarker}) {
  return BoxDecoration(
    borderRadius: BorderRadius.circular(15),
    color: color,
  );
}

SvgPicture filesSvgIcon = assetSvg(appIconPath("files"), 'Files', 35.w);
SvgPicture ssdStorageIcon =
    assetSvg(appIconPath("phone_storage_ssd"), 'SOC Storage', 20.w);

SvgPicture filesUsageIcon =
    assetSvg(appIconPath("disk_used"), 'Files Usage', 20.w);

SvgPicture assetSvg(String imageIcon, String label, double width) {
  return SvgPicture.asset(
    imageIcon,
    semanticsLabel: label,
    width: width,
  );
}

SvgPicture androidIcon(double size) {
  return assetSvg(appIconPath("android"), 'Android Icon', size);
}

SvgPicture photosIcon(double size) {
  return assetSvg(appIconPath("photos"), 'Photos Icon', size);
}

SvgPicture videosIcon(double size) {
  return assetSvg(appIconPath("videos"), 'Videos Icon', size);
}

SvgPicture musicIcon(double size) {
  return assetSvg(appIconPath("electric-guitar"), 'Music Icon', size);
}

SvgPicture documentsIcon(double size) {
  return assetSvg(appIconPath("documents"), 'Documents Icon', size);
}

SvgPicture settingsIcon(double size) {
  return assetSvg(appIconPath("puzzle"), 'Settings Icon', size);
}

SvgPicture downloadsIcon(double size) {
  return assetSvg(appIconPath("download"), 'Downloads Icon', size);
}

SvgPicture trashIcon(double size) {
  return assetSvg(appIconPath("trash"), 'Trash Icon', size);
}

SvgPicture extrasIcon(double size) {
  return assetSvg(appIconPath("extras"), 'Extras Icon', size);
}

SvgPicture usbcIcon(double size) {
  return assetSvg(appIconPath("usb-c"), 'USB-C Icon', size);
}

SvgPicture networkConnectionsIcon(double size) {
  return assetSvg(appIconPath("connection"), 'Network Connections Icon', size);
}

SvgPicture vaultIcon(double size) {
  return assetSvg(appIconPath("vault"), 'Vault Icon', size);
}

String appIconPath(String iconName) {
  return "assets/app_icons/$iconName.svg";
}
