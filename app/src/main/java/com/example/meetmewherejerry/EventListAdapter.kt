package com.example.meetmewherejerry

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class EventListAdapter(private var context: Context, private var eventList: List<Users>) :
    RecyclerView.Adapter<EventListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): EventListAdapter.MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.event_rom, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventListAdapter.MyViewHolder,position: Int) {
        var myModel =
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var eventTitleTv:TextView
        private var eventDateTv:TextView

        init{
            eventTitleTv = itemView.findViewById(R.id.eventTitleTv)
            eventDateTv = itemView.findViewById(R.id.eventDateTv)
        }
    }
}