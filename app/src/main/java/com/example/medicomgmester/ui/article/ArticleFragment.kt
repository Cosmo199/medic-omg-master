package com.example.medicomgmester.ui.article

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicomgmester.R
import com.example.medicomgmester.databinding.FragmentArticleBinding
import com.example.medicomgmester.model.ListArticle
import com.example.medicomgmester.model.RememberToken
import com.example.medicomgmester.network.ApiService
import com.example.medicomgmester.ui.article.adapter.AdapterArticle
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.load_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        apiService = ApiService()
        val preferences = this.activity?.getSharedPreferences("LOGIN_DATA", Context.MODE_PRIVATE)
        var getToken: String? = preferences?.getString("remember_token", "ไม่มี Token")
        callApi(getToken)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun callApi(token: String?) {
        val call = apiService.articleCall(RememberToken(token))
        call.enqueue(object : Callback<ListArticle> {
            override fun onFailure(call: Call<ListArticle>, t: Throwable) {
                Log.e("error_call", "error--------->")
            }

            override fun onResponse(call: Call<ListArticle>, response: Response<ListArticle>) {
                load_activity.visibility = View.GONE
                val data = response.body()
                val ad: AdapterArticle by lazy { AdapterArticle(listOf()) }
                list_article?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                list_article?.isNestedScrollingEnabled = false
                list_article?.adapter = ad
                data?.results?.let { ad.setItem(it) }
            }
        })
    }


}