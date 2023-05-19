import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:vault448/constants.dart';
import 'package:sizer/sizer.dart';
import 'package:vault448/text.dart';

double mediaIconSize = 8.w;
double mediaFontSize = 8.sp;

class Connections extends StatelessWidget {
  Connections({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceAround,
      children: [
        Row(
          children: [
            ConnectionColumn(
              mediaIcon: usbcIcon(mediaIconSize),
              connectionType: "USB",
              count: 0,
              decoration: usbDecoration,
            ),
            ConnectionColumn(
              mediaIcon: networkConnectionsIcon(mediaIconSize),
              connectionType: "NAS",
              count: 0,
              decoration: networkConnectionsDecoration,
            ),
          ],
        ),
        ConnectionColumn(
          mediaIcon: vaultIcon(mediaIconSize),
          connectionType: "VAULT",
          count: 0,
          decoration: vaultDecoration,
        )
      ],
    );
  }
}

class ConnectionColumn extends StatefulWidget {
  ConnectionColumn(
      {Key? key,
      required this.mediaIcon,
      required this.count,
      required this.connectionType,
      required this.decoration})
      : super(key: key);

  SvgPicture mediaIcon;
  int count;
  String connectionType;
  BoxDecoration decoration;

  @override
  State<ConnectionColumn> createState() => _ConnectionColumnState();
}

class _ConnectionColumnState extends State<ConnectionColumn> {
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: widget.decoration,
      padding: paddingM,
      child: Column(
        children: [
          widget.mediaIcon,
          mediaSizedBox(),
          ExpansivaText(
            textContent: "${widget.count}",
            fontSize: mediaFontSize,
          ),
          mediaSizedBox(),
          ExpansivaText(
            textContent: widget.connectionType,
            fontSize: mediaFontSize,
          ),
        ],
      ),
    );
  }
}

Widget mediaSizedBox() {
  return const SizedBox(
    height: 10,
  );
}

BoxDecoration usbDecoration = const BoxDecoration(
  borderRadius: BorderRadius.only(
    topLeft: Radius.circular(15),
    topRight: Radius.circular(0),
    bottomLeft: Radius.circular(15),
    bottomRight: Radius.circular(0),
  ),
  color: xorColorDarker,
);

BoxDecoration networkConnectionsDecoration = const BoxDecoration(
  borderRadius: BorderRadius.only(
    topLeft: Radius.circular(0),
    topRight: Radius.circular(15),
    bottomLeft: Radius.circular(0),
    bottomRight: Radius.circular(15),
  ),
  color: xorColorDarker,
);

BoxDecoration vaultDecoration = BoxDecoration(
  borderRadius: BorderRadius.circular(15),
  color: xorColorDarker,
);
