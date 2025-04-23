package com.example.meetmewherejerry

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.meetmewherejerry.databinding.ActivityEditEventBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class EditEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditEventBinding
    private var evm: EventsViewModel = EventsViewModel()
    //creates a current event object
    private var currentEvent: Events? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_event)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityEditEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //gets event id from intent
        val eventId = intent.getIntExtra("EventID", -1)

        //gets event off main thread
        lifecycleScope.launch {
            currentEvent = evm.eventDao.getEventById(eventId)
            currentEvent?.let {populateFields(it)}
        }

        binding.saveBtn.setOnClickListener {
            if(validateFields()){
                updateEvent()
            }
        }

        binding.backBtn.setOnClickListener {
            val i = Intent(applicationContext, EventHubActivity:: class.java)
            startActivity(i)
        }
    }

    //populates edit texts with the current event information
    private fun populateFields(event: Events) {
        binding.editTextTitle.setText(event.title)
        binding.editTextDescription.setText(event.description)
        binding.editTextTime.setText(event.time)
        binding.editTextLocation.setText(event.location)

        val calendar = Calendar.getInstance().apply { time = event.date }
        binding.eventDateDP.updateDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    //checks if all information is inputted
    private fun validateFields(): Boolean {
        return if (TextUtils.isEmpty(binding.editTextTitle.text) ||
            TextUtils.isEmpty(binding.editTextDescription.text) ||
            TextUtils.isEmpty(binding.editTextTime.text) ||
            TextUtils.isEmpty(binding.editTextLocation.text)) {
            Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_LONG).show()
            false
        } else {
            true
        }
    }

    //updates the event with the new information
    private fun updateEvent() {
        currentEvent?.let { event ->
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val eventDate: Date = dateFormat.parse(
                "${binding.eventDateDP.year}-${binding.eventDateDP.month + 1}-${binding.eventDateDP.dayOfMonth}"
            ) ?: Date() // Fallback to current date if parsing fails

            // Update the event object
            event.title = binding.editTextTitle.text.toString()
            event.description = binding.editTextDescription.text.toString()
            event.date = eventDate
            event.time = binding.editTextTime.text.toString()
            event.location = binding.editTextLocation.text.toString()

            lifecycleScope.launch {
                evm.updateEvent(event) // Assume you add this to EventsViewModel
                Toast.makeText(this@EditEventActivity, "Event updated", Toast.LENGTH_SHORT).show()
                finish() // Close activity after saving
            }
        }
    }
}