package com.sunringenius.sunrinproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var deadlineTextView: TextView // 기한 TextView
    private lateinit var goalTextView: TextView // 목표 TextView
    private lateinit var reasonTextView: TextView // 이유 TextView
    private lateinit var tryAgainButton: Button // 다시하기 버튼
    private lateinit var resetGoalButton: Button // 목표 재설정하기 버튼
    private lateinit var resetButton: Button // 초기화 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // 레이아웃 파일 연결

        // TextView와 Button 초기화
        deadlineTextView = findViewById(R.id.deadlineTextView)
        goalTextView = findViewById(R.id.goalTextView)
        reasonTextView = findViewById(R.id.reasonTextView)
        tryAgainButton = findViewById(R.id.tryAgainButton)
        resetGoalButton = findViewById(R.id.resetGoalButton)
        resetButton = findViewById(R.id.resetButton) // 초기화 버튼 초기화

        // SharedPreferences에서 목표, 기한, 이유 불러오기
        val sharedPreferences: SharedPreferences = getSharedPreferences("GoalData", MODE_PRIVATE)
        val deadline = sharedPreferences.getString("deadline", "기한이 설정되지 않았습니다.")
        val goal = sharedPreferences.getString("goal", "목표가 설정되지 않았습니다.")
        val reason = sharedPreferences.getString("reason", "이유가 설정되지 않았습니다.")

        // TextView에 불러온 데이터 설정
        deadlineTextView.text = "기간: ${deadline}까지"
        goalTextView.text = "목표: $goal"
        reasonTextView.text = "이유: $reason"

        // 다시하기 버튼 클릭 리스너 설정
        tryAgainButton.setOnClickListener {
            // TellingPurpose Activity로 이동
            val intent = Intent(this, TellingPurpose::class.java)
            startActivity(intent)
            finish() // 현재 Activity 종료
        }

        // 목표 재설정하기 버튼 클릭 리스너 설정
        resetGoalButton.setOnClickListener {
            // GoalActivity로 이동하여 목표 재설정
            val intent = Intent(this, GoalActivity::class.java)
            startActivity(intent)
        }

        // 초기화 버튼 클릭 리스너 설정
        resetButton.setOnClickListener {
            // SharedPreferences 초기화
            val editor = sharedPreferences.edit()
            editor.clear() // 모든 데이터를 삭제
            editor.apply() // 변경사항 적용

            // 초기화 완료 메시지 표시
            Toast.makeText(this, "초기화 되었습니다.\n앱이 처음부터 실행됩니다.", Toast.LENGTH_SHORT).show()

            // SplashActivity로 이동하여 앱 처음부터 실행
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            finish() // 현재 Activity 종료
        }
    }
}