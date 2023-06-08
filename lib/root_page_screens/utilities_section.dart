import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:vault448/constants.dart';
import 'package:responsive_sizer/responsive_sizer.dart';
import 'package:vault448/text.dart';

double mediaIconSize = 8.w;

class UtilitiesSection extends StatelessWidget {
  const UtilitiesSection({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
        decoration: customBoxDecoration(),
        padding: paddingM,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            UtiltiyColumn(
              mediaIcon: settingsIcon(mediaIconSize),
              textContent: "Settings",
            ),
            UtiltiyColumn(
              mediaIcon: trashIcon(mediaIconSize),
              textContent: "Trash",
            ),
            UtiltiyColumn(
              mediaIcon: downloadsIcon(mediaIconSize),
              textContent: "Downloads",
            ),
            UtiltiyColumn(
              mediaIcon: extrasIcon(mediaIconSize),
              textContent: "Extras",
            )
          ],
        ));
  }
}

class UtiltiyColumn extends StatefulWidget {
  UtiltiyColumn({Key? key, required this.textContent, required this.mediaIcon})
      : super(key: key);

  String textContent;
  SvgPicture mediaIcon;

  @override
  State<UtiltiyColumn> createState() => _UtiltiyColumnState();
}

class _UtiltiyColumnState extends State<UtiltiyColumn> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        widget.mediaIcon,
        const SizedBox(
          height: 10,
        ),
        CustomText(
          textContent: widget.textContent,
          fontSize: 8.sp,
        )
      ],
    );
  }
}
