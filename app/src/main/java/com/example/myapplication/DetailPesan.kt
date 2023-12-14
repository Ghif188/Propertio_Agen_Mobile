package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.message.DetailMessageResponse
import com.example.myapplication.api.message.MessageApi
import com.example.myapplication.databinding.ActivityDetailPesanBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

class DetailPesan : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPesanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPesanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val idPesan = intent.getStringExtra("ID_PESAN")

        with(binding){
            if (idPesan != null) {
                getDetailMessage(idPesan)
            }

            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun getDetailMessage(idPesan : String) {
        val sharedPref = getSharedPreferences("account_data", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")
        val retro = Retrofit(token).getRetroClientInstance().create(MessageApi::class.java)

        retro.getDetailMessage(idPesan).enqueue(object : Callback<DetailMessageResponse> {
            override fun onResponse(
                call: Call<DetailMessageResponse>,
                response: Response<DetailMessageResponse>
            ) {
                var result = response.body()
                Log.d("ApiCall DetailMessage", "Response : ${result?.message}")

                if(response.isSuccessful) {
                    var message = result?.data
                    with(binding) {
                        var waktu = message?.created_at?.let { convertDateStringToFormattedString(it) }
                        txtSubject.text = message?.subject
                        txtWaktu.text = waktu
                        txtPengirim.text = message?.name
                        txtEmail.text = message?.email
                        txtPhone.text = message?.phone
                        txtMessage.text = message?.message
                    }
                } else if (response.code() == 401) {
                    Toast.makeText(this@DetailPesan, "Silakan login ulang", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DetailMessageResponse>, t: Throwable) {
                Log.e("ApiCall DetailMessage", "Error : ${t.message}")
            }
        })
    }
    private fun convertDateStringToFormattedString(dateString: String): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US)
        val outputFormatter = SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.US)

        val date = inputFormatter.parse(dateString)
        return outputFormatter.format(date)
    }

}