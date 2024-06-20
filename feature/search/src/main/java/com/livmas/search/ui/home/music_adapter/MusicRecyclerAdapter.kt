package com.livmas.search.ui.home.music_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.livmas.air_tikets.R
import com.livmas.air_tikets.databinding.MusicRecyclerItemBinding

internal class MusicRecyclerAdapter(
    private val data: List<MusicItemModel>
): RecyclerView.Adapter<MusicRecyclerAdapter.ViewHolder>() {
    internal class ViewHolder(private val itemViewBinding: MusicRecyclerItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
            fun bind(item: MusicItemModel) {
                itemViewBinding.apply {
                    sivTripCover.setImageResourceById(item.id)
                    tvTitle.text = item.title
                    tvDestination.text = item.destination
                    tvPrice.text = root.context.getPriceString(item.price)
                }
            }

        private fun ImageView.setImageResourceById(id: Int) {
            setImageResource(
                when(id) {
                    0 -> R.drawable.image_dora
                    1 -> R.drawable.image_socrat_i_lera
                    2 -> R.drawable.image_lampabikt
                    else -> R.drawable.image_dora
                }
            )
        }
        private fun Context.getPriceString(price: Int) =
            getString(com.livmas.ui.R.string.pattern_from_price, price.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = MusicRecyclerItemBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =
        data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}