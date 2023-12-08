package com.example.myapplication.crud

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.property.DeletePropertyResponse
import com.example.myapplication.api.property.PropertyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyHandler (private val context: Context) {
    fun updateProperty(id : Int) {
        Toast.makeText(context, "Update property ${id}", Toast.LENGTH_SHORT).show()
    }
    fun destroyProperty(id : Int) {
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
}