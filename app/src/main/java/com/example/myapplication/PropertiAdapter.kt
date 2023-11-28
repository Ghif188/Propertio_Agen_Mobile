package com.example.myapplication

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListPropertyBinding
import com.squareup.picasso.Picasso

typealias onClickDisaster = (Properti) -> Unit

class PropertiAdapter (
    private val listDisaster: List<Properti>,
    private val onClickDisaster: onClickDisaster) : RecyclerView.Adapter<PropertiAdapter.ItemDisasterViewHolder>() {
    inner class ItemDisasterViewHolder(private val binding : ListPropertyBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind (data: Properti){
            with(binding){
                Picasso.get().load(data.imgProperti).into(thumbnail)
                judul.text = data.judulProperti
                val hargaTxt = "Rp. " + data.hargaProperti.toString()
                harga.text = hargaTxt
                status.text = data.statusProperti
                kodeProperti.text = data.kodeProperti
                tipeProperti.text = data.tipeProperti
                val lihatTxt = data.dilihat.toString() + " dilihat"
                pelihat.text = lihatTxt
                val sukaTxt = data.disukai.toString() + " disukai"
                penyuka.text = sukaTxt
                alamat.text = data.lokasiProperti
                btnDetail.setOnClickListener{
                    onClickDisaster(data)
                }
                btnMenu.setOnClickListener {
                    popupMenus(it)
                }
            }
        }
        private fun popupMenus(v: View){
            val popupMenus = PopupMenu(itemView.context,v)
            popupMenus.inflate(R.menu.property_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.edit_properti->{
                        Toast.makeText(itemView.context, "HALO", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.hapus_properti->{
                        Toast.makeText(itemView.context, "HALO3", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else->true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDisasterViewHolder {
        val binding = ListPropertyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemDisasterViewHolder(binding)
    }

    override fun getItemCount(): Int = listDisaster.size

    override fun onBindViewHolder(holder: ItemDisasterViewHolder, position: Int) {
        holder.bind(listDisaster[position])
    }
}