package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.api.Retrofit
import com.example.myapplication.api.auth.UserApi
import com.example.myapplication.api.auth.UserRequest
import com.example.myapplication.api.auth.UserResponse
import com.example.myapplication.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            linkForgotPassword.setOnClickListener {
                Toast.makeText(this@Login, "Fitur dalam tahap pengembangan", Toast.LENGTH_SHORT).show()
            }
            register.setOnClickListener{
                val intentToRegister = Intent(this@Login, Register::class.java)
                startActivity(intentToRegister)
            }
            btnLogin.setOnClickListener {
                if (email.text.toString().isEmpty()) {
                    email.error = "Silakan masukkan email"
                    return@setOnClickListener
                }
                if (kataSandi.text.toString().isEmpty()) {
                    kataSandi.error = "Silakan masukkan kata sandi"
                    return@setOnClickListener
                }

                login()
            }
            // hanya untuk developer
            btnLoginDev.setOnClickListener{
                login("agent@mail.com", "11111111")
            }
        }
    }

    private fun login(email: String? = null, password: String? = null) {
        val request = UserRequest()
        request.email = email ?: binding.email.text.toString()
        request.password = password ?: binding.kataSandi.text.toString()

        val retro = Retrofit(null).getRetroClientInstance().create(UserApi::class.java)
        retro.login(request).enqueue(object: Callback<UserResponse> {
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

                        val intentToMainActivity = Intent(this@Login, MainActivity::class.java)
                        intentToMainActivity.putExtra("toastMessage", "Berhasil Login")
                        startActivity(intentToMainActivity)
                        finish()
                    }
                    else {
                        Toast.makeText(this@Login, "Email yang Anda gunakan bukan akun agent", Toast.LENGTH_SHORT).show()
                    }
                } else if (response.code() == 401) {
                    Toast.makeText(this@Login, "Email atau password salah", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("LoginActivity", "Error: ${t.message}")
            }

        })
    }
}