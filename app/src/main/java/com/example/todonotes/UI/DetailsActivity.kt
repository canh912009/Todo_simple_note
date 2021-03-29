package com.example.todonotes.UI

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.todonotes.Services.MyReceiver
import com.example.todonotes.entities.Note
import com.example.todonotes.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private val viewmodel: myviewmodel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Todo"
        if (intent.action == INTENT_ACTION_UPDATE) {
            binding.customTitle.editText?.setText(
                intent.getStringExtra(Extras_Title).toString()
            )
            binding.customDesc.editText?.setText(
                intent.getStringExtra(Extras_Description).toString()
            )

        }

        binding.button.setOnClickListener {


            val time_picker = binding.timePicker
            time_picker.setIs24HourView(false)
            val calendar = Calendar.getInstance()
            calendar.apply {
                set(Calendar.HOUR, time_picker.hour)
                set(Calendar.MINUTE, time_picker.minute)
            }
            creatAlaram(calendar)


            val time = SimpleDateFormat.getTimeInstance().format(calendar.timeInMillis)

            val note = Note(
                intent.getIntExtra(Extras_ID, 0),
                binding.customTitle.editText?.text.toString(),
                binding.customDesc.editText?.text.toString(),
                time
            )

            if (intent.action == INTENT_ACTION_UPDATE) {

                viewmodel.UpdateNote(note)


            } else {
                viewmodel.InsertNewNote(note)


            }
            val Intent = Intent(this@DetailsActivity, MainActivity::class.java)
            startActivity(Intent)

        }


    }


    fun creatAlaram(calendar: Calendar) {
        //val time = System.currentTimeMillis()
        val intent = Intent(this@DetailsActivity, MyReceiver::class.java)
        val alarm = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(
            this@DetailsActivity, 0, intent, 0
        )
        alarm.set(AlarmManager.RTC_WAKEUP,  calendar.timeInMillis-600000, pendingIntent)
    }

    companion object {
        const val Extras_Title = "title"
        const val Extras_Description = "description"
        const val Extras_Date = "date"
        const val Extras_ID = "id"
        const val INTENT_ACTION_UPDATE = "update"
        const val INTENT_ACTION_ADD_NEW = "add"
        const val BUNDLE_KEY = "bundle"
    }
}