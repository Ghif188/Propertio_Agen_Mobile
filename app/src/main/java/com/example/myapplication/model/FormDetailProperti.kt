package com.example.myapplication.model

import java.io.Serializable

data class FormDetailProperti (
    var deskripsi: String? ="",
    var luasTanah: Int? =0,
    var luasBangunan: Int? =0,
    var jmlKamarMandi: Int? =0,
    var tempatParkir: String?="",
    var tahunDibangun: Int?=0,
    var harga: Int?=0,
    var tipeHarga: String?="",
    var dayaListrik: String?="",
    var kondisi: String?="",
    var tipeAir: String?="",
    var interior: String?="",
    var menghadap: String?="",
    var posisi: String?="",
    var aksesJalan: String?="",
    var jmlLantai: Int?=0,
    var jmlKamar: Int?=0,
) : Serializable