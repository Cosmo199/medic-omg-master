package com.example.medicomgmester.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.medicomgmester.JsonMockUtility
import com.example.medicomgmester.MenuActivity
import com.example.medicomgmester.databinding.FragmentGalleryBinding
import com.example.medicomgmester.model.ListLesson
import com.example.medicomgmester.ui.chat.ChatActivity
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery_detail.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mockUpJson()
        fabOnclick()
    }

    private fun mockUpJson() {
        JsonMockUtility().apply {
            val dataMock = getJsonToMock("lesson_en.json", ListLesson::class.java)
            detail_1.text = dataMock.results?.get(0)?.detail_1
            detail_2.text = dataMock.results?.get(0)?.detail_2
            detail_3.text = dataMock.results?.get(0)?.detail_3
            detail_4.text = dataMock.results?.get(0)?.detail_4
            detail_5.text = dataMock.results?.get(0)?.detail_5
            detail_6.text = dataMock.results?.get(0)?.detail_6
            detail_7.text = dataMock.results?.get(0)?.detail_7
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fabOnclick(){
        fab.setOnClickListener {
           // scrollView.fullScroll(ScrollView.FOCUS_UP);
            val intent = Intent(activity, ChatActivity::class.java)
            activity?.startActivity(intent)
        }
    }


}