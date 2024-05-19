package com.example.valoranthelper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class VideoPreview(val imageResId: Int, val description: String, val videoResId: Int)

class VideoPreviewAdapter(
    private val context: Context,
    private val videoPreviewList: List<VideoPreview>,
    private val onItemClick: (VideoPreview) -> Unit
) : RecyclerView.Adapter<VideoPreviewAdapter.VideoPreviewViewHolder>() {

    class VideoPreviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.videoPreviewImage)
        val textView: TextView = itemView.findViewById(R.id.videoPreviewText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPreviewViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.video_preview_item, parent, false)
        return VideoPreviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoPreviewViewHolder, position: Int) {
        val videoPreview = videoPreviewList[position]
        holder.imageView.setImageResource(videoPreview.imageResId)
        holder.textView.text = videoPreview.description

        holder.itemView.setOnClickListener {
            onItemClick(videoPreview)
        }
    }

    override fun getItemCount(): Int = videoPreviewList.size
}
