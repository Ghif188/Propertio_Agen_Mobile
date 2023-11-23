package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentProyekBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProyekFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProyekFragment : Fragment() {
    private lateinit var binding: FragmentProyekBinding
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        binding = FragmentProyekBinding.inflate(inflater, container, false)
        val view = binding.root
        val propertiAdapter = PropertiAdapter(generateDummy()) {
        }
        binding.rvProperti.apply {
            adapter = propertiAdapter
            layoutManager = GridLayoutManager(activity, 1)

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

    private fun generateDummy(): List<Properti> {
        return listOf(
            Properti(
                imgProperti = "https://images.unsplash.com/photo-1523217582562-09d0def993a6?q=80&w=1780&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                judulProperti = "Rumah Idaman",
                lokasiProperti = "Surabaya",
                ketProperti = "Tempat yang sangat memanjakan mata yang jauh dihati",
                hargaProperti = 59000000,
                disukai = 200,
                dilihat = 1000,
                tipeProperti = "Rumah",
                statusProperti = "Laku",
                kodeProperti = "RMH-091278"
            ),
            Properti(
                imgProperti = "https://images.unsplash.com/photo-1568605114967-8130f3a36994?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                judulProperti = "Rumah Bagus",
                lokasiProperti = "Jl. Kubangan Rumah no 22a, kec. Cisarua, kab. Bandung Barat",
                ketProperti = "Tempat yang sangat memanjakan mata yang jauh dihati",
                hargaProperti = 5000000,
                disukai = 200,
                dilihat = 1000,
                tipeProperti = "Rumah",
                statusProperti = "Laku",
                kodeProperti = "RMH-091278"
            ),
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProyekFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProyekFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}