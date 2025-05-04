package com.sunringenius.sunrinproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // SharedPreferences에서 목표 설정 여부 확인
        val sharedPreferences: SharedPreferences = getSharedPreferences("GoalData", MODE_PRIVATE)
        val goal = sharedPreferences.getString("goal", null)

        // 2초 후에 다음 화면으로 이동
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (goal != null) {
                // 목표 설정이 존재하면 MainActivity로 이동
                Intent(this, MainActivity::class.java)
            } else {
                // 목표 설정이 없으면 TellingPurpose Activity로 이동
                Intent(this, TellingPurpose::class.java)
            }
            startActivity(intent)
            finish() // SplashActivity 종료
        }, 2000)
    }
}
