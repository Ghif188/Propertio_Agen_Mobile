package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListFasilitasBinding
import com.example.myapplication.databinding.ListPesanBinding
import com.squareup.picasso.Picasso

typealias onClickFasilitas = (Fasilitas) -> Unit

class FasilitasAdapter (
    private val listDisaster: List<Fasilitas>,
    private val onClickDisaster: onClickFasilitas) : RecyclerView.Adapter<FasilitasAdapter.ItemDisasterViewHolder>() {
        inner class ItemDisasterViewHolder(private val binding : ListFasilitasBinding) :
            RecyclerView.ViewHolder(binding.root){
            fun bind (data: Fasilitas){
                with(binding){
                    namaFasilitas.text = data.nama_fasilitas
                    itemView.setOnClickListener{
                        onClickDisaster(data)
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDisasterViewHolder {
            val binding = ListFasilitasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemDisasterViewHolder(binding)
        }

        override fun getItemCount(): Int = listDisaster.size

        override fun onBindViewHolder(holder: ItemDisasterViewHolder, position: Int) {
            holder.bind(listDisaster[position])
        }
}