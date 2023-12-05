package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.ActivityInputSummaryBinding
import com.example.myapplication.model.FormProperti

class InputSummary : AppCompatActivity() {
    private lateinit var binding: ActivityInputSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputSummaryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val dataTemp = intent.extras?.get("temp") as FormProperti
        with(binding){
            Log.d("HALO _!!!", dataTemp.toString())
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputSummary, MainActivity::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}