package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.admin.location.ProvinceResponse
import com.example.myapplication.api.admin.location.RegenciesResponse
import com.example.myapplication.api.admin.location.RetroLocation
import com.example.myapplication.api.auth.UserApi
import com.example.myapplication.api.auth.UserRequest
import com.example.myapplication.api.auth.UserResponse
import com.example.myapplication.api.register.RegisterApi
import com.example.myapplication.api.register.RegisterResponse
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.squareup.picasso.Picasso
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var selected_province = ""
    private var selected_regency = ""
    private var selectedImageUri: Uri? = null
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            showSelectedImage(selectedImageUri)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val country = resources.getStringArray(R.array.country)
        with(binding){
            kota.isEnabled = false
            val defaultRegency = mutableListOf("Pilih Kota / Kabupaten")
            val regencyAdapter = ArrayAdapter(this@Register, android.R.layout.simple_spinner_item, defaultRegency)
            regencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            kota.adapter = regencyAdapter

            fetchProvince()

            val countriesAdapter = ArrayAdapter(this@Register,
                android.R.layout.simple_spinner_dropdown_item,
                country)
            countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            txtLogin.setOnClickListener {
                val loginPage = Intent(this@Register, Login::class.java)
                startActivity(loginPage)
            }
            btnUploadImg.setOnClickListener {
                getContent.launch("image/*")
            }
            btnRegister.setOnClickListener {
                val email = binding.email.text.toString()
                val namaDepan = binding.namaDepan.text.toString()
                val namaBelakang = binding.namaBelakang.text.toString()
                val kataSandi = binding.kataSandi.text.toString()
                val konfirmasiSandi = binding.konfirSandi.text.toString()
                val nomor = binding.noTelp.text.toString()
                val alamat = binding.alamat.text.toString()
                if (email == "" || namaDepan == "" || namaBelakang == "" ||
                    kataSandi == "" || konfirmasiSandi == "" || nomor == "" || selected_regency == "" || selected_province == "" || alamat == ""){
                    if (email == ""){
                        txtEmail.visibility = View.VISIBLE
                    } else {
                        txtEmail.visibility = View.GONE
                    }
                    if (namaDepan == ""){
                        txtNama.visibility = View.VISIBLE
                    } else {
                        txtNama.visibility = View.GONE
                    }
                    if (namaBelakang == ""){
                        txtNamaBelakang.visibility = View.VISIBLE
                    } else {
                        txtNamaBelakang.visibility = View.GONE
                    }
                    if (kataSandi == ""){
                        txtPassword.visibility = View.VISIBLE
                    } else {
                        txtPassword.visibility = View.GONE
                    }
                    if (konfirmasiSandi == ""){
                        txtKonfirSandi.visibility = View.VISIBLE
                    } else {
                        txtKonfirSandi.visibility = View.GONE
                    }
                    if (nomor == ""){
                        txtNomor.visibility = View.VISIBLE
                    } else {
                        txtNomor.visibility = View.GONE
                    }
                    if (selected_regency == ""){
                        txtKota.visibility = View.VISIBLE
                    } else {
                        txtKota.visibility = View.GONE
                    }
                    if (selected_province == ""){
                        txtProvinsi.visibility = View.VISIBLE
                    } else {
                        txtProvinsi.visibility = View.GONE
                    }
                    if (alamat == ""){
                        txtAlamat.visibility = View.VISIBLE
                    } else {
                        txtAlamat.visibility = View.GONE
                    }
                } else if (kataSandi != konfirmasiSandi){
                    txtKonfirSandi.text = "Konfirmasi Sandi Harus Sama Dengan Kata Sandi"
                    txtKonfirSandi.visibility = View.VISIBLE
                } else {
                    register()
                }
            }
        }
    }
    private fun register() {
        val email = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.email.text.toString())
        val namaDepan = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.namaDepan.text.toString())
        val namaBelakang = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.namaBelakang.text.toString())
        val kataSandi = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.kataSandi.text.toString())
        val konfirmasiSandi = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.konfirSandi.text.toString())
        val nomorInt = binding.noTelp.text.toString().toInt()
        val nomorFormat = "62$nomorInt"
        val nomor = RequestBody.create("text/plain".toMediaTypeOrNull(), nomorFormat)
        val kota = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.kota.selectedItem.toString())
        val provinsi = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.provinsi.selectedItem.toString())
        val alamat = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.alamat.text.toString())
        val role = RequestBody.create("text/plain".toMediaTypeOrNull(), "agent")
        val status = RequestBody.create("text/plain".toMediaTypeOrNull(), "active")
        val retro = Retrofit(null).getRetroClientInstance().create(RegisterApi::class.java)
        if (selectedImageUri != null){
            val file = uriToFile(selectedImageUri!!)
            val fileRequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
            val filePhoto = MultipartBody.Part.createFormData("picture_profile_file", file.name, fileRequestBody)
            retro.registerFoto(email, kataSandi, konfirmasiSandi, namaDepan, namaBelakang, nomor, kota, provinsi, role, status, alamat, filePhoto).enqueue(object: Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    val user = response.body()
                    Log.d("Register", "Response: ${user?.message}")
                    if(response.isSuccessful) {
                        if(user?.data == null) {
                            Log.e("RegisterActivity", "Error: ${user?.message}")
                            return
                        }
                    } else if (response.code() == 401) {
                        Toast.makeText(this@Register, "Seluruh Inputan Wajib Terisi", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Log.e("res2", user?.data.toString())
                        response.errorBody()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.e("RegisterActivity", "Error: ${t.message}")
                }
            })
        } else {
            retro.register(email, kataSandi, konfirmasiSandi, namaDepan, namaBelakang, nomor, kota, provinsi, role, status, alamat).enqueue(object: Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    val user = response.body()
                    Log.d("Register", "Response: ${user?.message}")
                    if(response.isSuccessful) {
                        val request = UserRequest()
                        request.email = binding.email.text.toString()
                        request.password = binding.kataSandi.text.toString()
                        val retroLogin = Retrofit(null).getRetroClientInstance().create(UserApi::class.java)
                        retroLogin.login(request).enqueue(object: Callback<UserResponse> {
                            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                                val user = response.body()
                                Log.d("LoginActivity", "Response: ${user?.message}")
                                if(response.isSuccessful) {
                                    if(user?.data?.token == null) {
                                        Log.e("LoginActivity", "Error: ${user?.message}")
                                        return
                                    }
                                    if (user.data!!.user!!.role == "agent") {
                                        val sharedPreferences = getSharedPreferences("account_data", MODE_PRIVATE)
                                        with(sharedPreferences.edit()) {
                                            putString("token", user.data!!.token)
                                            commit()
                                        }

                                        val tokenFromPrefs = sharedPreferences.getString("token", null)
                                        Log.d("LoginActivity", "Token from prefs: $tokenFromPrefs")

                                        val intentToMainActivity = Intent(this@Register, MainActivity::class.java)
                                        intentToMainActivity.putExtra("toastMessage", "Berhasil Login")
                                        startActivity(intentToMainActivity)
                                        finish()
                                    }
                                    else {
                                        Toast.makeText(this@Register, "Email yang Anda gunakan bukan akun agent", Toast.LENGTH_SHORT).show()
                                    }
                                } else if (response.code() == 401) {
                                    Toast.makeText(this@Register, "Email atau password salah", Toast.LENGTH_SHORT).show()
                                }
                            }
                            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                                Log.e("LoginActivity", "Error: ${t.message}")
                            }
                        })
                        if(user?.data == null) {
                            Log.e("RegisterActivity", "Error: ${user?.message}")
                            return
                        }
                    } else if (response.code() == 401) {
                        Toast.makeText(this@Register, "Seluruh Inputan Wajib Terisi", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Log.e("res2", user?.data.toString())
                        response.errorBody()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.e("RegisterActivity", "Error: ${t.message}")
                }
            })
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

                    val adapter = ArrayAdapter(this@Register, android.R.layout.simple_spinner_item, provinceNamesWithDefault)

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    binding.provinsi.adapter = adapter

                    binding.provinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parentView: AdapterView<*>,
                            selectedItemView: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (position != 0) {
                                var selectedProvinceId = result?.get(position - 1)?.id.toString()
                                selected_province = result?.get(position - 1)?.name.toString()

                                binding.kota.isEnabled = true
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

                    val adapter = ArrayAdapter(this@Register, android.R.layout.simple_spinner_item, regencyNamesWithDefault)

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    binding.kota.adapter = adapter

                    binding.kota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parentView: AdapterView<*>,
                            selectedItemView: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (position != 0) {
                                var selectedRegencyId = result?.get(position - 1)?.id.toString()
                                selected_regency = result?.get(position - 1)?.name.toString()
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
    private fun showSelectedImage(uri: Uri?) {
        Picasso.get().load(uri).into(binding.fotoProfil)
        binding.fotoGroup.visibility = View.VISIBLE
        binding.txtBtnProfil.text = "Ubah Foto"
        binding.btnCancel.setOnClickListener {
            selectedImageUri = null
            binding.fotoGroup.visibility = View.GONE
            binding.txtBtnProfil.text = "Tambahkan"
        }
        Log.d("HELLO", selectedImageUri.toString())
    }
    private fun uriToFile(uri: Uri): File {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)
        val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) ?: 0
        cursor?.moveToFirst()
        val filePath = cursor?.getString(columnIndex) ?: ""
        cursor?.close()
        return File(filePath)
    }
}