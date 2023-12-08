package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.property.PropertyApi
import com.example.myapplication.api.property.storeResponse.PropertyDetailResponse
import com.example.myapplication.databinding.ActivityDetailPropertiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProperti : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPropertiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPropertiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val idProperti = intent.getStringExtra("ID_PROP")

        with(binding){
            val sharedPref = getSharedPreferences("account_data", Context.MODE_PRIVATE)
            val token = sharedPref?.getString("token", "")
            val retro = Retrofit(token).getRetroClientInstance().create(PropertyApi::class.java)

            retro.getSummaryProperty(idProperti.toString()).enqueue(object : Callback<PropertyDetailResponse>{
                override fun onResponse(
                    call: Call<PropertyDetailResponse>,
                    response: Response<PropertyDetailResponse>
                ) {
                    val result = response.body()
                    Log.d("ApiCall SummaryProperty", "Response : ${result?.message}")

                    if (response.isSuccessful) {
                        var data = result?.data
                        propertyType.text = data?.property_type?.name
                        listingType.text = data?.listing_type
                        headlineProperty.text = data?.headline
                        priceProperty.text = "Rp. " + data?.price
                        var lokasi = data?.location?.district + data?.location?.city + data?.location?.province
                        locationProperty.text = lokasi
                        descriptionProperty.text = "deskripsi property"
                        certificateProperty.text = data?.certificate
                        jumlahKamar.text = data?.bedroom + " kamar"
                        jumlahKamarMandi.text = data?.bathroom + " kamar mandi"
                        luasBangunan.text = data?.area
                    } else if (response.code() == 401) {
                        Toast.makeText(this@DetailProperti, "Sesi telah habis, silakan login ulang", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<PropertyDetailResponse>, t: Throwable) {
                    Log.e("DetailProperty", "Error: ${t.message}")
                }
            })

            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}