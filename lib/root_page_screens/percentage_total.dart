import 'package:flutter/material.dart';
import 'package:sizer/sizer.dart';
import 'package:vault448/constants.dart';
import 'package:vault448/text.dart';

class InternalStorageTotal extends StatefulWidget {
  InternalStorageTotal({Key? key}) : super(key: key);

  @override
  State<InternalStorageTotal> createState() => _InternalStorageTotalState();
}

class _InternalStorageTotalState extends State<InternalStorageTotal> {
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: customBoxDecoration(),
      padding: paddingL,
      margin: const EdgeInsets.fromLTRB(0, 0, 20, 0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          ExpansivaText(
            textContent: "INTERNAL STORAGE",
            fontSize: 8.sp,
            fontWeight: FontWeight.bold,
          ),
          const SizedBox(height: 20),
          filesSvgIcon,
          const SizedBox(height: 15),
          percentageStorage(),
          const SizedBox(height: 10),
          CustomText(
            textContent: "/storage/emulated/0",
            fontSize: 8.sp,
          )
        ],
      ),
    );
  }
}

Widget percentageStorage() {
  return ExpansivaText(
    textContent: "47%",
    fontSize: 30.sp,
  );
}
