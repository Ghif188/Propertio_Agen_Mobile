package com.example.myapplication.input

import android.R
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.admin.TypeProperty
import com.example.myapplication.api.admin.response.TypePropertyResponse
import com.example.myapplication.databinding.ActivityInputPropertiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputProperti : AppCompatActivity() {
    private lateinit var binding: ActivityInputPropertiBinding
    private var selected_tipe_properti_id = 0
    private var selected_tipe_properti = ""
    private var selected_tipe_iklan = ""
    private var selected_sertifikat = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputPropertiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val tipe_iklan = resources.getStringArray(com.example.myapplication.R.array.tipe_iklan)
        val tipe_sertifikat = resources.getStringArray(com.example.myapplication.R.array.tipe_sertifikat)

        val sharedPreferences = getSharedPreferences("property_data", MODE_PRIVATE)
        val id = sharedPreferences.getString("property_id", null)

        with(binding){
            if (id != null) {
                val property_berita = sharedPreferences.getString("property_beritaProperti", null)
                txtBeritaUtama.setText(property_berita)

                val property_judul = sharedPreferences.getString("property_judulProperti", null)
                txtJudulProperti.setText(property_judul)

                val property_tipe = sharedPreferences.getString("property_tipeProperti", null)
                getTipeProperty(property_tipe.toString())

                val tipeiklan = sharedPreferences.getString("property_tipeIklan", null)
                getTipeIklan(tipe_iklan, tipeiklan.toString())

                val tipesertifikat = sharedPreferences.getString("property_tipeSertifikat", null)
                getSertifikat(tipe_sertifikat, tipesertifikat.toString())
            }

            val filterJudul = arrayOf<InputFilter>(InputFilter.LengthFilter(100))
            txtJudulProperti.filters = filterJudul

            val filterBerita = arrayOf<InputFilter>(InputFilter.LengthFilter(70))
            txtBeritaUtama.filters = filterBerita

            getTipeProperty("")
            getTipeIklan(tipe_iklan, "")
            getSertifikat(tipe_sertifikat, "")

            btnNext.setOnClickListener {
                if (txtBeritaUtama.text.toString().isEmpty()) {
                    Toast.makeText(this@InputProperti, "Silahkan masukkan berita properti", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (txtJudulProperti.text.toString().isEmpty()) {
                    Toast.makeText(this@InputProperti, "Silahkan masukkan judul properti", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val dataTemp = getSharedPreferences("dataTemp", MODE_PRIVATE)
                with(dataTemp.edit()) {
                    putString("berita", txtBeritaUtama.text.toString())
                    putString("judul", txtJudulProperti.text.toString())
                    putString("tipe_properti", selected_tipe_properti_id.toString())
                    putString("tipe_properti_teks", selected_tipe_properti)
                    putString("tipe_iklan", selected_tipe_iklan)
                    putString("tipe_sertifikat", selected_sertifikat)
                    commit()
                }

                val intentToInputLokasi = Intent(this@InputProperti, InputLokasi::class.java)
                startActivity(intentToInputLokasi)
            }

            btnBack.setOnClickListener{
                clearPropertyData()
                finish()
            }
        }
    }

    private fun getTipeProperty(id: String) {
        val sharedPref = getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(TypeProperty::class.java)

        retro.getPropertyType().enqueue(object : Callback<TypePropertyResponse>{
            override fun onResponse(
                call: Call<TypePropertyResponse>,
                response: Response<TypePropertyResponse>
            ) {
                val typeResponse = response.body()
                Log.d("ApiCall TypeProperty", "Response : ${typeResponse?.message}")

                if (response.isSuccessful) {
                    val tipePropertiList = typeResponse?.data

                    val tipe_properti = tipePropertiList?.map {it.name} ?: emptyList()

                    val tipePropertiAdapter = ArrayAdapter(this@InputProperti,
                        R.layout.simple_spinner_dropdown_item,
                        tipe_properti)

                    tipePropertiAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                    binding.tipePropertiSpinner.adapter = tipePropertiAdapter

                    binding.tipePropertiSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {}

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            selected_tipe_properti_id = tipePropertiList?.get(position)?.id ?: 0
                            selected_tipe_properti = tipePropertiList?.get(position)?.name ?: ""
                        }
                    }
                    if (id != "") {
                        val position = tipePropertiList?.indexOfFirst { it.id == id.toInt() }
                        if (position != null) {
                            binding.tipePropertiSpinner.setSelection(position.toInt())
                        }

                    }
                } else if (response.code() == 401) {
                    Toast.makeText(this@InputProperti, "Silakan login ulang", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("ApiCall TypeProperty", "Error fetching data from API")
                }
            }

            override fun onFailure(call: Call<TypePropertyResponse>, t: Throwable) {
                Log.e("ApiCall TypeProperty", "Error : ${t.message}")
            }
        })
    }

    private fun getTipeIklan(tipeIklan: Array<String>, s: String) {
        val tipeIklanAdapter  = ArrayAdapter(this@InputProperti, R.layout.simple_spinner_dropdown_item, tipeIklan)
        tipeIklanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.tipeIklan.adapter = tipeIklanAdapter

        binding.tipeIklan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selected_tipe_iklan = binding.tipeIklan.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        if (s == "rent") {
            binding.tipeIklan.setSelection(0)
        } else {
            binding.tipeIklan.setSelection(1)
        }
    }

    private fun getSertifikat(sertifikat: Array<String>, s: String) {
        val sertifikatAdapter  = ArrayAdapter(this@InputProperti, R.layout.simple_spinner_dropdown_item, sertifikat)
        sertifikatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sertifikat.adapter = sertifikatAdapter

        binding.sertifikat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selected_sertifikat = binding.sertifikat.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        if (s.isNotEmpty()) {
            val position = sertifikat.indexOf(s)
            if (position != -1) {
                binding.sertifikat.setSelection(position)
                selected_sertifikat = s
            }
        }
    }

    private fun clearPropertyData(){
        val sharedPreferences = getSharedPreferences("property_data", MODE_PRIVATE)
        with(sharedPreferences!!.edit()) {
            clear()
            commit()
        }
    }
}