package com.example.myapplication

import com.example.myapplication.viewmodel.ProjectViewModel
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
import com.example.myapplication.api.property.PropertyApi
import com.example.myapplication.databinding.FragmentProyekBinding
import com.example.myapplication.input.InputProperti

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProyekFragment : Fragment() {
    private lateinit var binding: FragmentProyekBinding
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel: ProjectViewModel
    private lateinit var intentToDetail: Intent
    val propertiAdapter = PropertiAdapter(
        onClickDisaster = {},
        onItemClick = { idProperti ->
            intentToDetail.putExtra("ID_PROP", idProperti)
            startActivity(intentToDetail)
        }
    )
    override fun onAttach(context: Context) {
        super.onAttach(context)
        intentToDetail = Intent(requireActivity(), DetailProperti::class.java)
    }
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
        binding = FragmentProyekBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
        viewModel.propertyListLiveData.observe(viewLifecycleOwner, Observer { propertyList ->
            propertiAdapter.setData(propertyList)
        })

        val sharedPref = activity?.getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(PropertyApi::class.java)
        viewModel.getPropertyList(retro)

        binding.rvProperti.apply {
            adapter = propertiAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        binding.addProperty.setOnClickListener{
            val intentToAddProperty = Intent(activity, InputProperti::class.java)
            startActivity(intentToAddProperty)
        }
        binding.draftFilter.setOnClickListener{
            binding.draftFilter.isEnabled = false
            binding.aktifFilter.isEnabled = true
            binding.lakuFilter.isEnabled = true
        }
        binding.aktifFilter.setOnClickListener{
            binding.draftFilter.isEnabled = true
            binding.aktifFilter.isEnabled = false
            binding.lakuFilter.isEnabled = true
        }
        binding.lakuFilter.setOnClickListener{
            binding.draftFilter.isEnabled = true
            binding.aktifFilter.isEnabled = true
            binding.lakuFilter.isEnabled = false
        }
        return view
    }
}