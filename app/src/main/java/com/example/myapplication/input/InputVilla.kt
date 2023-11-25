package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputVillaBinding

class InputVilla : AppCompatActivity() {
    private lateinit var binding: ActivityInputVillaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputVillaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputVilla, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputVilla, InputLokasi::class.java)
                startActivity(intentToInputLokasi)
            }
        }
    }
}