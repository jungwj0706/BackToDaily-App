package com.sunringenius.sunrinproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        val submitButton = findViewById<Button>(R.id.submitButton)
        val nameInput = findViewById<EditText>(R.id.nameInput)

        submitButton.setOnClickListener {
            val userName = nameInput.text.toString().trim()

            if (userName.isEmpty()) {
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                // SharedPreferences에 사용자 이름 저장
                val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("USER_NAME", userName) // USER_NAME에 저장
                editor.apply()

                // CallActivity로 이동
                val intent = Intent(this, CallActivity::class.java)
                intent.putExtra("USER_NAME", userName) // userName을 Intent에 추가
                startActivity(intent)
            }
        }
    }
}
