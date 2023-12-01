package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentPesanBinding
import com.example.myapplication.databinding.FragmentProyekBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PesanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PesanFragment : Fragment() {
    private lateinit var binding: FragmentPesanBinding
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
        binding = FragmentPesanBinding.inflate(inflater, container, false)
        val view = binding.root
        val IntentToDetailPesan = Intent(activity, DetailPesan::class.java)
        val pesanAdapter = PesanAdapter(generateDummy()) {
            pesan ->  startActivity(IntentToDetailPesan)
        }
        binding.rvPesan.apply {
            adapter = pesanAdapter
            layoutManager = GridLayoutManager(activity, 1)

        }
        return view
    }

    private fun generateDummy(): List<Pesan> {
        return listOf(
            Pesan(
                namaPengirim = "Ghifari",
                judulPesan = "Informasi Pemberitahuan Proyek",
                jamPesan = "10:00",
                imgPengirim = "https://images.unsplash.com/photo-1603415526960-f7e0328c63b1?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            )
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PesanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PesanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}