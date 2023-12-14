package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.message.MessageApi
import com.example.myapplication.databinding.FragmentPesanBinding
import com.example.myapplication.project.PesanViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PesanFragment : Fragment() {
    private lateinit var binding: FragmentPesanBinding
    private lateinit var viewModel: PesanViewModel
    private lateinit var intentToDetail: Intent
    val pesanAdapter = PesanAdapter(
        onClickDisaster = {},
        onItemClick = { idPesan ->
            intentToDetail.putExtra("ID_PESAN", idPesan)
            startActivity(intentToDetail)
        }
    )

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
        binding = FragmentPesanBinding.inflate(inflater, container, false)
        val view = binding.root

        intentToDetail = Intent(activity, DetailPesan::class.java)

        viewModel = ViewModelProvider(this).get(PesanViewModel::class.java)
        viewModel.messageListLiveData.observe(viewLifecycleOwner, Observer { messageList ->
            pesanAdapter.submitList(messageList)
        })

        val sharedPref = activity?.getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(MessageApi::class.java)
        viewModel.getMessageList(retro)

        pesanAdapter.setOnItemClickListener { idPesan ->
            intentToDetail.putExtra("ID_PESAN", idPesan)
            startActivity(intentToDetail)
        }

        binding.rvPesan.apply {
            adapter = pesanAdapter
            layoutManager = LinearLayoutManager(activity)

        }
        return view
    }
}