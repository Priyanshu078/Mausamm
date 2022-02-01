package com.example.mausam.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mausam.R
import com.example.mausam.network.Daily

class WeeklyForecastAdapter(private val weeklyForecastDataList:List<Daily>) : RecyclerView.Adapter<WeeklyForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hourly_forecast_item,parent,false)
        return WeeklyForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeeklyForecastViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}

class WeeklyForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}