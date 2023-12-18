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