package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDetailPropertiBinding
import com.example.myapplication.databinding.ActivityInputSummaryBinding

class InputSummary : AppCompatActivity() {
    private lateinit var binding: ActivityInputSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputSummaryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputSummary, MainActivity::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}