package com.example.valoranthelper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MapDetailActivity : AppCompatActivity() {

    private lateinit var mapImageView: ImageView
    private lateinit var videoPreviewRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_detail)

        mapImageView = findViewById(R.id.miniMap)
        videoPreviewRecyclerView = findViewById(R.id.videoPreviewRecyclerView)

        val areaButton1 = findViewById<Button>(R.id.zoneButton1)
        val areaButton2 = findViewById<Button>(R.id.zoneButton2)
        val areaButton3 = findViewById<Button>(R.id.zoneButton3)
        val areaButton4 = findViewById<Button>(R.id.zoneButton4)

        val mapName = intent.getStringExtra("mapName")
        when (mapName) {
            "Ascent" -> mapImageView.setImageResource(R.drawable.ascent_minimap)
            "Bind" -> mapImageView.setImageResource(R.drawable.bind_minimap)
        }

        areaButton1.setOnClickListener {
            showVideoPreviews(1)
        }
        areaButton2.setOnClickListener {
            showVideoPreviews(2)
        }
        areaButton3.setOnClickListener {
            showVideoPreviews(3)
        }
        areaButton4.setOnClickListener {
            showVideoPreviews(4)
        }

        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun showVideoPreviews(zone: Int) {
        videoPreviewRecyclerView.visibility = View.VISIBLE

        val videoPreviews = when (zone) {
            1 -> listOf(
                VideoPreview(R.drawable.video_preview_placeholder_2, "lineup1", R.raw.asc_2),
            )
            2 -> listOf(
                VideoPreview(R.drawable.video_preview_placeholder_3, "lineup2", R.raw.asc_3),
            )
            3 -> listOf(
                VideoPreview(R.drawable.video_preview_placeholder_4, "lineup3", R.raw.asc_4),
            )
            4 -> listOf(
                VideoPreview(R.drawable.video_preview_placeholder_1, "lineup4", R.raw.asc_1),
            )
            else -> emptyList()
        }

        videoPreviewRecyclerView.layoutManager = LinearLayoutManager(this)
        videoPreviewRecyclerView.adapter = VideoPreviewAdapter(this, videoPreviews) { videoPreview ->
            playVideo(videoPreview.videoResId)
        }
    }

    private fun playVideo(videoResId: Int) {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra("videoResId", videoResId)
        startActivity(intent)
    }
}
