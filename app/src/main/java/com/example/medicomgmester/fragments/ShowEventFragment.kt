package com.example.medicomgmester.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.medicomgmester.setup.MainActivity
import com.example.medicomgmester.R

import com.example.medicomgmester.handler.EventHandler
import kotlinx.android.synthetic.main.activity_main.*

abstract class ShowEventFragment : Fragment() {

    var eventID: Int = -1
    abstract fun updateUI()
    abstract fun shareEvent()
    abstract fun editEvent()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        (context as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setToolbarTitle(requireContext().resources.getString(R.string.app_name))

        if (eventID >= 0) {
            if (EventHandler.getEventToEventIndex(eventID) != null) {
                this.updateUI()
            } else {
                closeButtonPressed()
            }
        } else if (arguments != null) {
            eventID = requireArguments().getInt(MainActivity.FRAGMENT_EXTRA_TITLE_EVENTID)
            updateUI()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu!!, inflater!!)
        inflater?.inflate(R.menu.toolbar_show_event, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                closeButtonPressed()
            }
            R.id.toolbar_edit -> {
                editEvent()
                (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
        return super.onOptionsItemSelected(item!!)
    }

    private fun setToolbarTitle(title: String) {
        (context as MainActivity).scrollable_toolbar.title = title
    }

    private fun closeButtonPressed() {
        (context as MainActivity).supportFragmentManager.popBackStack()
    }
}