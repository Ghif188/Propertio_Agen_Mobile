package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputPabrikBinding

class InputPabrik : AppCompatActivity() {
    private lateinit var binding: ActivityInputPabrikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputPabrikBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputPabrik, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}