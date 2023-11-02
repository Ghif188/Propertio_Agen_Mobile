package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(DashboardFragment())

        binding.bottomNavbar.setOnItemReselectedListener {
            when(it.itemId){
                R.id.nav_dash->replaceFragment(DashboardFragment())
                R.id.nav_proyek->replaceFragment(ProyekFragment())
                R.id.nav_pesan->replaceFragment(PesanFragment())
                R.id.nav_profil->replaceFragment(ProfilFragment())
                else->{}
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}