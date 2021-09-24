package com.example.medicomgmester.notification

import android.app.TimePickerDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicomgmester.MenuActivity
import com.example.medicomgmester.R
import com.example.medicomgmester.model.ListAppointment
import com.example.medicomgmester.model.RememberToken
import com.example.medicomgmester.network.ApiService
import com.example.medicomgmester.ui.home.adapter.AdapterListHome
import com.tommasoberlose.progressdialog.ProgressDialogFragment
import io.karn.notify.Notify
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.load_activity.*
import kotlinx.android.synthetic.main.view_action_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        text_bar.text = "การแจ้งเตือน"
        setEvent()
        // setTime()
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

    private fun setTime() {
        /*
        var timeInMilliSeconds: Long = 0
        var timeInMilliSeconds2: Long = 0
        var saveTextTime: String
        var saveTextTime2: String

        val receiver = ComponentName(applicationContext, BootCompleteReceiver::class.java)

        applicationContext.packageManager?.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        //preferencesTimePickerDialog
        val preferences = this.getSharedPreferences("TIME", Context.MODE_PRIVATE)
        var getTime: String? = preferences?.getString("time", "เลือกเวลาแจ้งเตือน")
        var getTime2: String? = preferences?.getString("time2", "เลือกเวลาแจ้งเตือน")

        startTimeText.text = getTime
        startTimeText2.text = getTime2


        startTimeText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minuteOfHour ->

                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minuteOfHour)
                    calendar.set(Calendar.SECOND, 0)

                    val amPm = if (hourOfDay < 12) "am" else "pm"
                    val formattedTime = String.format("%02d:%02d %s", hourOfDay, minuteOfHour, amPm)
                    startTimeText.text = formattedTime
                    saveTextTime = startTimeText.text as String

                    val editor = getSharedPreferences("TIME", MODE_PRIVATE).edit()
                    editor.putString("time", saveTextTime)
                    editor.apply()

                    val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                    val formattedDate = sdf.format(calendar.time)
                    val date = sdf.parse(formattedDate)
                    timeInMilliSeconds = date.time
                }, hour, minute, false
            )


            timePickerDialog.show()
        }

        startTimeText2.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minuteOfHour ->

                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minuteOfHour)
                    calendar.set(Calendar.SECOND, 0)

                    val amPm = if (hourOfDay < 12) "am" else "pm"
                    val formattedTime = String.format("%02d:%02d %s", hourOfDay, minuteOfHour, amPm)
                    startTimeText2.text = formattedTime
                    saveTextTime2 = startTimeText2.text as String

                    //preferencesTimePickerDialog
                    val editor = getSharedPreferences("TIME", MODE_PRIVATE).edit()
                    editor.putString("time2", saveTextTime2)
                    editor.apply()

                    val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                    val formattedDate = sdf.format(calendar.time)
                    val date = sdf.parse(formattedDate)
                    timeInMilliSeconds2 = date.time
                }, hour, minute, false
            )

            timePickerDialog.show()
        }


        setAlarm.setOnClickListener {
            //timeInMilliSeconds.toInt() == 0
            //timeInMilliSeconds.toInt() != 0
            if (startTimeText.text.equals("เลือกเวลาแจ้งเตือน")) {
                Toast.makeText(this, "กรุณาเลือกเวลาการแจ้งเตือน 1", Toast.LENGTH_LONG).show()

            } else if (startTimeText.text.equals("เลือกเวลาแจ้งเตือน")) {
                Toast.makeText(this, "กรุณาเลือกเวลาแจ้งเตือน 2", Toast.LENGTH_LONG).show()
            } else {
                val sharedPref = this.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                    ?: return@setOnClickListener
                with(sharedPref.edit()) {
                    putLong("timeInMilli", timeInMilliSeconds)
                    putLong("timeInMilli2", timeInMilliSeconds2)
                    apply()
                }
                Utils.setAlarm(this, timeInMilliSeconds)
                Utils.setAlarm2(this, timeInMilliSeconds2)
                Toast.makeText(this, "ตั้งเวลาการแจ้งเตือนสำเร็จ", Toast.LENGTH_LONG).show()
            }
        }

         */
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