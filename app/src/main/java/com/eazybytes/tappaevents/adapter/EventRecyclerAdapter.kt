package com.eazybytes.tappaevents.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.eazybytes.tappaevents.R
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
        }
        "${eventItemList[position].eventTitle}\t${eventItemList[position].eventHost}".also { holder.binding.textView.text = it }
        holder.binding.textView2.text = eventItemList[position].eventHost
        "${eventItemList[position].eventLocation}, ${eventItemList[position].eventDateTime}".also { holder.binding.textView3.text = it }

        val posterThumb = eventItemList[position].posterThumb

        val imagePath = posterThumb.substringAfter("/events/posters/").substringBefore("?itok=")

        val imageUrl = "https://www.kenyamoja.com/sites/default/files/styles/width_500px/public/events/posters/${imagePath}"


        holder.binding.imageView.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            error(R.drawable.ic_launcher_foreground)
        }
    }

    override fun getItemCount() = eventItemList.size

}
