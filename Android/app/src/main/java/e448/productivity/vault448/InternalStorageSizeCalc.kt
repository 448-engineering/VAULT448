package e448.productivity.vault448

import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Build
import android.os.Parcel
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import android.text.format.Formatter
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.File

@RequiresApi(Build.VERSION_CODES.O)
fun getMultipleStorageVolumesAccessState(context: Context) {
    val storageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
    val storageVolumes = storageManager.storageVolumes
    val storageStatsManager =
        context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
    for (storageVolume in storageVolumes) {
        var freeSpace: Long = 0L
        var totalSpace: Long = 0L
        val path = getPath(context, storageVolume)
        if (storageVolume.isPrimary) {
            totalSpace = storageStatsManager.getTotalBytes(StorageManager.UUID_DEFAULT)
            freeSpace = storageStatsManager.getFreeBytes(StorageManager.UUID_DEFAULT)
        } else if (path != null) {
            val file = File(path)
            freeSpace = file.freeSpace
            totalSpace = file.totalSpace
        }
        val usedSpace = totalSpace - freeSpace
        val freeSpaceStr = Formatter.formatFileSize(context, freeSpace)
        val totalSpaceStr = Formatter.formatFileSize(context, totalSpace)
        val usedSpaceStr = Formatter.formatFileSize(context, usedSpace)
        Log.d(
            "AppLog",
            "${storageVolume.getDescription(context)} - path:$path total:$totalSpaceStr used:$usedSpaceStr free:$freeSpaceStr"
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun getStorageVolumesAccessState(context: Context): InternalStorageDetails {
    val storageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
    val storageVolumes = storageManager.storageVolumes
    val storageStatsManager =
        context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
    val storageVolume = storageVolumes.get(0)
    var freeSpace: Long = 0L
    var totalSpace: Long = 0L
    val path = getPath(context, storageVolume)
    if (storageVolume.isPrimary) {
        totalSpace = storageStatsManager.getTotalBytes(StorageManager.UUID_DEFAULT)
        freeSpace = storageStatsManager.getFreeBytes(StorageManager.UUID_DEFAULT)
    } else if (path != null) {
        val file = File(path)
        freeSpace = file.freeSpace
        totalSpace = file.totalSpace
    }
    /*val usedSpace = totalSpace - freeSpace
    val freeSpaceStr = Formatter.formatFileSize(context, freeSpace)
    val totalSpaceStr = Formatter.formatFileSize(context, totalSpace)
    val usedSpaceStr = Formatter.formatFileSize(context, usedSpace)*/

    val usedSpace = totalSpace - freeSpace
    val freeSpaceStr = freeSpace / (1024 * 1024 * 1024)
    val totalSpaceStr = totalSpace / (1024 * 1024 * 1024)
    val usedSpaceStr = usedSpace / (1024 * 1024 * 1024)

    val details = InternalStorageDetails()
    details.volumeDescription = storageVolume.getDescription(context)
    details.path = path.toString()
    details.totalSpace = totalSpaceStr.toString()
    details.usedSpace = usedSpaceStr.toString()
    details.freeSpace = freeSpaceStr.toString()
    details.percentageUsed = ((usedSpace.toDouble() / totalSpace) * 100).toLong()

    return details
}

class InternalStorageDetails {
    var volumeDescription: String = ""
    var path: String = ""
    var totalSpace: String = ""
    var usedSpace: String = ""
    var freeSpace: String = ""
    var percentageUsed: Long = 0

}

@RequiresApi(Build.VERSION_CODES.N)
fun getPath(context: Context, storageVolume: StorageVolume): String? {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        storageVolume.directory?.absolutePath?.let { return it }
    try {
        return storageVolume.javaClass.getMethod("getPath").invoke(storageVolume) as String
    } catch (e: Exception) {
    }
    try {
        return (storageVolume.javaClass.getMethod("getPathFile")
            .invoke(storageVolume) as File).absolutePath
    } catch (e: Exception) {
    }
    val extDirs = context.getExternalFilesDirs(null)
    for (extDir in extDirs) {
        val storageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
        val fileStorageVolume: StorageVolume = storageManager.getStorageVolume(extDir)
            ?: continue
        if (fileStorageVolume == storageVolume) {
            var file = extDir
            while (true) {
                val parent = file.parentFile ?: return file.absolutePath
                val parentStorageVolume = storageManager.getStorageVolume(parent)
                    ?: return file.absolutePath
                if (parentStorageVolume != storageVolume)
                    return file.absolutePath
                file = parent
            }
        }
    }
    try {
        val parcel = Parcel.obtain()
        storageVolume.writeToParcel(parcel, 0)
        parcel.setDataPosition(0)
        parcel.readString()
        return parcel.readString()
    } catch (e: Exception) {
    }
    return null
}