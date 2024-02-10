package com.example.myapplication.crud

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.property.storeResponse.DeletePropertyResponse
import com.example.myapplication.api.property.PropertyApi
import com.example.myapplication.api.property.storeRequest.ChangeStatusPropertyRequest
import com.example.myapplication.api.property.storeResponse.PropertyDetailResponse
import com.example.myapplication.api.property.storeResponse.RepostPropertyResponse
import com.example.myapplication.api.property.storeResponse.StatusPropertyResponse
import com.example.myapplication.editproject.EditApartemen
import com.example.myapplication.editproject.EditGudang
import com.example.myapplication.input.InputApartemen
import com.example.myapplication.input.InputGudang
import com.example.myapplication.input.InputKantor
import com.example.myapplication.input.InputKondominium
import com.example.myapplication.input.InputPabrik
import com.example.myapplication.input.InputRuangUsaha
import com.example.myapplication.input.InputRuko
import com.example.myapplication.input.InputRumah
import com.example.myapplication.input.InputTanah
import com.example.myapplication.input.InputVilla
import com.example.myapplication.model.FormProperti
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyHandler (private val context: Context) {
    fun updateProperty(id: Int) {
        val sharedPref = context.getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(PropertyApi::class.java)

        retro.getSummaryProperty(id.toString()).enqueue(object : Callback<PropertyDetailResponse> {
            override fun onResponse(
                call: Call<PropertyDetailResponse>,
                response: Response<PropertyDetailResponse>
            ) {
                val result = response.body()
                Log.d("ApiCall SummaryProperty", "Response : ${result?.message}")

                if (response.isSuccessful) {
                    var data = result?.data

                    val sharedPref = context.getSharedPreferences("property_data", AppCompatActivity.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("property_id", data?.id.toString())
                        putString("property_beritaProperti", data?.headline)
                        putString("property_judulProperti", data?.title)
                        putString(
                            "property_tipeProperti",
                            data?.property_type?.property_type_id.toString()
                        )
                        putString("property_tipePropertiTeks", data?.property_type?.name)
                        putString("property_tipeSertifikat", data?.certificate)
                        putString("property_tipeIklan", data?.listing_type)
                        putString("property_provinsi", data?.location?.province)
                        putString("property_kota", data?.location?.city)
                        putString("property_kecamatan", data?.location?.district)
                        putString("property_alamat", data?.location?.address)
                        putString("property_kodePos", data?.location?.postal_code)
                        putString("property_luasBangunan", data?.area)
                        putString("property_jmlKamar", data?.bedroom)
                        putString("property_jmlKamarMandi", data?.bathroom)
                        commit()
                    }

                } else if (response.code() == 401) {
                    Toast.makeText(context, "Sesi telah habis, silakan login ulang", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PropertyDetailResponse>, t: Throwable) {
                Log.e("ApiCall SummaryProperty", "Error: ${t.message}")
            }

        })
    }
    fun destroyProperty(id: Int) {
        val sharedPref = context.getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(PropertyApi::class.java)

        retro.deleteProperty(id.toString()).enqueue(object : Callback<DeletePropertyResponse>{
            override fun onResponse(
                call: Call<DeletePropertyResponse>,
                response: Response<DeletePropertyResponse>
            ) {
                val result = response.body()
                Log.d("DeleteProperty", "Response : ${result?.message}")

                if (response.isSuccessful) {
                    Toast.makeText(context, "Berhasil hapus properti", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 401) {
                    Log.e("DeleteProperty", "onResponse: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<DeletePropertyResponse>, t: Throwable) {
                Log.e("ApiCall DeleteProperty", "onFailure: ${t.message}")
            }

        })
    }

    fun repostProperty(id: Int) {
        val sharedPref = context.getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(PropertyApi::class.java)

        retro.repostProperty(id.toString()).enqueue(object : Callback<RepostPropertyResponse> {
            override fun onResponse(
                call: Call<RepostPropertyResponse>,
                response: Response<RepostPropertyResponse>
            ) {
                var result = response.body()
                Log.d("ApiCall RepostProperty", "Response : ${result?.message}")

                if (response.isSuccessful) {
                    Toast.makeText(context, "Berhasil respost properti", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 401) {
                    Log.e("ApiCall RepostProperty", "onResponse: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<RepostPropertyResponse>, t: Throwable) {
                Log.e("ApiCall RepostProperty", "Error : ${t.message}")
            }
        })
    }

    fun changeStatus(id: Int, currentStatus: String) {
        val request = ChangeStatusPropertyRequest()
        if (currentStatus == "active") {
            request.status = "not_active"
        } else {
            request.status = "active"
        }

        val sharedPref = context.getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(PropertyApi::class.java)

        retro.changeStatusProperty(id.toString(), request).enqueue(object : Callback<StatusPropertyResponse> {
            override fun onResponse(
                call: Call<StatusPropertyResponse>,
                response: Response<StatusPropertyResponse>
            ) {
                var result = response.body()
                Log.d("ApiCall UpdateStatusProperty", "Response : ${result?.message}")

                if (response.isSuccessful) {
                    Toast.makeText(context, "Berhasil ubah status properti", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 401) {
                    Log.e("ApiCall UpdateStatusProperty", "onResponse: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<StatusPropertyResponse>, t: Throwable) {
                Log.e("ApiCall UpdateStatusProperty", "Error : ${t.message}")
            }
        })
    }
}