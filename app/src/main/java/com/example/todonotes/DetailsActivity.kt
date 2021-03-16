package com.example.todonotes

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProviders
import com.example.todonotes.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.StringBuilder
@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    lateinit var viewmodel: myviewmodel
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel = ViewModelProviders.of(this).get(myviewmodel::class.java)
        supportActionBar?.title = "Todo"
        if (intent.flags == Intent.FLAG_ACTIVITY_FORWARD_RESULT) {
            binding.customTitle.editText?.setText(intent.getStringExtra(Extras_Title).toString())
            binding.customDesc.editText?.setText(
                intent.getStringExtra(Extras_Description).toString()
            )
        }

        binding.button.setOnClickListener {
           binding.timePicker.setIs24HourView(true)
            val time="${binding.timePicker.hour}/${binding.timePicker.minute}"
            val note=Note(intent.getIntExtra(Extras_ID,0),
                binding.customTitle.editText?.text.toString()
                ,binding.customDesc.editText?.text.toString(),
                time)

            if (intent.flags == Intent.FLAG_ACTIVITY_FORWARD_RESULT) {

                viewmodel.UpdateNote(note)

                    creatnotification("Note updated successfully")



            }
            else{
                viewmodel.InsertNewNote(note)

                    creatnotification("Note inserted successfully ")

            }
            val Intent=Intent(this@DetailsActivity,MainActivity::class.java)
            startActivity(Intent)

        }


    }
   fun creatnotification(masg:String) {

        val notification =NotificationCompat.Builder(getApplicationContext(), NoteApp.CHANNEL_ID);
        notification.setSmallIcon(R.drawable.succss_24).setContentTitle("updating")
            .setContentText(masg);
        val notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, notification.build());}

    companion object {
        const val Extras_Title = "title"
        const val Extras_Description = "description"
        const val Extras_Date = "date"
        const val Extras_ID = "id"
    }
}