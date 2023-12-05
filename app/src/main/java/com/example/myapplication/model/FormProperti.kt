package com.example.myapplication.model

import java.io.Serializable

data class FormProperti (
    var beritaProperti: String? ="",
    var judulProperti: String? ="",
    var tipeProperti: String? ="",
    var tipeSertifikat: String? ="",
    var tipeIklan: String? ="",
    var provinsi: String? ="",
    var kota: String? ="",
    var kecamatan: String? ="",
    var alamat: String? ="",
    var kodePos: String? = "",
    var fotoProperti: List<FormFoto>? = listOf(FormFoto()),
    var linkVideo: String? ="",
    var namaVirtualTour: String? ="",
    var linkVirtualTour: String? ="",
    var fasilitas: List<FormFasilitas>? = listOf(FormFasilitas()),
    var detailProperti: FormDetailProperti? = FormDetailProperti()
) : Serializable