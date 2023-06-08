import 'package:flutter/material.dart';
import 'package:responsive_sizer/responsive_sizer.dart';
import 'package:vault448/constants.dart';
import 'package:vault448/root_page_screens/utilities_section.dart';
import '../blocks.dart';
import './percentage_total.dart';
import './gib_and_files.dart';
import './page_header.dart';
import 'connections_section.dart';
import 'multimedia_section.dart';

class RootPage extends StatefulWidget {
  const RootPage({Key? key}) : super(key: key);

  @override
  State<RootPage> createState() => _RootPageState();
}

class _RootPageState extends State<RootPage> {
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;

    return xorScaffold(LayoutBuilder(builder: (context, constraints) {
      if (constraints.maxWidth <= 767) {
        return const SizeXs();
      } else if (constraints.maxWidth >= 768 && constraints.maxWidth <= 1199) {
        return Container(
          alignment: Alignment.center,
          child: const SmLayout(),
        );
      } else {
        return Container(
          alignment: Alignment.center,
          child: const SmLayout(),
        );
      }
    }));
  }
}

class SizeXs extends StatefulWidget {
  const SizeXs({Key? key}) : super(key: key);

  @override
  State<SizeXs> createState() => _SizeXsState();
}

class _SizeXsState extends State<SizeXs> {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          const SizedBox(
            height: 25,
          ),
          const PageHeader(),
          const SizedBox(
            height: 25,
          ),
          Container(
            padding: paddingM,
            child: const StorageOccupied(),
          ),
          Container(
            padding: paddingM,
            child: const MediaSection(),
          ),
          Container(
            padding: paddingM,
            child: const UtilitiesSection(),
          ),
          Container(
            padding: paddingM,
            child: const Connections(),
          ),
        ],
      ),
    );
  }
}

class SmLayout extends StatefulWidget {
  const SmLayout({Key? key}) : super(key: key);

  @override
  State<SmLayout> createState() => _SmLayoutState();
}

class _SmLayoutState extends State<SmLayout> {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Container(
          alignment: Alignment.center,
          constraints: const BoxConstraints(
            maxWidth: 1199,
          ),
          child: Column(
            children: [
              const SizedBox(
                height: 25,
              ),
              const PageHeader(),
              const SizedBox(
                height: 10,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Expanded(
                      flex: 1,
                      child: Container(
                        padding: paddingM,
                        height: 40.h,
                        child: const StorageOccupied(),
                      )),
                  Expanded(
                      flex: 1,
                      child: Container(
                        padding: paddingM,
                        child: Column(
                          children: [
                            Container(
                              padding: paddingM,
                              child: const MediaSection(),
                            ),
                            Container(
                              padding: paddingM,
                              child: const UtilitiesSection(),
                            ),
                            Container(
                              padding: paddingM,
                              child: const Connections(),
                            ),
                          ],
                        ),
                      )),
                ],
              )
            ],
          )),
    );
  }
}

class StorageOccupied extends StatefulWidget {
  const StorageOccupied({Key? key}) : super(key: key);

  @override
  State<StorageOccupied> createState() => _StorageOccupiedState();
}

class _StorageOccupiedState extends State<StorageOccupied> {
  @override
  Widget build(BuildContext context) {
    return const Row(
      mainAxisAlignment: MainAxisAlignment.spaceAround,
      children: [
        Expanded(flex: 7, child: InternalStorageTotal()),
        Expanded(flex: 3, child: InternalStorageGibFiles())
      ],
    );
  }
}
