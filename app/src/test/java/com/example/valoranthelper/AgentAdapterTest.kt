package com.example.valoranthelper

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class AgentAdapterTest {

    private lateinit var agentAdapter: AgentAdapter
    private val agents = listOf(
        Agent(R.drawable.kayo_icon, "KAY/O"),
        Agent(R.drawable.jett_icon, "Jett")
    )

    @Before
    fun setUp() {
        agentAdapter = AgentAdapter(agents)
    }

    @Test
    fun getItemCount_returnsCorrectItemCount() {
        val itemCount = agentAdapter.itemCount
        assertEquals(2, itemCount)
    }

    @Test
    fun onBindViewHolder_setsCorrectAgentData() {
        // Создание мок-контекста
        val context = mock(Context::class.java)
        val inflater = mock(LayoutInflater::class.java)
        `when`(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater)

        val parent = mock(ViewGroup::class.java)
        val itemView = mock(android.view.View::class.java)
        val agentIcon = mock(ImageView::class.java)
        val agentName = mock(TextView::class.java)

        `when`(inflater.inflate(R.layout.agent_list_item, parent, false)).thenReturn(itemView)
        `when`(itemView.findViewById<ImageView>(R.id.agentIcon)).thenReturn(agentIcon)
        `when`(itemView.findViewById<TextView>(R.id.agentName)).thenReturn(agentName)

        val viewHolder = AgentAdapter.AgentViewHolder(itemView)

        agentAdapter.onBindViewHolder(viewHolder, 0)

        `when`(agentName.text).thenReturn("KAY/O")
        `when`(agentIcon.tag).thenReturn(R.drawable.kayo_icon)

        assertEquals("KAY/O", viewHolder.agentName.text)
        assertEquals(R.drawable.kayo_icon, viewHolder.agentIcon.tag)
    }
}
