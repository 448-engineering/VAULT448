import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:vault448/constants.dart';
import 'package:responsive_sizer/responsive_sizer.dart';
import 'package:vault448/text.dart';

double mediaIconSize = 6.w;

class MediaSection extends StatefulWidget {
  const MediaSection({Key? key}) : super(key: key);

  @override
  State<MediaSection> createState() => _MediaSectionState();
}

class _MediaSectionState extends State<MediaSection> {
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;

    return Container(
        decoration: customBoxDecoration(),
        padding: paddingM,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              alignment: Alignment.center,
              width: size.width,
              child: ExpansivaText(
                textContent: "MEDIA",
                fontSize: label14sp,
              ),
            ),
            const SizedBox(
              height: 15,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Expanded(
                  flex: 1,
                  child: MediaColumn(
                      textContent: "Photos",
                      icon: photosIcon(mediaIconSize),
                      count: 200),
                ),
                Expanded(
                  flex: 1,
                  child: MediaColumn(
                      textContent: "Videos",
                      icon: videosIcon(mediaIconSize),
                      count: 40),
                ),
                Expanded(
                  flex: 1,
                  child: MediaColumn(
                      textContent: "Music",
                      icon: musicIcon(mediaIconSize),
                      count: 6000),
                ),
                Expanded(
                  flex: 1,
                  child: MediaColumn(
                      textContent: "Documents",
                      icon: documentsIcon(mediaIconSize),
                      count: 2909873),
                ),
              ],
            ),
          ],
        ));
  }
}

class MediaColumn extends StatelessWidget {
  MediaColumn({
    Key? key,
    required this.textContent,
    required this.icon,
    required this.count,
  }) : super(key: key);

  String textContent;
  SvgPicture icon;
  int count;

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: numConverter(count),
        builder: (context, snapshot) {
          return Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              icon,
              const SizedBox(height: 10),
              if (snapshot.hasData)
                countFormatted(snapshot.data as String)
              else
                countFormatted("..."),
              const SizedBox(height: 5),
              CustomText(
                textContent: textContent,
                fontSize: label12sp,
              ),
            ],
          );
        });
  }
}

Widget countFormatted(String textContent) {
  return ExpansivaText(
    textContent: textContent,
    fontSize: label14sp,
  );
}
