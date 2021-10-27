package com.example.medicomgmester.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.medicomgmester.databinding.FragmentSlideshowBinding
import com.example.medicomgmester.ui.chat.ChatActivity
import com.example.medicomgmester.ui.emergency.EmergencyActivity
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel = ViewModelProvider(this).get(SlideshowViewModel::class.java)
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventChat()
        eventEmergency()
    }

    private fun eventChat() {
        layout_doctor_chat.setOnClickListener {
            val intent = Intent(activity, ChatActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    private fun eventEmergency() {
        layout_emergency_call.setOnClickListener {
            val intent = Intent(activity, EmergencyActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}