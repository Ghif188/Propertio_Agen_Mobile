package com.example.myapplication.input

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.PropertiBerhasil
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.admin.TypeProperty
import com.example.myapplication.api.property.PropertyApi
import com.example.myapplication.api.property.storeRequest.StorePropertyLocationRequest
import com.example.myapplication.api.property.storeResponse.StorePropertyLocationResponse
import com.example.myapplication.databinding.ActivityInputSummaryBinding
import com.example.myapplication.model.FormProperti
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputSummary : AppCompatActivity() {
    private lateinit var binding: ActivityInputSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputSummaryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dataTemp = intent.extras?.get("temp") as FormProperti

        with(binding){

            if (dataTemp.fotoProperti?.isNotEmpty() == true) {
                val firstPhoto = dataTemp.fotoProperti?.get(0)?.image
                Glide.with(this@InputSummary)
                    .load(firstPhoto)
                    .into(headerImage)
            }
            judulProperti.text = dataTemp.judulProperti
            tipeProperti.text = dataTemp.tipePropertiTeks
            listingType.text = dataTemp.tipeIklan
            harga.text = dataTemp.detailProperti?.harga.toString()
            var lok = dataTemp.kecamatan + ", " + dataTemp.kota + ", " + dataTemp.provinsi
            lokasi.text = lok
            deskripsi.text = dataTemp.detailProperti?.deskripsi
            sertifikat.text = dataTemp.tipeSertifikat
            jumlahKamar.text = dataTemp.detailProperti?.jmlKamar.toString() + " kamar"
            jumlahKamarMandi.text = dataTemp.detailProperti?.jmlKamarMandi.toString() + " kamar mandi"
            luasBangunan.text = dataTemp.detailProperti?.luasBangunan.toString()

            btnBack.setOnClickListener {
                finish()
            }

            btnNext.setOnClickListener {
                storePropertyLocation(dataTemp)
//                val intentToInputVideo = Intent(this@InputSummary, MainActivity::class.java)
//                startActivity(intentToInputVideo)
            }
        }
    }

    private fun storePropertyLocation(dataTemp: FormProperti) {
        val request = StorePropertyLocationRequest()
        request.headline = dataTemp.beritaProperti
        request.title = dataTemp.judulProperti
        request.property_type_id = dataTemp.tipeProperti
        request.listing_type = dataTemp.tipeIklan
        request.certificate = dataTemp.tipeSertifikat
        request.district = dataTemp.kecamatan
        request.city = dataTemp.kota
        request.province = dataTemp.provinsi
        request.longitude = "0.1"
        request.latitude = "0.2"
        // status?
        request.listing_class = "listing class null"

        val sharedPref = getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(PropertyApi::class.java)

        retro.storePropertyLocation(request).enqueue(object : Callback<StorePropertyLocationResponse> {
            override fun onResponse(
                call: Call<StorePropertyLocationResponse>,
                response: Response<StorePropertyLocationResponse>
            ) {
                val result = response.body()
                Log.d("StorePropertyLocation", "Response : ${result?.message}")

                if (response.isSuccessful) {
                    var id_properti = result?.data?.id
                    val intent = Intent(this@InputSummary, PropertiBerhasil::class.java)
                    startActivity(intent)
                } else if (response.code() == 422) {
                    Toast.makeText(this@InputSummary, "Terdapat data yang masih belum diisi", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 401) {
                    Toast.makeText(this@InputSummary, "Silakan login ulang", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<StorePropertyLocationResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        Log.d("HALO _!!!", dataTemp.toString())
    }
}