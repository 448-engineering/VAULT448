import 'package:flutter/material.dart';
import 'package:modal_bottom_sheet/modal_bottom_sheet.dart';
import 'package:sizer/sizer.dart';
import 'package:vault448/constants.dart';
import 'package:vault448/text.dart';
import '../native_loader.dart';

class PageHeader extends StatelessWidget {
  const PageHeader({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      child: GestureDetector(
          onTap: () {
            showMaterialModalBottomSheet(
              backgroundColor: themeColorDark,
              context: context,
              builder: (context) => const ApiVersion(),
            );
          },
          child: ExpansivaText(
            textContent: "FILE MANAGER",
            fontSize: 12.sp,
            fontWeight: FontWeight.bold,
          )),
    );
  }
}

class ApiVersion extends StatelessWidget {
  const ApiVersion({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Container(
        height: 20.h,
        child: Column(
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                assetSvg(appIconPath("disk_used"), 'Files Usage', 40),
                SizedBox(
                  width: 2.w,
                ),
                LogoText(
                  fontSize: 20,
                ),
              ],
            ),
            const SizedBox(
              height: 20,
            ),
            FutureBuilder(
                future: checkFfiVersion(),
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return computeText("FFI Version: ${snapshot.data}");
                  } else {
                    return computeText("FFI Version: Checking...");
                  }
                }),
            const SizedBox(
              height: 10,
            ),
            FutureBuilder(
                future: checkAppVersion(),
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return computeText("APP Version: ${snapshot.data}");
                  } else {
                    return computeText("APP Version: Checking...");
                  }
                }),
          ],
        ),
      ),
    );
  }

  Widget computeText(String textContent) {
    return CustomText(
      textContent: textContent,
      fontSize: 10.sp,
    );
  }

  Future<String> checkFfiVersion() async {
    String ffiInfo = await api.ffiInfo();

    return ffiInfo;
  }

  Future<String> checkAppVersion() async {
    String appVersion = await api.appVersion();

    return appVersion;
  }
}
