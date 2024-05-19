package com.example.valoranthelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MapSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_selection)

        val mapRecyclerView = findViewById<RecyclerView>(R.id.mapRecyclerView)
        mapRecyclerView.layoutManager = LinearLayoutManager(this)
        mapRecyclerView.adapter = MapAdapter(getMaps()) { map ->
            val intent = Intent(this, MapDetailActivity::class.java)
            intent.putExtra("mapName", map.name)
            startActivity(intent)
        }

        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun getMaps(): List<Map> {
        return listOf(
            Map(R.drawable.ascent_map, "Ascent"),
            Map(R.drawable.bind_map, "Bind")
        )
    }
}
