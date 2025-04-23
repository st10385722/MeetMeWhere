package com.example.meetmewherejerry

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.meetmewherejerry.databinding.ActivityEventCreationBinding
import com.example.meetmewherejerry.databinding.ActivityEventHubBinding
import com.example.meetmewherejerry.databinding.ActivityViewEventDetailsBinding
import kotlinx.coroutines.launch

class ViewEventDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewEventDetailsBinding
    private var event: Events? = null
    private var evm: EventsViewModel = EventsViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_event_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityViewEventDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindValues()

        binding.backBtn.setOnClickListener {
            val i = Intent(applicationContext, EventHubActivity::class.java)
            startActivity(i)
        }
    }

    private fun bindValues(){
        val eventId = intent.getIntExtra("EventID", -1)
        lifecycleScope.launch {
            event = evm.eventDao.getEventById(eventId)
        }

        binding.eventTitleTv.setText("${event?.title}")
        binding.eventDescriptionEt.setText("${event?.description}")
        binding.eventDateEt.setText("${event?.date}")
        binding.eventTimeEt.setText("${event?.time}")
        binding.eventLocationEt.setText("${event?.location}")
    }

}