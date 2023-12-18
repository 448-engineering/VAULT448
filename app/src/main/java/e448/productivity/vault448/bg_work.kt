package e448.productivity.vault448

import android.app.Application
import android.app.usage.StorageStatsManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.storage.StorageManager
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit

const val STORAGE_DETAILS_WORKER_NAME = "VAULT448_Update_Internal_Storage_Details"

class BootCompletedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            println("Hello World, Boot Completed")

            updateInternalStorageDetails(context)
        }
    }
}


class StorageDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val workManager = WorkManager.getInstance(application)

    val storageDetailsLiveData: LiveData<MutableList<WorkInfo>> =
        workManager.getWorkInfosForUniqueWorkLiveData(STORAGE_DETAILS_WORKER_NAME)


    fun refreshData() {
        // Trigger refresh when needed
        val refreshWorkRequest = PeriodicWorkRequestBuilder<InternalStorageDetailsWorker>(1, TimeUnit.SECONDS)
            .build()

        workManager.enqueueUniquePeriodicWork(
            STORAGE_DETAILS_WORKER_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            refreshWorkRequest
        )
    }
}


class InternalStorageDetailsWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {
        val details = InternalStorageDetailsFfi()

        val storageManager = applicationContext.getSystemService(Context.STORAGE_SERVICE) as StorageManager
        val storageVolumes = storageManager.storageVolumes
        val storageStatsManager =
            applicationContext.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
        val storageVolume = storageVolumes[0]
        val path = getPath(applicationContext, storageVolume)
        if (storageVolume.isPrimary) {
            details.addTotalSpace(storageStatsManager.getTotalBytes(StorageManager.UUID_DEFAULT).toULong())
            details.addFreeSpace(storageStatsManager.getFreeBytes(StorageManager.UUID_DEFAULT).toULong())
        } else if (path != null) {
            val file = File(path)

            details.addTotalSpace(file.totalSpace.toULong())
            details.addFreeSpace(file.freeSpace.toULong())
        }


        details.addDescription(storageVolume.getDescription(applicationContext))
        details.addPath(path.toString())

        // FIXME: Retrying on handling all errors within this methods
        //FIXME: Handle for other android version before version `O`

        val outcome = details.get()
        val outputData = workDataOf(
                "volumeDescription" to outcome.volumeDescription,
                "path" to outcome.path,
                "totalSpace" to outcome.totalSpace.toLong(),
                "usedSpace" to outcome.usedSpace.toLong(),
                "freeSpace" to outcome.freeSpace.toLong(),
                "percentageUsed" to outcome.percentageUsed.toLong(),
        )

        return Result.success(outputData)
    }
}

fun updateInternalStorageDetails(context: Context?) {
    val periodicWorkRequest = PeriodicWorkRequestBuilder<InternalStorageDetailsWorker>(1, TimeUnit.SECONDS)
        .build()

    WorkManager.getInstance(context!!).enqueueUniquePeriodicWork(
        STORAGE_DETAILS_WORKER_NAME,
        ExistingPeriodicWorkPolicy.UPDATE,
        periodicWorkRequest
    )
}

/*
@RequiresApi(Build.VERSION_CODES.O)
fun getStorageVolumesAccessState2(context: Context): InternalStorageDetails {
    val storageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
    val storageVolumes = storageManager.storageVolumes
    val storageStatsManager =
        context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
    val storageVolume = storageVolumes[0]
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
}*/
