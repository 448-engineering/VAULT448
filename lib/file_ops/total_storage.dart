import 'package:storage_space/storage_space.dart';

getTotalStorage() async {
  StorageSpace storageSpace = await getStorageSpace(
      lowOnSpaceThreshold: 2 * 1024 * 1024 * 1024, fractionDigits: 0);
  int totalSpace = storageSpace.total;
  print('Total device storage: $totalSpace bytes');
}
