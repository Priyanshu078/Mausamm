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
import com.priyanshupaliwal.mausamm.network.Daily
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class WeeklyForecastAdapter(val context: Context, private val weeklyForecastDataList:List<Daily>) : RecyclerView.Adapter<WeeklyForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weekly_forecast_item,parent,false)
        return WeeklyForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeeklyForecastViewHolder, position: Int) {
        val item = weeklyForecastDataList[position]
        Glide.with(context).load("https://openweathermap.org/img/w/${item.weather[0].icon}.png").into(holder.weeklyWeatherImage)
        holder.date.text = getTime(item.date)
        holder.weather.text = item.weather[0].main
        holder.maxTemp.text = context.resources.getString(R.string.temperature,floor(item.temperature.max).toInt().toString())
        holder.minTemp.text = context.resources.getString(R.string.temperature,floor(item.temperature.min).toInt().toString())
    }

    private fun getTime(timeStamp: Long): String {
        val timestamp: Long = timeStamp
        val timeD = Date(timestamp * 1000)
        return SimpleDateFormat("dd MMMM yyyy").format(timeD)
    }

    override fun getItemCount(): Int {
        return weeklyForecastDataList.size
    }
}

class WeeklyForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val weeklyWeatherImage: ImageView = itemView.findViewById(R.id.weekLyWeatherImage)
    val date: TextView = itemView.findViewById(R.id.date)
    val weather:TextView = itemView.findViewById(R.id.weeklyWeather)
    val maxTemp:TextView = itemView.findViewById(R.id.maxTemp)
    val minTemp:TextView = itemView.findViewById(R.id.minTemp)
}