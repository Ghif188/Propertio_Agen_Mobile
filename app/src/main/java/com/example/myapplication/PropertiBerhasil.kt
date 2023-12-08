package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.myapplication.databinding.ActivityPropertiBerhasilBinding

class PropertiBerhasil : AppCompatActivity() {
    private lateinit var binding: ActivityPropertiBerhasilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityPropertiBerhasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val intent = Intent(this@PropertiBerhasil, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}