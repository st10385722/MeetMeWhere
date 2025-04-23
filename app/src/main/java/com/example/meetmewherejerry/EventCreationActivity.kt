package com.example.meetmewherejerry

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty
import androidx.lifecycle.lifecycleScope
import com.example.meetmewherejerry.databinding.ActivityEventCreationBinding
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale
import java.util.Calendar

class EventCreationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventCreationBinding
    private var evm: EventsViewModel = EventsViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_event_creation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityEventCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //validation
        binding.createEventBtn.setOnClickListener {
            //validation
            if(TextUtils.isEmpty(binding.eventTitleEt.text) ||
                TextUtils.isEmpty(binding.eventDescriptionEt.text) ||
                TextUtils.isEmpty(binding.eventDateDP.toString()) ||
                TextUtils.isEmpty(binding.eventTimeEt.text) ||
                TextUtils.isEmpty(binding.eventLocationEt.text)){
                Toast.makeText(this, "One or more fields are not filled in", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val eventDate: Date = dateFormat.parse("${binding.eventDateDP.year}-${binding.eventDateDP.month}-${binding.eventDateDP.dayOfMonth}")
//            Log.d("Correct formatting", "This is a correct date format ${eventDate}")
            //val eventDate: Date = dateFormat.parse(binding.eventDateDP.toString())
            lifecycleScope.launch {
                evm.insertEvent(
                    binding.eventTitleEt.text.toString(),
                    binding.eventDescriptionEt.text.toString(),
                    eventDate,
                    binding.eventTimeEt.text.toString(),
                    binding.eventLocationEt.text.toString()
                )
            }
            EventHubAfterNewEventScreen()
        }

        binding.backBtn.setOnClickListener{
            EventHubScreen()
        }
    }
    private fun EventHubAfterNewEventScreen(){
        val i = Intent(applicationContext, EventHubActivity:: class.java)
        i.putExtra("newEventAdded", "Event added successfully")
        startActivity(i)
    }
    private fun EventHubScreen(){
        val i = Intent(applicationContext, EventHubActivity:: class.java)
        startActivity(i)
    }
}