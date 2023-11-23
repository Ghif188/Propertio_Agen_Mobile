package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputApartemenBinding
import com.example.myapplication.databinding.ActivityInputLokasiBinding

class InputLokasi : AppCompatActivity() {
    private lateinit var binding: ActivityInputLokasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputLokasiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val tipeProperti = intent.extras?.getString("tipe")
        with(binding){
            btnNext.setOnClickListener {
                if (tipeProperti == "Apartemen"){
                    val intentToApartemen = Intent( this@InputLokasi, InputApartemen::class.java)
                    startActivity(intentToApartemen)
                } else if (tipeProperti == "Gudang"){
                    val intentToGudang = Intent(this@InputLokasi, InputGudang::class.java)
                    startActivity(intentToGudang)
                } else if (tipeProperti == "Kantor"){
                    val intentToKantor = Intent(this@InputLokasi, InputKantor::class.java)
                    startActivity(intentToKantor)
                } else if (tipeProperti == "Kondominium"){
                    val intentToKondominium = Intent(this@InputLokasi, InputKondominium::class.java)
                    startActivity(intentToKondominium)
                } else if (tipeProperti == "Pabrik"){
                    val intentToPabrik = Intent(this@InputLokasi, InputPabrik::class.java)
                    startActivity(intentToPabrik)
                } else if (tipeProperti == "Ruang Usaha"){
                    val intentToRuangUsaha = Intent(this@InputLokasi, InputRuangUsaha::class.java)
                    startActivity(intentToRuangUsaha)
                }else if (tipeProperti == "Ruko"){
                    val intentToRuko = Intent(this@InputLokasi, InputRuko::class.java)
                    startActivity(intentToRuko)
                }else if (tipeProperti == "Rumah"){
                    val intentToRumah = Intent(this@InputLokasi, InputRumah::class.java)
                    startActivity(intentToRumah)
                }else if (tipeProperti == "Tanah"){
                    val intentToTanah = Intent(this@InputLokasi, InputTanah::class.java)
                    startActivity(intentToTanah)
                }else if (tipeProperti == "Villa"){
                    val intentToVilla = Intent(this@InputLokasi, InputVilla::class.java)
                    startActivity(intentToVilla)
                }       
            }
        }
    }
}