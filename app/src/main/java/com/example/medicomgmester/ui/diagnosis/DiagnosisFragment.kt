package com.example.medicomgmester.ui.diagnosis


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.medicomgmester.databinding.FragmentDiagnosisBinding
import com.example.medicomgmester.extension.load
import com.example.medicomgmester.model.Diagnosis
import kotlinx.android.synthetic.main.fragment_diagnosis.*

class DiagnosisFragment : Fragment(), DiagnosisContact.View {
    private lateinit var presenter: DiagnosisContact.Presenter
    private var _binding: FragmentDiagnosisBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiagnosisBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = DiagnosisPresenter(this)
        presenter.callList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCallSuccess(data: List<Diagnosis>?) {
        detail_diagnosis_1.text = data?.get(0)?.detail_diagnosis ?: ""
        var kidneyType: String? = data?.get(0)?.kidney_type ?: ""
        if (kidneyType.equals("0")) {
            image_view_kidney.load("https://firebasestorage.googleapis.com/v0/b/medic-omg.appspot.com/o/kidney_1.png?alt=media&token=8154b2bd-1c29-4bef-a13e-802d7470e4d3")
            name_diagnosis_2.text = "ตำแหน่งสาย: ข้างซ้าย"
        } else if (kidneyType.equals("1")) {
            image_view_kidney.load("https://firebasestorage.googleapis.com/v0/b/medic-omg.appspot.com/o/kidney_2.png?alt=media&token=c6b52318-7cc3-4731-a702-abbb0d9c765e")
            name_diagnosis_2.text = "ตำแหน่งสาย: ข้างขวา"
        } else {
            image_view_kidney.load("https://firebasestorage.googleapis.com/v0/b/medic-omg.appspot.com/o/kidney_3.png?alt=media&token=01a49a1f-c10e-429e-8359-f37fb83398fb")
            name_diagnosis_2.text = "ตำแหน่งสาย: ทั้ง 2 ข้างซ้าย-ขวา"
        }
        load_diagnosis.visibility = GONE
    }


}