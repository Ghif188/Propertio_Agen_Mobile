package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputRukoBinding

class InputRuko : AppCompatActivity() {
    private lateinit var binding: ActivityInputRukoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputRukoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputRuko, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputRuko, InputLokasi::class.java)
                startActivity(intentToInputLokasi)
            }
        }
    }
}