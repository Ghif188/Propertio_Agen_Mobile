package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDetailPropertiBinding

class DetailProperti : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPropertiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPropertiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnBack.setOnClickListener {
                val intentToInputVideo = Intent(this@DetailProperti, MainActivity::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}