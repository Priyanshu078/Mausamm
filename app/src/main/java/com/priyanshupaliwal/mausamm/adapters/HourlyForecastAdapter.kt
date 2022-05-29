package com.priyanshupaliwal.mausamm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.priyanshupaliwal.mausamm.R
import com.priyanshupaliwal.mausamm.network.Hourly
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor
class HourlyForecastAdapter(val context: Context, private val hourlyDataList: List<Hourly>) : RecyclerView.Adapter<HourlyForecastViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hourly_forecast_item,parent,false)
        return HourlyForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
        val item = hourlyDataList[position]
        Glide.with(context).load("https://openweathermap.org/img/w/${item.weather[0].icon}.png").into(holder.imageView)
        holder.weatherText.text = item.weather[0].description
        holder.temperatureText.text = context.resources.getString(R.string.temperature,floor(item.temperature).toInt().toString())
        holder.timeTextView.text = getTime(item.date)
    }

    private fun getTime(timeStamp: Long): String {
        val timestamp: Long = timeStamp
        val timeD = Date(timestamp * 1000)
        return SimpleDateFormat("hh:mm a").format(timeD)
    }

    override fun getItemCount(): Int {
        return hourlyDataList.size
    }
}

class HourlyForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val imageView:ImageView = itemView.findViewById(R.id.weatherImage)
    val timeTextView:TextView = itemView.findViewById(R.id.time)
    val weatherText:TextView = itemView.findViewById(R.id.weather)
    val temperatureText:TextView = itemView.findViewById(R.id.temperature)
}