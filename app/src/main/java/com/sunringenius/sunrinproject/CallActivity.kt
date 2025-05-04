package com.sunringenius.sunrinproject

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class CallActivity : AppCompatActivity() {
    private var notificationId = 1
    private lateinit var viewNextMessageButton: Button
    private lateinit var goToTreatmentButton: Button
    private lateinit var userName: String // 사용자 이름 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        viewNextMessageButton = findViewById(R.id.viewNextMessageButton)
        goToTreatmentButton = findViewById(R.id.goToTreatmentButton)

        // Intent에서 사용자 이름 받기
        userName = intent.getStringExtra("USER_NAME") ?: "기본값" // 기본값 설정

        Toast.makeText(this, "허용을 눌러주세요", Toast.LENGTH_SHORT).show()

        // 첫 번째 알림 표시
        showNotification(this, notificationId, userName)  // userName 추가

        viewNextMessageButton.setOnClickListener {
            notificationId++
            if (notificationId <= 6) {
                showNotification(this, notificationId, userName)  // userName 추가
            }
            if (notificationId == 6) {
                viewNextMessageButton.visibility = View.GONE
                goToTreatmentButton.visibility = View.VISIBLE
            }
        }

        goToTreatmentButton.setOnClickListener {
            val intent = Intent(this, StimulationActivity::class.java)
            startActivity(intent)
        }

        // Android 13 이상에서 알림 권한 요청
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        } else {
            showNotification(this, notificationId, userName)  // userName 추가
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showNotification(this, notificationId, userName)  // userName 추가
            }
        }
    }
}
