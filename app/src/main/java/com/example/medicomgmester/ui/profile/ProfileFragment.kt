package com.example.medicomgmester.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.medicomgmester.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        val share = context?.getSharedPreferences("LOGIN_DATA", AppCompatActivity.MODE_PRIVATE)
        val i: String? = share?.getString("name", null)
        edit_profile_name.setText(i)

        btn_save.setOnClickListener {
            val profile_user_data: String? = edit_profile_name.text.toString()
            val editor = context?.getSharedPreferences("LOGIN_DATA", AppCompatActivity.MODE_PRIVATE)?.edit()
            editor?.putString("name", profile_user_data)
            editor?.apply()
        }

    }


}