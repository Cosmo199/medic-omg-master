package com.example.medicomgmester.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.medicomgmester.setup.MainActivity
import com.example.medicomgmester.R
import java.util.*

abstract class EventInstanceFragment : Fragment() {

    private val toolbar: Toolbar by lazy {
        activity!!.findViewById<Toolbar>(R.id.toolbar)
    }

    private var toolbarContentInsentLeft = 56

    protected var eventDate: Date = Calendar.getInstance().time

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (context as MainActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(false)

        //use 16dp as left content insent
        toolbarContentInsentLeft = MainActivity.convertDpToPx(context!!, 16.toFloat())

        //check if toolbar already has a view inflated
        var toolbarView: View? = toolbar.getChildAt(0)
        if (toolbarView != null && toolbarView.id == R.id.constrLayout_toolbar_edit) {
            toolbar.getChildAt(0).visibility = View.VISIBLE
            toolbar.setContentInsetsAbsolute(0, toolbar.contentInsetRight)
        } else {
            //when toolbar doesnt have child of custom view, then inflate and add it to toolbar, also set some params
            toolbarView = layoutInflater.inflate(R.layout.toolbar_edit_event, null)
            val actionBarParams =
                ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT
                )
            actionBarParams.gravity = Gravity.CENTER
            toolbar.setContentInsetsAbsolute(0, toolbar.contentInsetRight)
            toolbar.addView(toolbarView, 0, actionBarParams)
        }

        toolbarView?.findViewById<ImageView>(R.id.btn_edit_event_accept).apply {
            this?.setOnClickListener {
                acceptBtnPressed()
            }
        }
        toolbarView?.findViewById<ImageView>(R.id.btn_edit_event_close).apply {
            this?.setOnClickListener {
                closeBtnPressed()
            }
        }
    }

    fun setToolbarTitle(title: String) {
        val toolbarView: View? = toolbar.getChildAt(0)
        if (toolbarView != null && toolbarView.id == R.id.constrLayout_toolbar_edit) {
            toolbarView.findViewById<TextView>(R.id.tv_edit_fragment_title).apply {
                text = title
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        toolbar.getChildAt(0).visibility = View.GONE
        toolbar.setContentInsetsAbsolute(this.toolbarContentInsentLeft, toolbar.contentInsetRight)
        toolbar.setBackgroundColor(
            ContextCompat.getColor(
                context as MainActivity,
                android.R.color.transparent
            )
        )
    }

    fun closeBtnPressed() {
        if (context != null) {
            (context as MainActivity).supportFragmentManager.popBackStack()
        }
    }

    abstract fun acceptBtnPressed()

}