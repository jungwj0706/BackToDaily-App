package com.sunringenius.sunrinproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GoalActivity : AppCompatActivity() {
    private lateinit var deadlineEditText: EditText
    private lateinit var goalEditText: EditText
    private lateinit var reasonEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal) // 레이아웃 파일 연결

        // EditText와 Button 초기화
        deadlineEditText = findViewById(R.id.deadlineEditText)
        goalEditText = findViewById(R.id.goalEditText)
        reasonEditText = findViewById(R.id.reasonEditText)
        saveButton = findViewById(R.id.saveGoalButton)

        // 저장 버튼 클릭 리스너 설정
        saveButton.setOnClickListener {
            saveGoal() // 목표 저장 함수 호출
        }
    }

    private fun saveGoal() {
        // EditText에서 입력값 가져오기
        val deadline = deadlineEditText.text.toString()
        val goal = goalEditText.text.toString()
        val reason = reasonEditText.text.toString()

        // 입력값 유효성 검사
        if (deadline.isEmpty() || goal.isEmpty() || reason.isEmpty()) {
            Toast.makeText(this, "목표를 다 입력해주세요", Toast.LENGTH_SHORT).show()
            return // 입력이 비어있으면 함수 종료
        }

        // SharedPreferences를 사용하여 목표 저장
        val sharedPreferences = getSharedPreferences("GoalData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("deadline", deadline)
        editor.putString("goal", goal)
        editor.putString("reason", reason)
        editor.apply() // 변경사항 적용

        // MainActivity로 이동
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // 현재 Activity 종료
    }
}