package com.example.myapplication.model

data class FormProperti (
    val beritaProperti: String? ="",
    val judulProperti: String? ="",
    val tipeProperti: String? ="",
    val tipeSertifikat: String? ="",
    val tipeIklan: String? ="",
    val provinsi: String? ="",
    val kota: String? ="",
    val kecamatan: String? ="",
    val alamat: String? ="",
    val kodePos: String? = "",
    val fotoProperti: List<FormFoto>? = listOf(FormFoto()),
    val linkVideo: String? ="",
    val namaVirtualTour: String? ="",
    val linkVirtualTour: String? ="",
    val fasilitas: List<FormFasilitas>? = listOf(FormFasilitas())
)