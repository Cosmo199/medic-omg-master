package com.example.medicomgmester.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicomgmester.R
import com.example.medicomgmester.handler.EventHandler
import com.example.medicomgmester.models.AnnualEvent
import com.example.medicomgmester.setup.MainActivity
import com.example.medicomgmester.views.EventAdapter
import com.example.medicomgmester.views.RecycleViewItemDivider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_event_list.*
import java.text.SimpleDateFormat
import java.util.*

class EventListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: EventAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var isFABOpen = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (context as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)

        (context as MainActivity).scrollable_toolbar.isTitleEnabled = false
       // (context as MainActivity).toolbar.title = getString(R.string.app_name)

        isFABOpen = false

        fab_layout_add_annual_event.visibility = ConstraintLayout.INVISIBLE

        viewManager =
            LinearLayoutManager(view.context)
        viewAdapter = EventAdapter(view.context, this.requireFragmentManager())

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            scrollToPosition(traverseForFirstMonthEntry())
        }
        recyclerView.addItemDecoration(RecycleViewItemDivider(view.context))
        recyclerView.setPadding(
            recyclerView.paddingLeft,
            recyclerView.paddingTop,
            recyclerView.paddingRight,
            (resources.getDimension(R.dimen.fab_margin) + resources.getDimension(R.dimen.fab_size_bigger)).toInt()
        )

        fab_show_fab_menu.setOnClickListener {
            if (isFABOpen) {
                closeFABMenu()
            } else {
                showFABMenu()
            }
        }


        fab_add_annual_event.setOnClickListener {
            closeFABMenu(true)
            val ft = requireFragmentManager().beginTransaction()
            ft.replace(
                R.id.fragment_placeholder,
                AnnualEventInstanceFragment.newInstance()
            )
            ft.addToBackStack(null)
            ft.commit()
        }
        //addDefaultPressed()
    }

    private fun addDefaultPressed(){
        var eventDate: Date = Calendar.getInstance().time
        val name = "Note default"
        val note = "msg default"
        val isYearGiven = false
        val annualEvent = AnnualEvent(eventDate, name, isYearGiven)
        annualEvent.note = note
        EventHandler.addEvent(annualEvent, this.requireContext(), true)
    }

    override fun onResume() {
        super.onResume()
        //when no items except of the 12 month items are in the event list, then display text message
        if (EventHandler.getList().size - 12 == 0) {
            tv_no_events.visibility = TextView.VISIBLE
        } else {
            tv_no_events.visibility = TextView.GONE
        }
    }

    private fun showFABMenu() {
        isFABOpen = true
        fab_show_fab_menu.isClickable = false
        //show layouts
        fab_layout_add_annual_event.visibility = ConstraintLayout.VISIBLE


        this.recyclerView.animate().alpha(0.15f).apply {
            duration = 175
        }




        //move add annual event layout up
        fab_layout_add_annual_event.animate()
            .translationYBy(-resources.getDimension(R.dimen.standard_105) - 40)
            .apply {
                duration = 100
            }.withEndAction {
                fab_layout_add_annual_event.animate().translationYBy(40.toFloat()).apply {
                    duration = 75
                }
            }

        fab_show_fab_menu.animate().duration = 100
        fab_show_fab_menu.animate().rotationBy(80.0f).withEndAction {
            fab_show_fab_menu.animate().rotationBy(-35.0f).apply {
                duration = 75
            }.withEndAction {
                fab_show_fab_menu.isClickable = true
            }
        }
        viewAdapter.isClickable = false
    }

    private fun closeFABMenu(immediateAction: Boolean = false) {
        isFABOpen = false
        if (!immediateAction) {
            fab_show_fab_menu.isClickable = false
        }
        this.recyclerView.animate().alpha(1.0f)
        fab_layout_add_annual_event.animate()
            .translationYBy(resources.getDimension(R.dimen.standard_105))
            .withEndAction {
                if (!immediateAction) {
                    fab_layout_add_annual_event.visibility = ConstraintLayout.INVISIBLE
                }
            }
        fab_show_fab_menu.animate().rotationBy(-45.0f).withEndAction {
            if (!immediateAction) {
                fab_show_fab_menu.isClickable = true
            }
        }
        viewAdapter.isClickable = true
    }
    private fun traverseForFirstMonthEntry(): Int {
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
        for (i in EventHandler.getList().indices) {
            if (EventHandler.getList()[i].getMonth() == currentMonth)
                return i
        }
        return 0
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater!!)
        inflater?.inflate(R.menu.toolbar_main, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_settings -> {
                settingsClicked()
            }
        }
        return super.onOptionsItemSelected(item!!)
    }

    private fun settingsClicked() {
        //open settings fragment
        closeFABMenu(true)
        val ft = requireFragmentManager().beginTransaction()
        ft.replace(
            R.id.fragment_placeholder,
            SettingsFragment.newInstance()
        )
        ft.addToBackStack(null)
        ft.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(): EventListFragment {
            return EventListFragment()
        }
    }
}
