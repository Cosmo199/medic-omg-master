package com.example.medicomgmester.ui.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicomgmester.databinding.FragmentHomeBinding
import com.example.medicomgmester.model.Appointment
import com.example.medicomgmester.ui.home.adapter.AdapterListHome
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.load_activity.*

class HomeFragment : Fragment() , HomeContact.View {
    private lateinit var presenter: HomeContact.Presenter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = HomePresenter(this)
        presenter.callList()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCallSuccess(data: List<Appointment>?) {
        load_activity.visibility = View.GONE
        val fd: AdapterListHome by lazy { AdapterListHome(listOf()) }
        list_data_appointment?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list_data_appointment?.isNestedScrollingEnabled = false
        list_data_appointment?.adapter = fd
        data?.let { fd.setItem(it) }
    }



}