import 'package:flutter/material.dart';
//import 'package:responsive_sizer/responsive_sizer.dart';
import 'package:vault448/constants.dart';
import 'package:vault448/text.dart';

class InternalStorageGibFiles extends StatefulWidget {
  const InternalStorageGibFiles({Key? key}) : super(key: key);

  @override
  State<InternalStorageGibFiles> createState() =>
      _InternalStorageGibFilesState();
}

class _InternalStorageGibFilesState extends State<InternalStorageGibFiles> {
  @override
  Widget build(BuildContext context) {
    return const Column(
      children: [InternalTotalGiB(), SizedBox(height: 10), TotalFiles()],
    );
  }
}

class InternalTotalGiB extends StatefulWidget {
  const InternalTotalGiB({Key? key}) : super(key: key);

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

Widget labelText(String textContent) {
  return ExpansivaText(
    textContent: textContent,
    fontSize: label12sp,
  );
}

Widget gibDiv(BuildContext context) {
  return LayoutBuilder(builder: (ctx, constraints) {
    return SizedBox(
      width: constraints.maxWidth,
      child: Column(
        children: [
          labelText("40 GiB"),
          LayoutBuilder(builder: (ctx, constraints) {
            return SizedBox(
              width: constraints.maxWidth * 0.6,
              child: horizontalDivider(),
            );
          }),
          labelText("256 GiB")
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
  const TotalFiles({Key? key}) : super(key: key);

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
    return SizedBox(
      width: constraints.maxWidth,
      child: Column(
        children: [
          FutureBuilder(
              future: numConverter(10000000),
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return ExpansivaText(
                    textContent: snapshot.data as String,
                    fontSize: label16sp,
                  );
                } else {
                  return ExpansivaText(
                    textContent: "CALCULATING...",
                    fontSize: label16sp,
                  );
                }
              }),
          const SizedBox(
            height: 8,
          ),
          ExpansivaText(
            textContent: "FILES",
            fontSize: label12sp,
          ),
        ],
      ),
    );
  });
}
