package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
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
                itemView.setOnClickListener{
                    onClickDisaster(data)
                }
            }
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