package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputRuangUsahaBinding

class InputRuangUsaha : AppCompatActivity() {
    private lateinit var binding: ActivityInputRuangUsahaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputRuangUsahaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputRuangUsaha, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}