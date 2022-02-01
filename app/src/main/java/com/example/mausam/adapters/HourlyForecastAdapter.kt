package com.example.mausam.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mausam.R
import com.example.mausam.network.Hourly
import kotlin.math.floor

class HourlyForecastAdapter(private val hourlyDataList: List<Hourly>) : RecyclerView.Adapter<HourlyForecastViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hourly_forecast_item,parent,false)
        return HourlyForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
        val item = hourlyDataList[position]
        holder.weatherText.text = item.weather[0].description
        holder.temperatureText.text = floor(item.temperature).toString()
    }

    override fun getItemCount(): Int {
        return 10
    }
}

class HourlyForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val imageView:ImageView = itemView.findViewById(R.id.weatherImage)
    val weatherText:TextView = itemView.findViewById(R.id.weather)
    val temperatureText:TextView = itemView.findViewById(R.id.temperature)
}