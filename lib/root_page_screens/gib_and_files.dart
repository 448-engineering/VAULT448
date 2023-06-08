import 'package:flutter/material.dart';
import 'package:responsive_sizer/responsive_sizer.dart';
import 'package:vault448/constants.dart';
import 'package:vault448/text.dart';

class InternalStorageGibFiles extends StatefulWidget {
  InternalStorageGibFiles({Key? key}) : super(key: key);

  @override
  State<InternalStorageGibFiles> createState() =>
      _InternalStorageGibFilesState();
}

class _InternalStorageGibFilesState extends State<InternalStorageGibFiles> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [InternalTotalGiB(), const SizedBox(height: 10), TotalFiles()],
    );
  }
}

class InternalTotalGiB extends StatefulWidget {
  InternalTotalGiB({Key? key}) : super(key: key);

  @override
  State<InternalTotalGiB> createState() => _InternalTotalGiBState();
}

class _InternalTotalGiBState extends State<InternalTotalGiB> {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: paddingS,
      decoration: customBoxDecoration(),
      child: Column(
        children: [
          ssdStorageIcon,
          const SizedBox(
            height: 5,
          ),
          gibDiv(context)
        ],
      ),
    );
  }
}

Widget gibDiv(BuildContext context) {
  return LayoutBuilder(builder: (ctx, constraints) {
    return Container(
      width: constraints.maxWidth,
      child: Column(
        children: [
          ExpansivaText(
            textContent: "40 GiB",
            fontSize: 8.sp,
          ),
          LayoutBuilder(builder: (ctx, constraints) {
            return Container(
              width: constraints.maxWidth * 0.6,
              child: horizontalDivider(),
            );
          }),
          ExpansivaText(
            textContent: "256 GiB",
            fontSize: 8.sp,
          ),
        ],
      ),
    );
  });
}

Widget horizontalDivider() {
  return Container(
    height: 1,
    color: themeColorLight,
    margin: const EdgeInsets.fromLTRB(0, 2, 0, 5),
  );
}

class TotalFiles extends StatefulWidget {
  TotalFiles({Key? key}) : super(key: key);

  @override
  State<TotalFiles> createState() => TotalFilesState();
}

class TotalFilesState extends State<TotalFiles> {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: paddingS,
      decoration: customBoxDecoration(),
      child: Column(
        children: [
          filesUsageIcon,
          const SizedBox(
            height: 5,
          ),
          totalFilesDiv(context)
        ],
      ),
    );
  }
}

Widget totalFilesDiv(BuildContext context) {
  return LayoutBuilder(builder: (ctx, constraints) {
    return Container(
      width: constraints.maxWidth,
      child: Column(
        children: [
          ExpansivaText(
            textContent: "8,400",
            fontSize: 10.sp,
          ),
          const SizedBox(
            height: 8,
          ),
          ExpansivaText(
            textContent: "FILES",
            fontSize: 6.sp,
          ),
        ],
      ),
    );
  });
}
