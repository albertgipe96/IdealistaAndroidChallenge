package com.development.ads.presentation.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.RecyclerView
import coil.compose.AsyncImage
import com.development.ui.R

class CarouselImageAdapter(private val images: List<String>) : RecyclerView.Adapter<CarouselImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewCompose: ComposeView = itemView.findViewById(R.id.image_view_compose)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageViewCompose.setContent {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = images[position],
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}