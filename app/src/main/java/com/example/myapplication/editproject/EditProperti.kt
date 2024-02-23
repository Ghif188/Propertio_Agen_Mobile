package com.example.myapplication.editproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityEditPropertiBinding
import com.example.myapplication.model.FormProperti

class EditProperti : AppCompatActivity() {
    private lateinit var binding: ActivityEditPropertiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPropertiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnBack.setOnClickListener { finish() }

            btnNext.setOnClickListener {
                val intent = Intent(this@EditProperti, EditLokasi::class.java)
                startActivity(intent)
            }
        }
    }
}