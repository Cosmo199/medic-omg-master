package com.example.medicomgmester.handler

import android.content.Context
import android.net.Uri
import com.example.medicomgmester.setup.MainActivity
import com.example.medicomgmester.models.EventBirthday
import com.example.medicomgmester.models.EventDate
import com.example.medicomgmester.models.MonthDivider
import com.example.medicomgmester.models.SortIdentifier
import kotlinx.android.synthetic.main.fragment_event_list.*
import java.util.*
object EventHandler {

    private var event_list: List<EventDate> = emptyList()
    private var event_map: MutableMap<Int, EventDate> = emptyMap<Int, EventDate>().toMutableMap()
    fun addEvent(
        event: EventDate,
        context: Context,
        writeAfterAdd: Boolean = true,
        addNewNotification: Boolean = true,
        updateEventList: Boolean = true,
        addBitmap: Boolean = true

    ) {

        if (event !is MonthDivider) {
            val cal = Calendar.getInstance()
            cal.time = event.eventDate
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.SECOND, 1)
            event.eventDate = cal.time
        }

        event_map[event.eventID] = event

        if (event is EventBirthday && addBitmap) {
            Thread(Runnable {
                if (event.avatarImageUri != null) {
                    BitmapHandler.addDrawable(
                        event.eventID,
                        Uri.parse(event.avatarImageUri),
                        context,
                        readBitmapFromGallery = false,
                        //150dp because the app_bar height is 300dp
                        scale = MainActivity.convertDpToPx(context, 150f)
                    )
                }
                if (context is MainActivity) {
                    context.runOnUiThread {
                        if (context.recyclerView != null) {
                            context.recyclerView.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }).start()
        }
        if (event !is MonthDivider && addNewNotification) {
            NotificationHandler.scheduleNotification(context, event)
        }
        if (updateEventList) {
            event_list = getSortedListBy()
        }
        if (writeAfterAdd) {
            IOHandler.writeEventToFile(event.eventID, event)
        }
    }
    fun changeEventAt(
        ID: Int,
        newEvent: EventDate,
        context: Context,
        writeAfterChange: Boolean = false
    ) {
        getEventToEventIndex(ID)?.let { oldEvent ->
            newEvent.eventID = ID
            if (newEvent !is MonthDivider) {
                val cal = Calendar.getInstance()
                cal.time = newEvent.eventDate
                cal.set(Calendar.HOUR_OF_DAY, 0)
                cal.set(Calendar.SECOND, 1)
                newEvent.eventDate = cal.time
            }
            NotificationHandler.cancelNotification(context, oldEvent)
            NotificationHandler.scheduleNotification(context, newEvent)
            event_map[ID] = newEvent

            if (newEvent is EventBirthday) {
                if (newEvent.avatarImageUri != null) {
                    val newEventImageUri = newEvent.avatarImageUri
                    if ((oldEvent as EventBirthday).avatarImageUri != null) {
                        BitmapHandler.removeBitmap(oldEvent.eventID, context)
                    }
                    BitmapHandler.addDrawable(
                        ID,
                        Uri.parse(newEventImageUri),
                        context,
                        readBitmapFromGallery = true,
                        scale = MainActivity.convertDpToPx(context, 150f)
                    )
                }
            }
            event_list = getSortedListBy()

            if (writeAfterChange) {
                IOHandler.writeEventToFile(ID, newEvent)
            }
        }
    }
    fun removeEventByID(index: Int, context: Context, writeChange: Boolean = false) {
        getEventToEventIndex(index)?.let { event ->
            if (event is EventBirthday) {
                BitmapHandler.removeBitmap(index, context)
            }
            NotificationHandler.cancelNotification(context, event)
            if (writeChange) {
                IOHandler.removeEventFromFile(event.eventID)
            }
            event_map.remove(index)
            event_list = getSortedListBy()
        }
    }

    fun clearData() {
        if (event_list.isNotEmpty()) {
            event_map.clear()
            event_list = getSortedListBy()
        }
    }
    fun getEventToEventIndex(index: Int): EventDate? {
        if (event_map.containsKey(index))
            return event_map[index]
        return null
    }
    fun getList(): List<EventDate> {
        return event_list
    }

    fun getEventsAsStringList(): String {
        var eventString = ""
        val tempList = event_list.toMutableList()
        tempList.forEach {
            //don't save Monthdividers bc they are created with the first start of the app
            if (it !is MonthDivider) {
                //removing avatar image
                if (it is EventBirthday) {
                    eventString += it.toStringWithoutImage() + "\n"
                } else {
                    eventString += it.toString() + "\n"
                }
            }
        }
        return eventString
    }
    private fun getSortedListBy(
        identifier: SortIdentifier = EventDate.Identifier.Date
    ): List<EventDate> {
        if (identifier == EventDate.Identifier.Date) {
            return event_map.values.sorted()
        } else {
            return emptyList()
        }
    }
}