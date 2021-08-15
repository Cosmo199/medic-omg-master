package com.example.medicomgmester.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.medicomgmester.databinding.FragmentQuestionnaireBinding

class QuestionnaireFragment : Fragment() {

    private lateinit var questionnaireViewModel: QuestionnaireViewModel
    private var _binding: FragmentQuestionnaireBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        questionnaireViewModel =
            ViewModelProvider(this).get(QuestionnaireViewModel::class.java)

        _binding = FragmentQuestionnaireBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}