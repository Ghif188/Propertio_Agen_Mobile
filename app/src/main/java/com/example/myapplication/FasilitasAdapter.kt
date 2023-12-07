package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListFasilitasBinding
import com.example.myapplication.model.Fasilitas

typealias onClickFasilitas = (Fasilitas, isChecked: Boolean) -> Unit

class FasilitasAdapter (
    private val listDisaster: List<Fasilitas>,
    private val onClickDisaster: onClickFasilitas) : RecyclerView.Adapter<FasilitasAdapter.ItemDisasterViewHolder>() {
    private val selectedItems = HashSet<Int>()
        inner class ItemDisasterViewHolder(private val binding : ListFasilitasBinding) :
            RecyclerView.ViewHolder(binding.root){
            fun bind (data: Fasilitas){
                with(binding) {
                    namaFasilitas.text = data.name
                    checkBox.isChecked = data.isChecked

                    itemView.setOnClickListener {
                        checkBox.isChecked = !checkBox.isChecked
                        data.isChecked = checkBox.isChecked
                        onClickDisaster(data, checkBox.isChecked)
                    }

                    checkBox.setOnCheckedChangeListener { _, isChecked ->
                        data.isChecked = isChecked
                        if (isChecked) {
                            data.id?.let { selectedItems.add(it) }
                        } else {
                            selectedItems.remove(data.id)
                        }

                        onClickDisaster(data, isChecked)
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

    fun getSelectedItems(): Set<Int> {
        return selectedItems
    }
}