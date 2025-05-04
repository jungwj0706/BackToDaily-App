package com.sunringenius.sunrinproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView

class StimulationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stimulation)

        val messageTextView: TextView = findViewById(R.id.stimulationText)

        // 4초 후에 메시지를 표시
        Handler(Looper.getMainLooper()).postDelayed({
            messageTextView.text = "열심히 노력하면, 도박보다 더 많은 돈을 벌 수 있습니다."
            messageTextView.visibility = View.VISIBLE
        }, 4000)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val startButton: Button = findViewById(R.id.goToCardnews)
        startButton.setOnClickListener {
            val intent = Intent(this, CardNewsActivity::class.java)
            startActivity(intent)
        }
    }
}