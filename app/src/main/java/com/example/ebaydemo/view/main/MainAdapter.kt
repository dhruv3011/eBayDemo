package com.example.ebaydemo.view.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ebaydemo.R
import com.example.ebaydemo.api.dataClass.Earthquake
import com.example.ebaydemo.databinding.ItemEarthquakeBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var onItemClick: ((Earthquake?) -> Unit)? = null
    var earthquake = mutableListOf<Earthquake?>()

    fun setEarthquakeList(earthquakes: List<Earthquake?>?) {
        if (earthquakes != null) {
            if (earthquakes.size > 0) {
                this.earthquake = earthquakes.toMutableList()
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEarthquakeBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val earthquakeItem = earthquake[position]
        holder.binding.tvLatLng.text =
            "Lattitude: ${earthquakeItem?.lat}, Longitude: ${earthquakeItem?.lng}"
        holder.binding.tvMagnitude.text = "Magnitude: ${earthquakeItem?.magnitude}"

        if (earthquakeItem?.magnitude != null) {
            if (earthquakeItem.magnitude >= 8.0) {
                holder.binding.cardViewItem.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.binding.cardViewItem.context,
                        R.color.magnitude_item_bg
                    )
                )
            } else {
                holder.binding.cardViewItem.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.binding.cardViewItem.context,
                        R.color.item_bg
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return earthquake.size
    }

    inner class MainViewHolder(val binding: ItemEarthquakeBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(earthquake[adapterPosition])
            }
        }
    }
}

