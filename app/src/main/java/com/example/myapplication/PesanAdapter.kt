package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListPesanBinding
import com.example.myapplication.model.Pesan
import com.squareup.picasso.Picasso

typealias onClickPesan = (Pesan) -> Unit

class PesanAdapter (
    private val listDisaster: List<Pesan>,
    private val onClickDisaster: onClickPesan) : RecyclerView.Adapter<PesanAdapter.ItemDisasterViewHolder>() {
    inner class ItemDisasterViewHolder(private val binding : ListPesanBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind (data: Pesan){
            with(binding){
                Picasso.get().load(data.imgPengirim).into(imgProfil)
                subjekPesan.text = data.judulPesan
                namaPengirim.text = data.namaPengirim
                waktu.text = data.jamPesan
                itemView.setOnClickListener{
                    onClickDisaster(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDisasterViewHolder {
        val binding = ListPesanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemDisasterViewHolder(binding)
    }

    override fun getItemCount(): Int = listDisaster.size

    override fun onBindViewHolder(holder: ItemDisasterViewHolder, position: Int) {
        holder.bind(listDisaster[position])
    }
}