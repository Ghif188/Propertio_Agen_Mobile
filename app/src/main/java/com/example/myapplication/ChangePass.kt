package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityChangePassBinding
import com.example.myapplication.input.InputLokasi
import com.example.myapplication.input.InputVideo

class ChangePass : AppCompatActivity() {
    private lateinit var binding: ActivityChangePassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChangePassBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnBack.setOnClickListener{
                finish()
            }
            btnBatal.setOnClickListener{
                finish()
            }
        }
    }
}