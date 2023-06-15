import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:vault448/constants.dart';
import 'package:vault448/text.dart';
import 'package:responsive_sizer/responsive_sizer.dart';

double mediaIconSize = 6.w;

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
            UtilityColumn(
              mediaIcon: androidIcon(mediaIconSize),
              textContent: "Apps",
            ),
            UtilityColumn(
              mediaIcon: trashIcon(mediaIconSize),
              textContent: "Trash",
            ),
            UtilityColumn(
              mediaIcon: downloadsIcon(mediaIconSize),
              textContent: "Downloads",
            ),
            UtilityColumn(
              mediaIcon: extrasIcon(mediaIconSize),
              textContent: "Extras",
            )
          ],
        ));
  }
}

class UtilityColumn extends StatefulWidget {
  UtilityColumn({Key? key, required this.textContent, required this.mediaIcon})
      : super(key: key);

  String textContent;
  SvgPicture mediaIcon;

  @override
  State<UtilityColumn> createState() => _UtilityColumnState();
}

class _UtilityColumnState extends State<UtilityColumn> {
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
          fontSize: label14sp,
        )
      ],
    );
  }
}
