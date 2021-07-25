package com.example.medicomgmester.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.medicomgmester.setup.MainActivity
import com.example.medicomgmester.R
import com.example.medicomgmester.handler.EventHandler
import com.example.medicomgmester.models.AnnualEvent
import com.example.medicomgmester.models.EventDate
import kotlinx.android.synthetic.main.fragment_annual_instance.*
import kotlinx.android.synthetic.main.fragment_event_list.*
import java.text.DateFormat
import java.util.*

class AnnualEventInstanceFragment : EventInstanceFragment() {

    private var isEditAnnualEvent = false
    var eventID = -1
    private val editName: EditText by lazy { requireView().findViewById<EditText>(R.id.edit_add_fragment_name_annual_event) }
    private val editDate: TextView by lazy { requireView().findViewById<TextView>(R.id.edit_add_fragment_date_annual_event) }
    private val editNote: EditText by lazy { requireView().findViewById<EditText>(R.id.edit_add_fragment_note_annual_event) }
    private val switchIsYearGiven: Switch by lazy { requireView().findViewById<Switch>(R.id.sw_is_year_given_annual_event) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_annual_instance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editName.hint =
            "${context?.getText(R.string.edit_annual_event_name_hint)} ${context?.getText(R.string.necessary)}"
        if (arguments != null) {
            isEditAnnualEvent = true
            setToolbarTitle(requireContext().resources.getString(R.string.toolbar_title_edit_annual_event))
            eventID = (requireArguments().getInt(MainActivity.FRAGMENT_EXTRA_TITLE_EVENTID))
            EventHandler.getEventToEventIndex(eventID)?.let { annualEvent ->
                if (annualEvent is AnnualEvent) {
                    this.eventDate = annualEvent.eventDate
                    if (annualEvent.hasStartYear) {
                        editDate.text =
                            EventDate.getLocalizedDayMonthYearString(this.eventDate)
                    } else {
                        editDate.text = EventDate.getLocalizedDayAndMonthString(this.eventDate)
                    }
                    editName.setText(annualEvent.name)
                    if (!annualEvent.note.isNullOrBlank()) {
                        editNote.setText(annualEvent.note)
                    }
                    switchIsYearGiven.isChecked = annualEvent.hasStartYear
                    btn_fragment_annual_event_instance_delete.visibility = Button.VISIBLE
                    btn_fragment_annual_event_instance_delete.setOnClickListener {

                        val alertBuilder = AlertDialog.Builder(context)
                        alertBuilder.setTitle(resources.getString(R.string.btn_annual_event_delete))
                        alertBuilder.setMessage(resources.getString(R.string.alert_dialog_body_message_annual_event))
                        val contextTemp = context

                        // Set a positive button and its click listener on alert dialog
                        alertBuilder.setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                            // delete annual_event on positive button
                            Snackbar
                                .make(
                                    view,
                                    resources.getString(
                                        R.string.annual_event_deleted_notification,
                                        editName.text
                                    ),
                                    Snackbar.LENGTH_LONG
                                )
                                .setAction(R.string.undo) {
                                    EventHandler.addEvent(
                                        annualEvent, contextTemp!!,
                                        true
                                    )
                                    //get last fragment in stack list, when its eventlistfragment, we can update the recycler view
                                    val fragment =
                                        (contextTemp as MainActivity).supportFragmentManager.fragments.last()
                                    if (fragment is EventListFragment) {
                                        fragment.recyclerView.adapter!!.notifyDataSetChanged()
                                        fragment.tv_no_events.visibility = TextView.GONE
                                    }
                                }
                                .show()

                            EventHandler.removeEventByID(eventID, contextTemp!!, true)
                            closeBtnPressed()
                        }
                        alertBuilder.setNegativeButton(resources.getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
                        // Finally, make the alert dialog using builder
                        val dialog: AlertDialog = alertBuilder.create()
                        // Display the alert dialog on app interface
                        dialog.show()
                    }
                }
            }
        } else {
            setToolbarTitle(requireContext().resources.getString(R.string.toolbar_title_add_annual_event))
            btn_fragment_annual_event_instance_delete.visibility = Button.INVISIBLE
            editDate.hint = EventDate.getLocalizedDayMonthYearString(this.eventDate)
        }

        editDate.setOnClickListener { showDatePickerDialog() }

        switchIsYearGiven.setOnCheckedChangeListener { _, isChecked ->
            if (editDate.text.isNotBlank()) {
                //year is given
                if (isChecked) {
                    val cal = Calendar.getInstance()
                    if (this.eventDate.after(cal.time)) {
                        cal.time = this.eventDate
                        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) - 1)
                        this.eventDate = cal.time
                    }

                    editDate.text = EventDate.getLocalizedDayMonthYearString(this.eventDate)
                    //year is not given
                } else {
                    editDate.text = EventDate.getLocalizedDayAndMonthString(this.eventDate)
                }
            } else {
                if (isChecked) {
                    editDate.hint = EventDate.getLocalizedDayMonthYearString(this.eventDate)

                } else {
                    editDate.hint = EventDate.getLocalizedDayAndMonthString(this.eventDate)
                }
            }
        }


    }

    private fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        if (!editDate.text.isNullOrBlank()) {
            c.time = this.eventDate
        }
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd =
            DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year_, monthOfYear, dayOfMonth ->
                    c.set(Calendar.YEAR, year_)
                    c.set(Calendar.MONTH, monthOfYear)
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    if (c.time.after(Calendar.getInstance().time) && switchIsYearGiven.isChecked) {
                        Toast.makeText(
                            view.context,
                            requireContext().resources.getText(R.string.future_annual_event_error),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        this.eventDate = c.time
                        if (switchIsYearGiven.isChecked) {
                            editDate.text = EventDate.getLocalizedDayMonthYearString(this.eventDate)
                        } else {
                            editDate.text =
                                EventDate.getLocalizedDayAndMonthString(this.eventDate)
                        } }
                }, year, month, day
            )
        dpd.show()
    }


    override fun acceptBtnPressed() {
        val name = editName.text.toString()
        val date = editDate.text.toString()
        val note = editNote.text.toString()
        val isYearGiven = switchIsYearGiven.isChecked

        if (name.isBlank() || date.isBlank()) {
            Toast.makeText(
                context,
                context?.resources?.getText(R.string.empty_fields_error_annual_event),
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            val annualEvent = AnnualEvent(this.eventDate, name, isYearGiven)
            if (note.isNotBlank()) {
                annualEvent.note = note
            }

            //new annual event entry, just add a new entry in map
            if (!isEditAnnualEvent) {
                EventHandler.addEvent(annualEvent, this.requireContext(), true)
                Snackbar
                    .make(
                        requireView(),
                        requireContext().resources.getString(
                            R.string.annual_event_added_notification,
                            name
                        ),
                        Snackbar.LENGTH_LONG
                    )
                    .show()
                closeBtnPressed()
                //already annual event entry, overwrite old entry in map
            } else {
                EventHandler.getEventToEventIndex(eventID)?.let { event ->
                    if (event is AnnualEvent && wasChangeMade(event)) {
                        EventHandler.changeEventAt(eventID, annualEvent, requireContext(), true)
                        Snackbar.make(
                            requireView(),
                            requireContext().resources.getString(
                                R.string.annual_event_changed_notification,
                                name
                            ),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    closeBtnPressed()
                }
            }
        }
    }

    private fun wasChangeMade(event: AnnualEvent): Boolean {
        if (switchIsYearGiven.isChecked) {
            if (editDate.text != event.dateToPrettyString(DateFormat.FULL)) return true
        } else {
            if (editDate.text != event.dateToPrettyString(DateFormat.DATE_FIELD).subSequence(0..5)
                    .toString()
            ) return true
        }

        if (editNote.text.isNotBlank() && event.note == null) {
            return true
        } else {
            if (event.note != null) {
                if (editNote.text.toString() != event.note!!) return true
            }
        }
        if (editName.text.toString() != event.name) return true
        if (switchIsYearGiven.isChecked != event.hasStartYear) return true
        //if nothing has changed return false
        return false
    }

    companion object {
        const val ANNUAL_EVENT_INSTANCE_FRAGMENT_TAG = "ANNUAL_EVENT_INSTANCE"
        @JvmStatic
        fun newInstance(): AnnualEventInstanceFragment {
            return AnnualEventInstanceFragment()
        }
    }
}
