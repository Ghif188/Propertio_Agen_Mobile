package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.crud.PropertyHandler
import com.example.myapplication.databinding.ListPropertyBinding
import com.example.myapplication.model.Properti
import com.squareup.picasso.Picasso

typealias onClickDisaster = (Properti) -> Unit

class PropertiAdapter (
    private val onClickDisaster: onClickDisaster,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<PropertiAdapter.ItemDisasterViewHolder>() {
    private var listDisaster: List<Properti> = listOf()

    fun setData(list: List<Properti>){
        listDisaster = list
        notifyDataSetChanged()
    }

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
                btnDetail.setOnClickListener {
                    onClickDisaster(data)
                    onItemClick.invoke(data.idProperti.toString())
                }
                btnRepost.setOnClickListener {
                    data.idProperti?.let { it2 -> repostProperty(it2) }
                }
                btnMenu.setOnClickListener {
                    data.idProperti?.let { it1 -> popupMenus(it, it1) }
                }
            }
        }
        private fun popupMenus(v: View, propertyId: Int){
            val popupMenus = PopupMenu(itemView.context,v)
            popupMenus.inflate(R.menu.property_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.edit_properti->{
                        editProperty(propertyId)
                        true
                    }
                    R.id.hapus_properti->{
                        deleteProperty(propertyId)
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

        private fun editProperty(id: Int) {
            val property = PropertyHandler(itemView.context)
            property.updateProperty(id)
        }
        private fun deleteProperty(id: Int) {
            val property = PropertyHandler(itemView.context)
            property.destroyProperty(id)
        }

        private fun repostProperty(id: Int) {
            val property = PropertyHandler(itemView.context)
            property.repostProperty(id)
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