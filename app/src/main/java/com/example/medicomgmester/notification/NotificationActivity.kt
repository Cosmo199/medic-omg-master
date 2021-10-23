package com.example.medicomgmester.notification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.medicomgmester.MenuActivity
import com.example.medicomgmester.R
import com.tommasoberlose.progressdialog.ProgressDialogFragment
import io.karn.notify.Notify
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.load_activity.*
import kotlinx.android.synthetic.main.view_action_bar.*
import java.text.SimpleDateFormat
import java.util.*

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        text_bar.text = "การแจ้งเตือน"
        setEvent()
    }

    private fun setEvent() {
        arrow_back.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        setAlarm.setOnClickListener {
            val timer = object: CountDownTimer(1000, 2000) {
                override fun onTick(millisUntilFinished: Long) {
                    ProgressDialogFragment.showProgressBar(this@NotificationActivity)
                }
                override fun onFinish() {
                    ProgressDialogFragment.hideProgressBar(this@NotificationActivity)
                }
            }
            timer.start()
            setTimeNotificationDefault()
        }
    }

     private fun setTimeNotificationDefault() {
        //preferencesTimeHolder
        val preferencesTimeHolder = getSharedPreferences("TIME_HOLDER", Context.MODE_PRIVATE)
        var getInsertDate: String? = preferencesTimeHolder?.getString("dateInsert", "noDate")
        var getInsertTime: String? = preferencesTimeHolder?.getString("timeInsert", "noTime")
        var getOutDate: String? = preferencesTimeHolder?.getString("dateOut", "noDate")
        var getOutTime: String? = preferencesTimeHolder?.getString("timeOut", "noTime")

        // Check getInsert
        if (getInsertDate.equals("noDate")) {
        }  else {
            var timeInMilliSeconds: Long = 0
            val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
            val date = sdf.parse(getInsertDate+ getInsertTime)
            timeInMilliSeconds = date.time
            Utils.setAlarm(this, timeInMilliSeconds)
        }

        //Check getOut
        if (getOutDate.equals("noDate")) {
        }  else {
            var timeInMilliSeconds2: Long = 0
            val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
            val date2 = sdf.parse(getOutDate+ getOutTime)
            timeInMilliSeconds2 = date2.time
            Utils.setAlarm2(this, timeInMilliSeconds2 )
        }

    }

    private fun setNotification() {
        Notify
            .with(this)
            .content { // this: Payload.Content.Default
                title = "แจ้งเตือนรายการนัด"
                text = "นัดพบหมอ"
            }
            .show()
    }


}