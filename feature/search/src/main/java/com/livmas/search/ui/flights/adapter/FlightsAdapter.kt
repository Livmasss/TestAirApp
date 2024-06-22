package com.livmas.search.ui.flights.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.FlightItemLayoutBinding
import com.livmas.utils.DateTimeStringifier
import com.livmas.utils.MyDecimalFormatter

internal class FlightsAdapter(
    private val context: Context,
    private var data: List<FlightModel>,
    private val dateTimeStringifier: DateTimeStringifier
): RecyclerView.Adapter<FlightsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: FlightItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FlightModel, index: Int) {
            binding.ivImage.setImageDrawable(defineFlightImage(index))
            binding.tvAirCompany.text = item.company
            binding.tvPrice.text = context.resources.getString(R.string.pattern_open_ticket, MyDecimalFormatter.formatPrice(item.price))
            binding.tvFlightTimes.text = item.times.joinToString(" ") {
                dateTimeStringifier.stringifyTime(it)
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
        holder.bind(data[position], position)
    }

    private fun defineFlightImage(index: Int): Drawable =
        Color.parseColor(
            when (index) {
                0 -> "#FF5E5E"
                1 -> "#2261BC"
                else -> "#FFFFFF"
            }
        ).toDrawable()

    fun updateData(newData: List<FlightModel>) {
        data = newData
        notifyDataSetChanged()
    }
}