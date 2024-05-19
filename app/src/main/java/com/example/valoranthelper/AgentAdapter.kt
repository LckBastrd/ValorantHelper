package com.example.valoranthelper

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Agent(val icon: Int, val name: String)

class AgentAdapter(private val agentList: List<Agent>) : RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    class AgentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val agentIcon: ImageView = itemView.findViewById(R.id.agentIcon)
        val agentName: TextView = itemView.findViewById(R.id.agentName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.agent_list_item, parent, false)
        return AgentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val currentAgent = agentList[position]
        holder.agentIcon.setImageResource(currentAgent.icon)
        holder.agentIcon.tag = currentAgent.icon
        holder.agentName.text = currentAgent.name

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MapSelectionActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = agentList.size
}
