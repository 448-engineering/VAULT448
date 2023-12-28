package e448.productivity.vault448

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat


const val FS_SERVICE_ID = 1
const val FS_SERVICE_CHANNEL_ID = "FsListener"

class BootCompletedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            println("Hello World, Boot Completed")

            Intent(context, FsListenerService::class.java).also {
                it.action  = FsListenerService.FsActions.START.toString()
                if (context != null) {
                    ContextCompat.startForegroundService(context, it)
                }
            }

            val serviceIntent = Intent(context, FsListenerService::class.java)
            if (context != null) {
                ContextCompat.startForegroundService(context, serviceIntent)
            }else {
                println("ERROR: The Context is null")
            }
        }
    }
}

class FsListenerService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //return super.onStartCommand(intent, flags, startId)

        when (intent?.action) {
            FsActions.START.toString() -> start()
            FsActions.STOP.toString() -> stopSelf()
        }

        return START_STICKY

    }

    private fun start() {
        val notification = NotificationCompat.Builder(
            this,
            FS_SERVICE_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.vault)
            .setContentTitle("Vault448 File Manager Active")
            .setContentText("Listening for File System Changes")
            .build()

        startForeground(FS_SERVICE_ID, notification)
    }
    enum class FsActions {
        START, STOP
    }
}


class FsNotificationChannel: Application() {
    /// Started each time application is launched
    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           val channel = NotificationChannel(
               FS_SERVICE_CHANNEL_ID,
               "Listening for File System Changes",
               NotificationManager.IMPORTANCE_HIGH
           )

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}

/*

class FsListenerService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("FOREGROUND SERVICE STARTED")

        //return super.onStartCommand(intent, flags, startId)

        return START_STICKY
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d("Stopping","Stopping Service")

        return super.stopService(name)
    }

    override fun onDestroy() {

        Log.d("Stopped","Service Stopped")
        super.onDestroy()
    }
}
 */