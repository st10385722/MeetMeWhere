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
import com.example.meetmewherejerry.ActivityAccess.AuthActivityAccess
import com.example.meetmewherejerry.databinding.ActivityEventHubBinding

class EventHubActivity : AuthActivityAccess() {
    private lateinit var binding : ActivityEventHubBinding
    private var evm : EventsViewModel = EventsViewModel()

    override fun onAuthenticatedCreate(savedInstanceState: Bundle?) {
        super.onAuthenticatedCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_access)

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

        if(evm.eventDao.getAnyEvent() == null){
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
        evm.eventsList.observe(this) { events ->
            val adapter = EventListAdapter(
                this,
                events,
                onDeleteClick = { event ->
                    evm.deleteEvent(event.id)
                },
                onUpdateClick = { event ->
                    val i = Intent(applicationContext, EditEventActivity::class.java)
                    i.putExtra("EventID", event.id)
                    startActivity(i)
                },
                onViewClick = { event ->
                    val i = Intent(applicationContext, ViewEventDetailsActivity::class.java)
                    i.putExtra("EventID", event.id)
                    startActivity(i)
                }
            )
            binding.eventRv.adapter = adapter
            binding.eventRv.layoutManager = LinearLayoutManager(this)
        }
    }
}