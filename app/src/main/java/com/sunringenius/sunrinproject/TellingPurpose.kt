package com.sunringenius.sunrinproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView // TextView import 추가
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class TellingPurpose : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 전체 화면 설정
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_telling_purpose)

        // WindowInsetsCompat 사용하여 시스템 바 크기 조정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val startButton: Button = findViewById(R.id.startButton)
        startButton.setOnClickListener {
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
        }
    }
}


