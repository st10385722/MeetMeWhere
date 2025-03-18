package com.example.meetmewherejerry

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meetmewherejerry.databinding.ActivityEventHubBinding

class EventHubActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEventHubBinding
    private var evm : EventsViewModel = EventsViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_event_hub)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityEventHubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra("Username")

        val eventAdded = intent.getStringExtra("newEventAdded")
        if (username != null) {
            binding.welcomeTv.text = "Welcome, $username make an event"
        } else {
            binding.welcomeTv.text = "Welcome user, make an event"
        }
        val eventList = evm.eventsList
        getData()

        if(eventList.isEmpty()){
            binding.listEventTv.text = "There are no events added. Consider creating one!"
        }

        if(eventAdded != null){
            Toast.makeText(this, "$eventAdded", Toast.LENGTH_LONG).show()
        }
        binding.createEventBtn.setOnClickListener {
            eventCreationScreen()
        }
    }

    private fun eventCreationScreen(){
        val i = Intent(applicationContext, EventCreationActivity::class.java)
        startActivity(i)
    }

    private fun getData(){
        var eventList =  evm.eventsList
        var adapter = EventListAdapter(this, eventList)
        binding.eventRv.adapter=adapter
        binding.eventRv.layoutManager = LinearLayoutManager(this)
    }
}