package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputKantorBinding

class InputKantor : AppCompatActivity() {
    private lateinit var binding: ActivityInputKantorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputKantorBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputKantor, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}