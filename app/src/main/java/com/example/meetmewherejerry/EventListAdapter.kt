package com.example.meetmewherejerry

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class EventListAdapter(private var context: Context,
                       private var eventList: List<Events>,
                       private val onDeleteClick: (Events) -> Unit,
                       private val onUpdateClick: (Events) -> Unit,
                       private val onViewClick: (Events) -> Unit
) :RecyclerView.Adapter<EventListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): EventListAdapter.MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.event_rom, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventListAdapter.MyViewHolder,position: Int) {
        var myModel = eventList.get(position)
        holder.eventTitleTv.text = myModel?.title
        holder.eventDateTv.text = myModel?.date.toString()
//        holder.updateEventBtn.setOnClickListener {
//            Toast.makeText(context, "Not implemented yet!", Toast.LENGTH_SHORT).show()
//        }
        //add for update when you implement it
        holder.deleteEventBtn.setOnClickListener {
            onDeleteClick(myModel)
        }
        holder.updateEventBtn.setOnClickListener {
            onUpdateClick(myModel)
        }
        holder.expandInfoBtn.setOnClickListener {
            onViewClick(myModel)
        }



        //alternate way to get all items to bind to holder
        //val item = items[position]
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var eventTitleTv:TextView
        var eventDateTv:TextView
        var updateEventBtn:Button
        var deleteEventBtn:Button
        var expandInfoBtn:Button

        init{
            eventTitleTv = itemView.findViewById(R.id.eventTitleTv)
            eventDateTv = itemView.findViewById(R.id.eventDateTv)
            updateEventBtn = itemView.findViewById(R.id.UpdateEventBtn)
            deleteEventBtn = itemView.findViewById(R.id.DeleteEventBtn)
            expandInfoBtn = itemView.findViewById(R.id.expandInfoBtn)
        }
    }
}