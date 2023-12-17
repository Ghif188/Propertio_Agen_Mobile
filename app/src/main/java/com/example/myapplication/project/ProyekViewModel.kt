import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.property.PropertyApi
import com.example.myapplication.api.property.storeResponse.PropertyResponse
import com.example.myapplication.model.Properti
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProyekViewModel : ViewModel() {
    val propertyListLiveData: MutableLiveData<List<Properti>> = MutableLiveData()

    fun getPropertyList(api: PropertyApi) {
        api.getPropertyList().enqueue(object : Callback<PropertyResponse> {
            override fun onResponse(
                call: Call<PropertyResponse>,
                response: Response<PropertyResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    val properti = result?.data ?: emptyList()
                    if (properti.isNotEmpty()) {
                        val listData = mutableListOf<Properti>()
                        for (i in properti) {
                            val addres = i.address
                            val full_address =
                                addres?.address + " " + addres?.district + " " + addres?.city + " " + addres?.province
                            listData.add(
                                Properti(
                                    idProperti = i.id,
                                    imgProperti = i.photo,
                                    judulProperti = i.title,
                                    hargaProperti = i.price,
                                    lokasiProperti = full_address,
                                    ketProperti = "keterangan",
                                    tipeProperti = i.property_type,
                                    statusProperti = i.status,
                                    kodeProperti = i.property_code
                                )
                            )
                        }
                        propertyListLiveData.value = listData
                    }
                } else if (response.code() == 401) {
                    Log.e("ApiCall ListProperty", "onResponse: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<PropertyResponse>, t: Throwable) {
                Log.e("ApiCall ListProperty", "onFailure: ${t.message}")
            }
        })
    }
}
