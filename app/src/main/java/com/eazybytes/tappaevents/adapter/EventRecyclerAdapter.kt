package com.eazybytes.tappaevents.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.eazybytes.tappaevents.R
import com.eazybytes.tappaevents.R.color.BG
import com.eazybytes.tappaevents.databinding.EventitemcardBinding
import com.eazybytes.domain.model.EventItem

class EventRecyclerAdapter(private val eventItemList: List<EventItem>): RecyclerView.Adapter<EventRecyclerAdapter.EventViewHolder>() {
    class EventViewHolder(val binding: EventitemcardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        val binding = EventitemcardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        holder.binding.cardView.setOnClickListener {
            holder.binding.textView.maxLines = 100
            holder.binding.textView.setTextColor(BG)
        }
        holder.binding.textView.text = eventItemList[position].description

        holder.binding.imageView.load(eventItemList[position].src) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            error(R.drawable.ic_launcher_foreground)
        }
    }

    override fun getItemCount() = eventItemList.size

}
