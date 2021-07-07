package com.example.medicomgmester.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicomgmester.JsonMockUtility
import com.example.medicomgmester.databinding.FragmentGalleryBinding
import com.example.medicomgmester.model.ListLesson
import com.example.medicomgmester.ui.gallery.adapter.AdapterListGallery
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mockUpJson()
    }

    private fun mockUpJson() {
        JsonMockUtility().apply {
            val dataMock = getJsonToMock("lesson_en.json", ListLesson::class.java)
            val en: AdapterListGallery by lazy { AdapterListGallery(listOf()) }
            list_data_doc?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            list_data_doc?.isNestedScrollingEnabled = false
            list_data_doc?.adapter = en
            dataMock.results?.let { en.setItem(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}