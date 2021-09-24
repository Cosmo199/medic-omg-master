package com.example.medicomgmester.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.medicomgmester.R

class NotificationReceiver2 : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "Alarm"
            val descriptionText = "Alarm details"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("AlarmId", name, importance)
            mChannel.description = descriptionText
            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val preferencesTimeHolder =
            context?.getSharedPreferences("TIME_HOLDER", Context.MODE_PRIVATE)
        var getOutDate: String? = preferencesTimeHolder?.getString("dateIntNote", "noDate")
        var getOutTime: String? = preferencesTimeHolder?.getString("timeOut", "noDate")

        // Create the notification to be shown
        val mBuilder = NotificationCompat.Builder(context!!, "AlarmId")
            .setSmallIcon(R.drawable.kidney_3)
            .setContentTitle("แจ้งเตือนรายการนัด")
            .setContentText("คุณมีรายการนัดถอดสายวันที่: $getOutDate เวลา: $getOutTime น.")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Get the Notification manager service
        val am = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Generate an Id for each notification
        val id = System.currentTimeMillis() / 100

        // Show a notification
        am.notify(id.toInt(), mBuilder.build())
    }
}