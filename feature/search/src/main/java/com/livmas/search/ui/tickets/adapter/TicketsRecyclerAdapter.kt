package com.livmas.search.ui.tickets.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.TicketItemLayoutBinding
import com.livmas.utils.DateTimeStringifier

internal class TicketsRecyclerAdapter(
    private val context: Context,
    private val data: List<TicketModel>
): RecyclerView.Adapter<TicketsRecyclerAdapter.ViewHolder>() {
    private val stringifier = DateTimeStringifier()
    inner class ViewHolder(private val binding: TicketItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TicketModel) {
            binding.apply {
                tvPrice.text = context.resources.getString(
                    com.livmas.ui.R.string.pattern_price, item.price.toString()
                )

                tvEndPoint.text = item.endCity
                tvStartPoint.text = item.startCity

                tvEndTime.text = stringifier.stringifyTime(item.endTime)
                tvStartTime.text = stringifier.stringifyTime(item.startTime)

                tvTimeInTrip.text = context.resources.getString(R.string.time_in_trip, item.timeInTrip.toString())
                tvTransfer.text = context.resources.getString(
                    if (item.hasTransfer)
                        R.string.no_transfers
                    else
                        R.string.has_transfers
                )

                if (item.badge.isNullOrEmpty())
                    tvBadge.visibility = View.GONE
                else
                    tvBadge.text = item.badge
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