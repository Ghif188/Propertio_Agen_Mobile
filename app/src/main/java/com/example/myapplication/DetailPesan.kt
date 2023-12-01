package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDetailPesanBinding

class DetailPesan : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPesanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPesanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnBack.setOnClickListener {
                val intentToInputVideo = Intent(this@DetailPesan, MainActivity::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}