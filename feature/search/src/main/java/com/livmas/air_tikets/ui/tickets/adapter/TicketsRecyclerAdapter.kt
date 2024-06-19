package com.livmas.air_tikets.ui.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.livmas.air_tikets.databinding.TicketItemLayoutBinding
import com.livmas.utils.DateTimeStringifier

internal class TicketsRecyclerAdapter(
    private val data: List<TicketModel>
): RecyclerView.Adapter<TicketsRecyclerAdapter.ViewHolder>() {
    private val stringifier = DateTimeStringifier()
    inner class ViewHolder(private val binding: TicketItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TicketModel) {
            binding.apply {
                this.tvEndPoint.text = item.endCity
                this.tvStartPoint.text = item.startCity

                this.tvEndTime.text = stringifier.stringifyTime(item.endTime)
                this.tvStartTime.text = stringifier.stringifyTime(item.startTime)

                this.tvPrice.text = item.price.toString()
                this.tvTimeInTrip.text = item.timeInTrip.toString()

                this.tvTransfer.text = item.hasTransfer.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TicketItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}