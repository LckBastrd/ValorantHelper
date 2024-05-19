package com.example.valoranthelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Map(val icon: Int, val name: String)

class MapAdapter(
    private val mapList: List<Map>,
    private val onClick: (Map) -> Unit
) : RecyclerView.Adapter<MapAdapter.MapViewHolder>() {

    class MapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mapIcon: ImageView = itemView.findViewById(R.id.mapIcon)
        val mapName: TextView = itemView.findViewById(R.id.mapName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.map_list_item, parent, false)
        return MapViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        val currentMap = mapList[position]
        holder.mapIcon.setImageResource(currentMap.icon)
        holder.mapName.text = currentMap.name
        holder.itemView.setOnClickListener { onClick(currentMap) }
    }

    override fun getItemCount() = mapList.size
}
