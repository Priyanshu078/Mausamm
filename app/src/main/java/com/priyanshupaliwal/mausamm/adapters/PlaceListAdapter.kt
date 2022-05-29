package com.priyanshupaliwal.mausamm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.priyanshupaliwal.mausamm.R
import com.priyanshupaliwal.mausamm.fragments.BasicWeatherInfoFragmentDirections
import com.priyanshupaliwal.mausamm.fragments.SearchValue
import com.google.android.material.card.MaterialCardView

class PlaceListAdapter(val list: List<String>) :RecyclerView.Adapter<PlaceListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_list_item,parent,false)
        return PlaceListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceListViewHolder, position: Int) {
        val item = list[position]
        holder.placeTextView.text = item
        holder.placeCard.setOnClickListener {
            SearchValue.cityName = item
            holder.itemView.findNavController().navigate(
                BasicWeatherInfoFragmentDirections.actionBasicWeatherInfoFragmentToBasicWeatherInfoFragment()
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class PlaceListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val placeCard:MaterialCardView = itemView.findViewById(R.id.placeCard)
    val placeTextView:TextView = itemView.findViewById(R.id.placeName)
}