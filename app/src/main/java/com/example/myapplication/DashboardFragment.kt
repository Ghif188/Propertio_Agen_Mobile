package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.dashboard.DashboardApi
import com.example.myapplication.api.dashboard.DashboardResponse
import com.example.myapplication.databinding.FragmentDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root

        getData()

        return view
    }

    private fun getData() {
        val sharedPref = context?.getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(DashboardApi::class.java)

        retro.getDashboard().enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(
                call: Call<DashboardResponse>,
                response: Response<DashboardResponse>
            ) {
                val result = response.body()
                Log.d("ApiCall Dashboard", "Response : ${result?.message}")

                if (response.isSuccessful) {
                    var data = result?.data
                    with(binding) {
                        txtDraftListing.text = data?.listing_draft_count.toString()
                        txtAktifListing.text = data?.listing_active_count.toString()
                        txtLakuListing.text = data?.listing_done_count.toString()
                        txtLihatListing.text = data?.view_count.toString()
                        txtTertarikListing.text = data?.lead_count.toString()
                        txtPesanMasuk.text = data?.message_count.toString()
                    }
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}