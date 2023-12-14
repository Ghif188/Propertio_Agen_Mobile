package com.example.myapplication.project

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.message.AllMessagesResponse
import com.example.myapplication.api.message.MessageApi
import com.example.myapplication.model.Pesan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

class PesanViewModel : ViewModel() {
    val messageListLiveData: MutableLiveData<List<Pesan>> = MutableLiveData()

    fun getMessageList(api: MessageApi) {
        api.getMessage().enqueue(object : Callback<AllMessagesResponse> {
            override fun onResponse(
                call: Call<AllMessagesResponse>,
                response: Response<AllMessagesResponse>
            ) {
                val result = response.body()
                Log.d("ApiCall Message", "Response : ${result?.message}")

                if (response.isSuccessful) {
                    val message = result?.data ?: emptyList()
                    if (message.isNotEmpty()) {
                        val listMessage = mutableListOf<Pesan>()
                        for (i in message) {
                            val timestamp = i.updated_at?.let {
                                convertDateStringToFormattedString(
                                    it
                                )
                            }
                            listMessage.add(
                                Pesan(
                                    idPesan = i.id.toString(),
                                    namaPengirim = i.name,
                                    judulPesan = i.subject,
                                    jamPesan = timestamp.toString(),
                                    imgPengirim = null
                                )
                            )
                        }
                        messageListLiveData.value = listMessage
                    }
                } else if (response.code() == 401) {
                    Log.e("ApiCall Message", "onResponse: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<AllMessagesResponse>, t: Throwable) {
                Log.e("PesanViewModel", "Error: ${t.message}")
            }

        })
    }

    private fun convertDateStringToFormattedString(dateString: String): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US)
        val outputFormatter = SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.US)

        val date = inputFormatter.parse(dateString)
        return outputFormatter.format(date)
    }
}