package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityDetailPropertiBinding

class DetailProperti : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPropertiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailPropertiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val idProperti = intent.getStringExtra("ID_PROP")

        with(binding){
            Toast.makeText(this@DetailProperti, "Id Properti : ${idProperti}", Toast.LENGTH_SHORT).show()
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}