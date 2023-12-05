package com.example.myapplication.input

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityInputVideoBinding
import com.example.myapplication.model.FormFoto
import com.example.myapplication.model.FormProperti
import java.io.Serializable

class InputVideo : AppCompatActivity() {
    private lateinit var binding: ActivityInputVideoBinding
    private var selectedImageUri: Uri? = null
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            showSelectedImage(selectedImageUri)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputVideoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dataTemp = intent.extras?.get("temp") as FormProperti

        with(binding){
            btnUploadImg.setOnClickListener {
                getContent.launch("image/*")
            }

            delAllPhotos.setOnClickListener {
                removeAllPhotos()
            }

            btnNext.setOnClickListener {
                dataTemp.namaVirtualTour = namaVirtualTour.text.toString()
                dataTemp.linkVideo = linkVideo.text.toString()
                dataTemp.linkVirtualTour = linkVirtualTour.text.toString()
                dataTemp.fotoProperti = listOf(FormFoto())
                val intentToInputFasilitas = Intent(this@InputVideo, InputFasilitasProperti::class.java)
                intentToInputFasilitas.putExtra("temp", dataTemp as Serializable)
                startActivity(intentToInputFasilitas)
            }
            btnBack.setOnClickListener{
                finish()
            }
        }
    }

    private fun showSelectedImage(uri: Uri?) {
        binding.nullPhotos.visibility = View.GONE

        val containerLayout = LinearLayout(this)
        containerLayout.orientation = LinearLayout.VERTICAL
        containerLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val frameLayout = FrameLayout(this)
        frameLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0,0,0,10)
        }

        val imageView = ImageView(this)
        imageView.layoutParams = FrameLayout.LayoutParams(350, 350).apply {
            setMargins(0, 0, 20, 0)
        }
        imageView.background = getDrawable(R.drawable.bg_btn_grey)
        imageView.setImageURI(uri)
        frameLayout.addView(imageView)

        val deleteImageView = ImageView(this)
        deleteImageView.layoutParams = FrameLayout.LayoutParams(70, 70).apply {
            gravity = Gravity.END or Gravity.TOP
        }
        deleteImageView.setImageResource(R.drawable.ic_cancel)
        frameLayout.addView(deleteImageView)

        containerLayout.addView(frameLayout)

        val captionEditText = EditText(this)
        captionEditText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        captionEditText.hint = "Keterangan"
        captionEditText.setBackgroundResource(R.drawable.bg_btn_grey)
        captionEditText.gravity = Gravity.CENTER_HORIZONTAL
        containerLayout.addView(captionEditText)

        binding.imageContainer.addView(containerLayout)

        deleteImageView.setOnClickListener {
            removeSelectedImage(containerLayout)
        }
    }

    private fun removeSelectedImage(selectedContainerLayout: LinearLayout) {
        binding.imageContainer.removeView(selectedContainerLayout)
        selectedImageUri = null

        if (binding.imageContainer.childCount == 1) {
            binding.nullPhotos.visibility = View.VISIBLE
        }
    }

    private fun removeAllPhotos() {
        val nullPhotosIndex = binding.imageContainer.indexOfChild(binding.nullPhotos)
        binding.imageContainer.removeAllViews()
        if (nullPhotosIndex != -1) {
            binding.imageContainer.addView(binding.nullPhotos, nullPhotosIndex)
        }
        selectedImageUri = null
        if (binding.imageContainer.childCount == 1) {
            binding.nullPhotos.visibility = View.VISIBLE
        }
    }
}
