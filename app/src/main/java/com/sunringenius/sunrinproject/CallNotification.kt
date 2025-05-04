package com.sunringenius.sunrinproject

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

fun showNotification(context: Context, notificationId: Int, userName: String) {
    val channelId = "my_channel_id"
    val channelName = "My Notification Channel"

    // 알림 채널 생성 (안드로이드 8.0 이상)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = "This is my notification channel"
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    // 각 알림에 대한 제목과 내용을 정의
    val notificationTitles = arrayOf(
        "채권자", "사채업자", "도박앱",
        "도박앱", "엄마", "아빠"
    )
    val notificationContents = arrayOf(
        "이달 안으로 결제를 해주셔야 합니다. 더 이상 연기할 수 없습니다.",
        "미납 금액이 계속 쌓이고 있습니다. 심각한 상황이니 반드시 연락 바랍니다.",
        "한 번의 베팅으로 모든 것을 얻을 수 있는 기회! 지금 바로 참여하세요!",
        "가입하고 첫 베팅 시 100% 보너스를 드려요! 당신의 운을 시험해보세요.",
        "$userName 아, 요즘 많이 힘든 것 같아... 집에 대출 관련 편지가 많이 왔어. 무슨 일인지 꼭 이야기해줘.",
        "$userName 아, 네가 요즘 잘 지내는지 걱정돼. 도박 아직 못 끊었니? 가족을 위해서라도 끊자꾸나."
    )

    // 알림 빌더 설정
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.logo)
        .setContentTitle(notificationTitles[notificationId - 1])  // 알림 제목
        .setContentText(notificationContents[notificationId - 1])  // 알림 내용
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    // 알림 표시
    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notify(notificationId, builder.build())
    }
}
