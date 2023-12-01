package com.example.myapplication.input

import android.R
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.admin.TypeProperty
import com.example.myapplication.api.admin.response.TypePropertyResponse
import com.example.myapplication.databinding.ActivityInputPropertiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputProperti : AppCompatActivity() {
    private lateinit var binding: ActivityInputPropertiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputPropertiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val tipe_iklan = resources.getStringArray(com.example.myapplication.R.array.tipe_iklan)
        val tipe_sertifikat = resources.getStringArray(com.example.myapplication.R.array.tipe_sertifikat)

        // retrofit
        val sharedPref = getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(TypeProperty::class.java)

        with(binding){
            // fetch list data tipe_properti
            var selectedTypeId = 0
            var selectedType = ""

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

                        tipePropertiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        tipePropertiSpinner.adapter = tipePropertiAdapter

                        tipePropertiSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {}

                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                selectedTypeId = tipePropertiList?.get(position)?.id ?: 0
                                selectedType = tipePropertiList?.get(position)?.name ?: ""
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

            val tipeIklanAdapter  = ArrayAdapter(this@InputProperti, R.layout.simple_spinner_dropdown_item, tipe_iklan)
            tipeIklanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeIklan.adapter = tipeIklanAdapter

            val sertifikatAdapter  = ArrayAdapter(this@InputProperti, R.layout.simple_spinner_dropdown_item, tipe_sertifikat)
            sertifikatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sertifikat.adapter = sertifikatAdapter

            btnNext.setOnClickListener {
                val intentToInputLokasi = Intent(this@InputProperti, InputLokasi::class.java)
                intentToInputLokasi.putExtra("tipe", selectedType)
                startActivity(intentToInputLokasi)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputProperti, MainActivity::class.java)
                startActivity(intentToInputLokasi)
                finish()
            }
        }
    }
}