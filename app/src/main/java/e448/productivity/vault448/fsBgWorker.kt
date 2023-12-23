package e448.productivity.vault448

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

const val STORAGE_DETAILS_WORKER_NAME = "VAULT448_Update_Internal_Storage_Details"
class BootCompletedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            println("Hello World, Boot Completed")
        }
    }
}