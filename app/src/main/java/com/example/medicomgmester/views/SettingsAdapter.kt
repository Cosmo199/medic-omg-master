package com.example.medicomgmester.views

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.medicomgmester.R

import com.example.medicomgmester.handler.IOHandler
import com.example.medicomgmester.models.EventDate
import kotlinx.android.synthetic.main.card_view_settings_notification_birthday.view.*
import java.util.*

class SettingsAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemList = listOf(1)

    class SettingCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class SettingExtraCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        return this.itemList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cardView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_settings_notification_annual, parent, false)
        return SettingCardViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {

            // ANNUAL NOTIFICATION SETTINGS
            1 -> {
                holder.itemView.tv_settings_title.text =
                    context.getText(R.string.event_type_annual_event_sub)

                val isEnabled =
                    IOHandler.getBooleanFromKey(IOHandler.SharedPrefKeys.key_isNotificationOnAnnual)!!
                if (!isEnabled) changeEnabledStatus(holder.itemView, isEnabled)

                holder.itemView.sw_settings_notifcations.isChecked = isEnabled
                holder.itemView.sw_settings_notifcations.setOnCheckedChangeListener { _, isChecked ->
                    changeEnabledStatus(holder.itemView, isChecked)
                    IOHandler.writeSetting(
                        IOHandler.SharedPrefKeys.key_isNotificationOnAnnual,
                        isChecked
                    )
                }

                //sound switch
                holder.itemView.sw_settings_sound.isChecked =
                    IOHandler.getBooleanFromKey(IOHandler.SharedPrefKeys.key_isNotificationSoundOnAnnual)!!
                holder.itemView.sw_settings_sound.setOnCheckedChangeListener { _, isChecked ->
                    IOHandler.writeSetting(
                        IOHandler.SharedPrefKeys.key_isNotificationSoundOnAnnual,
                        isChecked
                    )
                }

                //vibration switch
                holder.itemView.sw_settings_vibration.isChecked =
                    IOHandler.getBooleanFromKey(IOHandler.SharedPrefKeys.key_isNotificationVibrationOnAnnual)!!
                holder.itemView.sw_settings_vibration.setOnCheckedChangeListener { _, isChecked ->
                    IOHandler.writeSetting(
                        IOHandler.SharedPrefKeys.key_isNotificationVibrationOnAnnual,
                        isChecked
                    )
                }

                //set notification time
                holder.itemView.tv_settings_notificaton_time_value.text =
                    IOHandler.getStringFromKey(IOHandler.SharedPrefKeys.key_strNotificationTimeAnnual)
                //set time picker dialog on click
                holder.itemView.tv_settings_notificaton_time_value.setOnClickListener {
                    showTimePickerDialog(
                        it as TextView,
                        IOHandler.SharedPrefKeys.key_strNotificationTimeAnnual
                    )
                }

                val notificationDateArray: BooleanArray = booleanArrayOf(
                    IOHandler.getBooleanFromKey(IOHandler.SharedPrefKeys.key_isRemindedDay_month_beforeAnnual)!!,
                    IOHandler.getBooleanFromKey(IOHandler.SharedPrefKeys.key_isRemindedDay_week_beforeAnnual)!!,
                    IOHandler.getBooleanFromKey(IOHandler.SharedPrefKeys.key_isRemindedDay_day_beforeAnnual)!!,
                    IOHandler.getBooleanFromKey(IOHandler.SharedPrefKeys.key_isRemindedDay_eventdayAnnual)!!
                )

                val constrLayoutNotificationDay =
                    holder.itemView.findViewById<ConstraintLayout>(R.id.constrLayout_settings_notification_day)
                //show checkbox dialog on click
                constrLayoutNotificationDay.setOnClickListener {
                    val alertDialogBuilder = AlertDialog.Builder(context)
                    alertDialogBuilder.setTitle(R.string.settings_title_notification_day)
                        .setPositiveButton(R.string.apply) { _, _ ->

                            holder.itemView.tv_settings_notification_day_value.text =
                                getNotificationDateValueStringFromBooleanArray(notificationDateArray)

                            IOHandler.writeSetting(
                                IOHandler.SharedPrefKeys.key_isRemindedDay_month_beforeAnnual,
                                notificationDateArray[0]
                            )
                            IOHandler.writeSetting(
                                IOHandler.SharedPrefKeys.key_isRemindedDay_week_beforeAnnual,
                                notificationDateArray[1]
                            )
                            IOHandler.writeSetting(
                                IOHandler.SharedPrefKeys.key_isRemindedDay_day_beforeAnnual,
                                notificationDateArray[2]
                            )
                            IOHandler.writeSetting(
                                IOHandler.SharedPrefKeys.key_isRemindedDay_eventdayAnnual,
                                notificationDateArray[3]
                            )
                        }
                        .setMultiChoiceItems(
                            arrayOf(
                                context.getText(R.string.tv_notification_interval_month),
                                context.getText(R.string.tv_notification_interval_week),
                                context.getText(R.string.tv_notification_interval_day),
                                context.getText(R.string.tv_notification_interval_eventday)
                            ),
                            notificationDateArray
                        ) { _, which, isChecked ->
                            notificationDateArray[which] = isChecked
                        }
                        .show()
                }

                var notificationLight =
                    IOHandler.getIntFromKey(IOHandler.SharedPrefKeys.key_notificationLightAnnual)!!
                val constrLayoutNotificationLight =
                    holder.itemView.findViewById<ConstraintLayout>(R.id.constraint_layout_settings_notification_light)
                //show checkbox dialog on click
                constrLayoutNotificationLight.setOnClickListener {
                    val alertDialogBuilder = AlertDialog.Builder(context)
                    alertDialogBuilder.setTitle(R.string.dialog_title_notification_light)
                        .setPositiveButton(R.string.apply) { _, _ ->

                            holder.itemView.tv_settings_notification_light_value.text =
                                getNotifcationLightValueFromInt(notificationLight)

                            IOHandler.writeSetting(
                                IOHandler.SharedPrefKeys.key_notificationLightAnnual,
                                notificationLight
                            )
                        }
                        .setSingleChoiceItems(
                            context.resources.getStringArray(R.array.light_modes),
                            notificationLight
                        ) { _: DialogInterface?, which: Int ->
                            notificationLight = which
                        }
                        .show()
                }

                holder.itemView.tv_settings_notification_light_value.text =
                    getNotifcationLightValueFromInt(notificationLight)

                holder.itemView.tv_settings_notification_day_value.text =
                    getNotificationDateValueStringFromBooleanArray(notificationDateArray)
            }

        }
    }

    private fun getNotificationDateValueStringFromBooleanArray(array: BooleanArray)
            : String {
        var reminderString = ""
        if (array[0]) {
            reminderString += "${context.getText(R.string.tv_notification_interval_month)}\n"
        }
        if (array[1]) {
            reminderString += "${context.getText(R.string.tv_notification_interval_week)}\n"
        }
        if (array[2]) {
            reminderString += "${context.getText(R.string.tv_notification_interval_day)}\n"
        }
        if (array[3]) {
            reminderString += "${context.getText(R.string.tv_notification_interval_eventday)}"
        }
        return reminderString
    }

    private fun getNotifcationLightValueFromInt(value: Int)
            : String {
        // 0 = no light
        // 1 = white light
        // 2 = red light
        // 3 = green light
        // 4 = blue light
        return context.resources.getStringArray(R.array.light_modes)[value]
    }

    private fun changeEnabledStatus(view: View, isEnabled: Boolean) {
        val constraintLayout =
            view.findViewById<ConstraintLayout>(R.id.constraintLayout_card_view_settings)

        // differentiate between the count of childs in the birthday constraint settings layout and the rest
        // the birthday settings card currently has 9 childs
        if (constraintLayout.childCount == 9) {
            for (i in 3 until constraintLayout.childCount) {
                if (isEnabled) {
                    constraintLayout.getChildAt(i).visibility = View.VISIBLE
                } else {
                    constraintLayout.getChildAt(i).visibility = View.GONE
                }
            }
        } else {
            for (i in 2 until constraintLayout.childCount) {
                if (isEnabled) {
                    constraintLayout.getChildAt(i).visibility = View.VISIBLE
                } else {
                    constraintLayout.getChildAt(i).visibility = View.GONE
                }
            }
        }
    }

    private fun showTimePickerDialog(tv_notification_time: TextView, notifcationTimeKey: String) {
        //split tv string into hour and minute
        val hour = tv_notification_time.text.split(":")[0].toInt()
        val minute = tv_notification_time.text.split(":")[1].toInt()

        val tpd = TimePickerDialog(
            this.context,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minuteOfHour ->

                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minuteOfHour)

                val timeString = EventDate.parseTimeToString(cal.time)

                tv_notification_time.text = timeString
                IOHandler.writeSetting(
                    notifcationTimeKey,
                    timeString
                )
            },
            hour,
            minute,
            true
        )
        tpd.show()
    }

    override fun getItemCount()
            : Int {
        return this.itemList.size
    }

}