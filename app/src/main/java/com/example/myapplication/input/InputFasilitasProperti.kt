package com.example.myapplication.input

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.DetailProperti
import com.example.myapplication.Fasilitas
import com.example.myapplication.FasilitasAdapter
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.admin.TypeProperty
import com.example.myapplication.api.admin.response.FacilityResponse
import com.example.myapplication.databinding.ActivityInputFasilitasPropertiBinding
import com.example.myapplication.model.FormProperti
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class InputFasilitasProperti : AppCompatActivity() {
    private lateinit var binding: ActivityInputFasilitasPropertiBinding
    private lateinit var fasilitasAdapter: FasilitasAdapter
    private val listdata = ArrayList<Fasilitas>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputFasilitasPropertiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dataTemp = intent.extras?.get("temp") as FormProperti

        with(binding){
            getFacility()

            fasilitasAdapter = FasilitasAdapter(listdata) { fasilitas, isChecked ->
                Log.d("ItemClick", "Item ${fasilitas.id} clicked, isChecked: $isChecked")
                if (isChecked) {
                    Log.d("CheckboxChanged", "Checkbox for ${fasilitas.id} is checked")
                } else {
                    Log.d("CheckboxChanged", "Checkbox for ${fasilitas.id} is unchecked")
                }
            }

            rvFasilitas.apply {
                adapter = fasilitasAdapter
                layoutManager = GridLayoutManager(this@InputFasilitasProperti, 1)
            }

            btnNext.setOnClickListener {
                val selectedIds = fasilitasAdapter.getSelectedItems()

                val intentToSummary = Intent(this@InputFasilitasProperti, InputSummary::class.java)
                intentToSummary.putExtra("temp", dataTemp as Serializable)
                startActivity(intentToSummary)
            }


            btnBack.setOnClickListener{
                finish()
            }
        }
    }

    private fun getFacility() {
        val sharedPref = getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(TypeProperty::class.java)

        retro.getFacilityType().enqueue(object : Callback<FacilityResponse> {
            override fun onResponse(call: Call<FacilityResponse>, response: Response<FacilityResponse>) {
                val result = response.body()
                Log.d("ApiCall FasilityType", "Response : ${result?.message}")

                val fasilitas = result?.data ?: emptyList()
                if (fasilitas.isNotEmpty()) {
                    listdata.clear() // Bersihkan listdata sebelum menambahkan data baru
                    for (i in fasilitas) {
                        listdata.add(
                            Fasilitas(i.id, i.name, i.category, i.icon)
                        )
                    }
                    fasilitasAdapter.notifyDataSetChanged() // Beritahu adapter bahwa data telah berubah
                } else if (response.code() == 401) {
                    Log.e("ApiCall ListProperty", "onResponse: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<FacilityResponse>, t: Throwable) {
                Log.e("ApiCall FacilityType", "Error : ${t.message}")
            }
        })
    }
}