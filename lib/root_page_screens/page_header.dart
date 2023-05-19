import 'package:flutter/material.dart';
import 'package:sizer/sizer.dart';
import 'package:vault448/text.dart';

Widget pageHeader() {
  return Container(
    alignment: Alignment.center,
    child: ExpansivaText(
      textContent: "FILE MANAGER",
      fontSize: 12.sp,
      fontWeight: FontWeight.bold,
    ),
  );
}
