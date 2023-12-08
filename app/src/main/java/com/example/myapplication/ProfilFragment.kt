package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentPesanBinding
import com.example.myapplication.databinding.FragmentProfilBinding
import com.example.myapplication.databinding.LogoutPopupBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfilFragment : Fragment() {
    private lateinit var binding: FragmentProfilBinding
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
        binding = FragmentProfilBinding.inflate(inflater, container, false)
        val view = binding.root
        val intentToChangePass = Intent(activity, ChangePass::class.java)
        binding.ubahKataSandi.setOnClickListener {
            startActivity(intentToChangePass)
        }
        binding.btnLogout.setOnClickListener {
            popupLogout()
        }
        return view
    }

    private fun popupLogout() {
        val dialog = Dialog(requireContext())
        val inflaterDialog = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.logout_popup)
        val window = dialog.window
        val layoutParams = WindowManager.LayoutParams()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        layoutParams.copyFrom(window?.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes = layoutParams
        dialog.show()
        val btnBatal = dialog.findViewById<TextView>(R.id.btn_batal_logout)
        val btnClose = dialog.findViewById<TextView>(R.id.btn_close_dialog)
        val btnLogout = dialog.findViewById<TextView>(R.id.btn_logout)
        btnClose.setOnClickListener {
            dialog.cancel()
        }
        btnBatal.setOnClickListener {
            dialog.cancel()
        }
        btnLogout.setOnClickListener {
            dialog.cancel()
            val sharedPreferences = activity?.getSharedPreferences("account_data", AppCompatActivity.MODE_PRIVATE)
            with(sharedPreferences!!.edit()) {
                putString("token", null)
                commit()
            }

            val intentToLogin = Intent(activity, Login::class.java)
            startActivity(intentToLogin)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}