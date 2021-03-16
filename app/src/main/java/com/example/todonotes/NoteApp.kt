package com.example.todonotes

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApp :Application (){
    companion object{
        const  val CHANNEL_ID = "1"
    }
    var CHANNEL_NAME = "my channel"
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT;
            val channel =NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription("my channel");
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

        }
    }

}