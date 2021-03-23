package com.example.todonotes

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.reflect.Array
import java.text.DateFormat
import java.util.*
import java.util.concurrent.Flow

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val myintent=Intent(context,MainActivity::class.java)
        creatnotification("here is a todo today",context,myintent)



    }
    fun creatnotification(masg: String,context: Context,intent: Intent) {
        val pendingIntent=PendingIntent.getActivity(context,0,intent,0)
        val notification = NotificationCompat.Builder(context, NoteApp.CHANNEL_ID)
        notification.setSmallIcon(R.drawable.succss_24)
            .setContentTitle("Reminder")
            .setContentText(masg)
            .setContentIntent(pendingIntent)
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(1, notification.build())
    }
}