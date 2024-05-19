package com.example.valoranthelper

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var quoteTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val agents = listOf(
            Agent(R.drawable.kayo_icon, "KAY/O"),
            Agent(R.drawable.jett_icon, "Jett"),
            Agent(R.drawable.cypher_icon, "Cypher"),
            Agent(R.drawable.pheonix_icon, "Pheonix"),
        )



        val recyclerView: RecyclerView = findViewById(R.id.agentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AgentAdapter(agents)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            val token = task.result

            val msg = "FCM Registration Token: $token"
            Log.d("FCM", msg)
        }

        quoteTextView = findViewById(R.id.quoteTextView)
        getRandomQuote()
    }

    private fun getRandomQuote() {
        RetrofitInstance.api.getRandomQuote().enqueue(object : Callback<Quote> {
            override fun onResponse(call: Call<Quote>, response: Response<Quote>) {
                if (response.isSuccessful) {
                    val quote = response.body()
                    quote?.let {
                        quoteTextView.text = "\"${it.content}\" - ${it.author}"
                    }
                }
            }

            override fun onFailure(call: Call<Quote>, t: Throwable) {
                quoteTextView.text = "Failed to load quote"
            }
        })
    }
}
