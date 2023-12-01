package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputVideoBinding
import com.example.myapplication.input.InputFasilitasProperti

class InputVideo : AppCompatActivity() {
    private lateinit var binding: ActivityInputVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputVideoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputVideo, InputFasilitasProperti::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}