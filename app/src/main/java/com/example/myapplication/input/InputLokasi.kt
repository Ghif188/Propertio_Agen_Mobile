package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myapplication.api.admin.location.DistrictsResponse
import com.example.myapplication.api.admin.location.ProvinceResponse
import com.example.myapplication.api.admin.location.RegenciesResponse
import com.example.myapplication.api.admin.location.RetroLocation
import com.example.myapplication.databinding.ActivityInputLokasiBinding
import com.example.myapplication.model.FormProperti
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class InputLokasi : AppCompatActivity() {
    private lateinit var binding: ActivityInputLokasiBinding
    private var selected_province = ""
    private var selected_regency = ""
    private var selected_district = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputLokasiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dataTemp = intent.extras?.get("temp") as FormProperti

        with(binding){
            listKabupaten.isEnabled = false
            val defaultRegency = mutableListOf("Pilih Kabupaten")
            val regencyAdapter = ArrayAdapter(this@InputLokasi, android.R.layout.simple_spinner_item, defaultRegency)
            regencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            listKabupaten.adapter = regencyAdapter
          
            listKecamatan.isEnabled = false
            val defaultDistrict = mutableListOf("Pilih Kecamatan")
            val districtAdapter = ArrayAdapter(this@InputLokasi, android.R.layout.simple_spinner_item, defaultDistrict)
            districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            listKecamatan.adapter = districtAdapter

            fetchProvince()
            btnNext.setOnClickListener {
                if (selected_province.isEmpty()) {
                    Toast.makeText(this@InputLokasi, "Silakan pilih provinsi terlebih dahulu", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (selected_regency.isEmpty()) {
                    Toast.makeText(this@InputLokasi, "Silakan pilih kabupaten terlebih dahulu", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (selected_district.isEmpty()) {
                    Toast.makeText(this@InputLokasi, "Silakan pilih kecamatan terlebih dahulu", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val intent: Intent
                when (dataTemp.tipePropertiTeks) {
                    "Apartemen" -> {intent = Intent(this@InputLokasi, InputApartemen::class.java)}
                    "Gudang" -> {intent = Intent(this@InputLokasi, InputGudang::class.java)}
                    "Perkantoran" -> {intent = Intent(this@InputLokasi, InputKantor::class.java)}
                    "Kondominium" -> {intent = Intent(this@InputLokasi, InputKondominium::class.java)}
                    "Pabrik" -> {intent = Intent(this@InputLokasi, InputPabrik::class.java)}
                    "Ruang usaha" -> {intent = Intent(this@InputLokasi, InputRuangUsaha::class.java)}
                    "Ruko" -> {intent = Intent(this@InputLokasi, InputRuko::class.java)}
                    "Rumah" -> {intent = Intent(this@InputLokasi, InputRumah::class.java)}
                    "Tanah" -> {intent = Intent(this@InputLokasi, InputTanah::class.java)}
                    "Villa" -> {intent = Intent(this@InputLokasi, InputVilla::class.java)}
                    else -> {
                        Toast.makeText(this@InputLokasi, "Pilihan tipe properti tidak tersedia", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
                dataTemp.provinsi = selected_province
                dataTemp.kota = selected_regency
                dataTemp.kecamatan = selected_district
                dataTemp.alamat = alamat.text.toString()
                dataTemp.kodePos = kodePos.text.toString()

                intent.putExtra("temp", dataTemp as Serializable)
                startActivity(intent)
            }

            btnBack.setOnClickListener{
                finish()
            }
        }
    }

    private fun fetchProvince() {
        val retro = RetroLocation().getInstance()

        retro.getProvinces().enqueue(object : Callback<List<ProvinceResponse.Province>> {
            override fun onResponse(
                call: Call<List<ProvinceResponse.Province>>,
                response: Response<List<ProvinceResponse.Province>>
            ) {
                val result = response.body()
                Log.d("ApiCall ListProvince", "Success fetching data province")

                if (response.isSuccessful) {
                    val provinceNames = result?.mapNotNull { it?.name } ?: emptyList()
                    val provinceNamesWithDefault = mutableListOf("Pilih Provinsi")
                    provinceNamesWithDefault.addAll(provinceNames)

                    val adapter = ArrayAdapter(this@InputLokasi, android.R.layout.simple_spinner_item, provinceNamesWithDefault)

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    binding.listProvinsi.adapter = adapter

                    binding.listProvinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parentView: AdapterView<*>,
                            selectedItemView: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (position != 0) {
                                var selectedProvinceId = result?.get(position - 1)?.id.toString()
                                selected_province = result?.get(position - 1)?.name.toString()

                                resetRegencyAndDistrictForms()

                                binding.listKabupaten.isEnabled = true
                                fetchRegencies(selectedProvinceId)
                            }
                        }

                        override fun onNothingSelected(parentView: AdapterView<*>) {}
                    }
                }
            }

            override fun onFailure(call: Call<List<ProvinceResponse.Province>>, t: Throwable) {
                Log.e("ApiCall ListProvince", "Error: ${t.message}")
            }
        })
    }

    private fun fetchRegencies(selectedProvinceId: String) {
        val retro = RetroLocation().getInstance()

        retro.getRegency(selectedProvinceId).enqueue(object : Callback<List<RegenciesResponse.Regency>> {
            override fun onResponse(
                call: Call<List<RegenciesResponse.Regency>>,
                response: Response<List<RegenciesResponse.Regency>>
            ) {
                val result = response.body()
                Log.d("ApiCall ListRegency", "Success fetching data regency")

                if (response.isSuccessful) {
                    val regencyNames = result?.mapNotNull { it?.name } ?: emptyList()
                    val regencyNamesWithDefault = mutableListOf("Pilih Kabupaten")
                    regencyNamesWithDefault.addAll(regencyNames)

                    val adapter = ArrayAdapter(this@InputLokasi, android.R.layout.simple_spinner_item, regencyNamesWithDefault)

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    binding.listKabupaten.adapter = adapter

                    binding.listKabupaten.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parentView: AdapterView<*>,
                            selectedItemView: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (position != 0) {
                                var selectedRegencyId = result?.get(position - 1)?.id.toString()
                                selected_regency = result?.get(position - 1)?.name.toString()

                                binding.listKecamatan.isEnabled = true
                                fetchDistrict(selectedRegencyId)
                            }
                        }
                        override fun onNothingSelected(parentView: AdapterView<*>) {}
                    }
                }
            }

            override fun onFailure(call: Call<List<RegenciesResponse.Regency>>, t: Throwable) {
                Log.e("ApiCall ListRegency", "Error: ${t.message}")
            }

        })
    }

    private fun fetchDistrict(selectedRegencyId: String) {
        val retro = RetroLocation().getInstance()

        retro.getDistrict(selectedRegencyId).enqueue(object : Callback<List<DistrictsResponse.District>>{
            override fun onResponse(
                call: Call<List<DistrictsResponse.District>>,
                response: Response<List<DistrictsResponse.District>>
            ) {
                val result = response.body()

                if (response.isSuccessful) {
                    val districtNames = result?.mapNotNull { it?.name } ?: emptyList()
                    val districtNamesWithDefault = mutableListOf("Pilih Kecamatan")
                    districtNamesWithDefault.addAll(districtNames)

                    val adapter = ArrayAdapter(this@InputLokasi, android.R.layout.simple_spinner_item, districtNamesWithDefault)

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    binding.listKecamatan.adapter = adapter

                    binding.listKecamatan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parentView: AdapterView<*>,
                            selectedItemView: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (position != 0) {
                                selected_district = result?.get(position - 1)?.name.toString()
                            }
                        }
                        override fun onNothingSelected(parentView: AdapterView<*>) {}
                    }
                }
            }

            override fun onFailure(call: Call<List<DistrictsResponse.District>>, t: Throwable) {
                Log.e("ApiCall ListDistrict", "Error: ${t.message}")
            }
        })
    }
    private fun resetRegencyAndDistrictForms() {
        binding.listKabupaten.setSelection(0)
        binding.listKabupaten.isEnabled = false

        binding.listKecamatan.setSelection(0)
        binding.listKecamatan.isEnabled = false
    }

}