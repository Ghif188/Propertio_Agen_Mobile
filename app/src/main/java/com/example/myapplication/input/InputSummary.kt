package com.example.myapplication.input

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.myapplication.PropertiBerhasil
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.property.PropertyApi
import com.example.myapplication.api.property.storeRequest.StorePropertyDetailRequest
import com.example.myapplication.api.property.storeRequest.StorePropertyLocationRequest
import com.example.myapplication.api.property.storeResponse.StorePropertyDetailResponse
import com.example.myapplication.api.property.storeResponse.StorePropertyLocationResponse
import com.example.myapplication.databinding.ActivityInputSummaryBinding
import com.example.myapplication.model.FormDetailProperti
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
        val dataTempo = getSharedPreferences("dataTemp", MODE_PRIVATE)
        val oldData = getSharedPreferences("property_data", MODE_PRIVATE)
        val id = oldData.getString("property_id", null)

        with(binding){

            if (dataTemp.fotoProperti?.isNotEmpty() == true) {
                val firstPhoto = dataTemp.fotoProperti?.get(0)?.image
                Glide.with(this@InputSummary)
                    .load(firstPhoto)
                    .into(headerImage)
            }
            judulProperti.text = dataTempo.getString("judul", null)
            tipeProperti.text = dataTempo.getString("tipe_properti_teks", null)
            listingType.text = dataTempo.getString("tipe_iklan", null)
            harga.text = dataTempo.getInt("detail_harga", 0).toString()
            var lok = dataTempo.getString("alamat", null) + ", " + dataTempo.getString("kecamatan", null) + ", " + dataTempo.getString("kota", null) + ", " + dataTempo.getString("provinsi", null)
            lokasi.text = lok
            deskripsi.text = dataTempo.getString("detail_deskripsi", null)
            sertifikat.text = dataTempo.getString("tipe_sertifikat", null)
            jumlahKamar.text = dataTempo.getInt("detail_jmlKamar", 0).toString() + " kamar"
            jumlahKamarMandi.text = dataTempo.getInt("detail_jmlKamarMandi", 0).toString() + " kamar mandi"
            luasBangunan.text = dataTempo.getInt("detail_luasBangunan", 0).toString()

            btnBack.setOnClickListener {
                finish()
            }

            btnNext.setOnClickListener {
                if (id != null) {
                    //
                } else {
                    storePropertyLocation(dataTemp)
                }
            }
        }
    }

    private fun storePropertyLocation(dataTemp: FormProperti) {
        val dataTempo = getSharedPreferences("dataTemp", MODE_PRIVATE)

        val request = StorePropertyLocationRequest()
        request.headline = dataTempo.getString("berita", null)
        request.title = dataTempo.getString("judul", null)
        request.property_type_id = dataTempo.getString("tipe_properti", null)
        request.listing_type = dataTempo.getString("tipe_iklan", null)
        request.certificate = dataTempo.getString("tipe_sertifikat", null)
        request.district = dataTempo.getString("kecamatan", null)
        request.city = dataTempo.getString("kota", null)
        request.province = dataTempo.getString("provinsi", null)
        request.longitude = "0.1"
        request.latitude = "0.2"
        request.status = "active"
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
                Log.d("ApiCall StorePropertyLocation", "Response : ${result?.message}")

                if (response.isSuccessful) {
                    var id_properti = result?.data?.id
                    dataTemp.detailProperti?.let { storePropertyDetail(it, id_properti.toString()) }
                } else if (response.code() == 422) {
                    Toast.makeText(this@InputSummary, "Terdapat data yang masih belum diisi", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 401) {
                    Toast.makeText(this@InputSummary, "Sesi telah habis, silakan login ulang", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<StorePropertyLocationResponse>, t: Throwable) {
                Log.e("ApiCall StorePropertyLocation", "Error : ${t.message}")
            }
        })
    }

    private fun storePropertyDetail(dataTemp: FormDetailProperti, id_property: String) {
        val dataTempo = getSharedPreferences("dataTemp", MODE_PRIVATE)
        val request = StorePropertyDetailRequest()
        request.property_id = id_property
        request.price = dataTempo.getInt("detail_harga", 0).toString()
        request.price_type = dataTempo.getString("detail_tipeHarga", null)
        request.description = dataTempo.getString("detail_deskripsi", null)
        request.surface_area = dataTempo.getInt("detail_luasTanah", 0)
        request.building_area = dataTempo.getInt("detail_luasBangunan", 0)
        request.floor = dataTempo.getInt("detail_jmlLantai", 0).toString()
        request.bedroom = dataTempo.getInt("detail_jmlKamar", 0).toString()
        request.bathroom = dataTempo.getInt("detail_jmlKamarMandi", 0).toString()
        request.garage = dataTempo.getString("detail_tempatParkir", null)
        request.year_built = dataTempo.getString("detail_tahunDibangun", null)
        request.position = dataTempo.getString("detail_posisi", null)
        request.power_supply = dataTempo.getString("detail_dayaListrik", null)
        request.condition = dataTempo.getString("detail_kondisi", null)
        request.water_type = dataTempo.getString("detail_tipeAir", null)
        request.facing = dataTempo.getString("detail_menghadap", null)
        request.road_access = dataTempo.getString("detail_aksesJalan", null)
        request.quality = "100"

        val sharedPref = getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(PropertyApi::class.java)

        retro.storePropertyDetail(request).enqueue(object : Callback<StorePropertyDetailResponse> {
            override fun onResponse(
                call: Call<StorePropertyDetailResponse>,
                response: Response<StorePropertyDetailResponse>
            ) {
                val result = response.body()
                Log.d("ApiCall StorePropertyDetail", "Response : ${result?.message}")

                if (response.isSuccessful) {
                    val berhasil = Intent(this@InputSummary, PropertiBerhasil::class.java)
                    startActivity(berhasil)
                } else if (response.code() == 422) {
                    Toast.makeText(this@InputSummary, "Terdapat data yang masih belum diisi", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 401) {
                    Toast.makeText(this@InputSummary, "Sesi telah habis, silakan login ulang", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<StorePropertyDetailResponse>, t: Throwable) {
                Log.e("ApiCall StorePropertyDetail", "Error : ${t.message}")
            }
        })
    }

    private fun updateProperty (id: String) {
        //
    }
}