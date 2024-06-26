package com.livmas.search.ui.destinations.adapter

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.livmas.air_tikets.databinding.DestinationItemLayoutBinding

internal class RecommendationsAdapter(
    private val data: List<RecommendationModel>,
    private val onItemSelectedListener: OnClickListener
): RecyclerView.Adapter<RecommendationsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: DestinationItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecommendationModel) {
            binding.apply{
                tvTitle.text = item.title
                tvSubtitle.text = item.subTitle
                ivImage.setImageResource(item.imageId)

                root.setOnClickListener(onItemSelectedListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DestinationItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}