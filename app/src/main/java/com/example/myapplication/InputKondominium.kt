package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputKondominiumBinding

class InputKondominium : AppCompatActivity() {
    private lateinit var binding: ActivityInputKondominiumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputKondominiumBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputKondominium, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}