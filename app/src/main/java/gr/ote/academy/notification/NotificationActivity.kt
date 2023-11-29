package gr.ote.academy.notification

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import gr.ote.academy.R
import gr.ote.academy.webview.ChromeActivity

class NotificationActivity : AppCompatActivity() {
    private val CHANNEL_ID = "ote"
    private val NOTIFICATION_ID = 9000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val pendingIntent = PendingIntent.getActivity(this,9100, Intent(this,ChromeActivity::class.java),0)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("")
            .setContentIntent(pendingIntent)

        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notificationBuilder.build())
    }
}