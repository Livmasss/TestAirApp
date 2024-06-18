package com.livmas.air_tikets.ui.flights.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.FlightItemLayoutBinding
import java.util.Calendar

internal class FlightsAdapter(
    private val context: Context,
    private val data: List<FlightModel>
): RecyclerView.Adapter<FlightsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: FlightItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FlightModel) {
            binding.ivImage.setImageDrawable(item.defineFlightImage())
            binding.tvAirCompany.text = item.company
            binding.tvPrice.text = context.resources.getString(R.string.pattern_open_ticket, item.price.toString())
            binding.tvFlightTimes.text = item.times.joinToString(" ") {
                "${it.get(Calendar.HOUR)}:${it.get(Calendar.MINUTE)} "
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FlightItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    private fun FlightModel.defineFlightImage(): Drawable =
        Color.parseColor(
            when (flightId) {
                0 -> "#FF5E5E"
                1 -> "#2261BC"
                else -> "#FFFFFF"
            }
        ).toDrawable()
}