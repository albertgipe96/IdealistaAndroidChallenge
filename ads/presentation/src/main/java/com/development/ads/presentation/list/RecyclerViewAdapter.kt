package com.development.ads.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.RecyclerView
import com.development.ads.domain.model.AdData
import com.development.ads.presentation.components.AdCard
import com.development.ui.R

class RecyclerViewAdapter(private val itemList: List<AdData>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val composeItemView: ComposeView = itemView.findViewById(R.id.item_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.composeItemView.setContent {
            AdCard(
                adData = currentItem,
                onCardClick = { listener.onItemClick(currentItem) }
            )
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}

interface OnItemClickListener {
    fun onItemClick(item: AdData)
}