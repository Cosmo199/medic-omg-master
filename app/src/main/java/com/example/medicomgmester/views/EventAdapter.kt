package com.example.medicomgmester.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.medicomgmester.setup.MainActivity
import com.example.medicomgmester.R
import com.example.medicomgmester.fragments.AnnualEventInstanceFragment
import com.example.medicomgmester.fragments.ShowAnnualEvent

import com.example.medicomgmester.handler.EventHandler
import com.example.medicomgmester.models.AnnualEvent
import com.example.medicomgmester.models.MonthDivider
import kotlinx.android.synthetic.main.annual_event_item_view.view.*
import kotlinx.android.synthetic.main.event_month_view_divider.view.*

class EventAdapter(private val context: Context, private val fragmentManager: FragmentManager) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var isClickable: Boolean = true
    class BirthdayEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class EventMonthDividerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class AnnualEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class OneTimeEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        when (EventHandler.getList()[position]) {
            is MonthDivider -> {
                if (position < EventHandler.getList().size - 1) {
                    if (EventHandler.getList()[position + 1] !is MonthDivider) {
                        return 0
                    }
                }
                return -1
            }
            is AnnualEvent -> {
                return 2
            }
        }
        return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // create a new view
        when (viewType) {
            0 -> {
                val itemView =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.event_month_view_divider, parent, false)
                return EventMonthDividerViewHolder(itemView)
            }
            2 -> {
                val itemView =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.annual_event_item_view, parent, false)
                return AnnualEventViewHolder(itemView)
            }
            else -> {
                //Default is birthday event
                val itemView = View(context)
                return EventMonthDividerViewHolder(itemView)
            }
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // - get element from dataset at this position
        // - replace the contents of the view with that element

        when (holder.itemViewType) {
            //EventMonthDividerViewHolder
            0 -> {
                EventHandler.getList()[position].let { monthDivider ->
                    if (monthDivider is MonthDivider) {
                        holder.itemView.tv_divider_description_month.text =
                            monthDivider.month_name
                    }
                }
            }

            //annual event item view holder
            2 -> {
                //check if is birthday event and if the year is given
                EventHandler.getList()[position].let { annualEvent ->
                    if (annualEvent is AnnualEvent) {
                        //set on click listener for item
                        holder.itemView.setOnClickListener {
                            if (isClickable) {
                                val bundle = Bundle()
                                //do this in more adaptable way
                                bundle.putInt(
                                    MainActivity.FRAGMENT_EXTRA_TITLE_EVENTID,
                                    annualEvent.eventID
                                )
                                val ft = fragmentManager.beginTransaction()
                                // add arguments to fragment
                                val newAnnualEvent = ShowAnnualEvent.newInstance()
                                newAnnualEvent.arguments = bundle
                                ft.replace(
                                    R.id.fragment_placeholder,
                                    newAnnualEvent
                                )
                                ft.addToBackStack(null)
                                ft.commit()
                            }
                        }

                        holder.itemView.setOnLongClickListener {
                            if (isClickable) {
                                val bundle = Bundle()
                                //do this in more adaptable way
                                bundle.putInt(
                                    MainActivity.FRAGMENT_EXTRA_TITLE_EVENTID,
                                    annualEvent.eventID
                                )
                                val ft = fragmentManager.beginTransaction()
                                // add arguments to fragment
                                val newAnnualEvent = AnnualEventInstanceFragment.newInstance()
                                newAnnualEvent.arguments = bundle
                                ft.replace(
                                    R.id.fragment_placeholder,
                                    newAnnualEvent
                                )
                                ft.addToBackStack(null)
                                ft.commit()
                            }
                            true
                        }

                        val textColor: Int

                        //set days until
                        val daysUntil = EventHandler.getList()[position].getDaysUntil()
                        if (daysUntil == 0) {
                            textColor = ContextCompat.getColor(context, R.color.colorAccent)
                            //holder.itemView.tv_days_until_annual_value.text =
                                context.resources.getText(R.string.today)
                           // holder.itemView.tv_days_until_annual_value.setTextColor(textColor)
                        } else {
                            textColor = ContextCompat.getColor(context, R.color.textDark)
                           // holder.itemView.tv_days_until_annual_value.text = daysUntil.toString()
                          //  holder.itemView.tv_days_until_annual_value.setTextColor(textColor)
                        }

                        //set date
                        holder.itemView.tv_annual_item_date_value.text =
                            annualEvent.getPrettyShortStringWithoutYear()
                        holder.itemView.tv_annual_item_date_value.setTextColor(textColor)

                        //set years since, if specified
                        if (annualEvent.hasStartYear) {
                            //holder.itemView.tv_years_since_annual_value.text =
                                annualEvent.getXTimesSinceStarting().toString()
                        } else {
                           // holder.itemView.tv_years_since_annual_value.text = "-"
                        }
                       // holder.itemView.tv_years_since_annual_value.setTextColor(textColor)

                        if (annualEvent.eventAlreadyOccurred()) {
                            holder.itemView.constraint_layout_annual_item_view.background =
                                ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ripple_recycler_view_item_dark
                                )
                        } else {
                            holder.itemView.constraint_layout_annual_item_view.background =
                                ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ripple_recycler_view_item
                                )
                        }

                        //set name
                        holder.itemView.tv_annual_item_name.text = annualEvent.name
                        holder.itemView.tv_annual_item_name.setTextColor(textColor)
                    }
                }
            }


        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return if (EventHandler.getList().isEmpty()) {
            0
        } else {
            EventHandler.getList().size
        }
    }
}