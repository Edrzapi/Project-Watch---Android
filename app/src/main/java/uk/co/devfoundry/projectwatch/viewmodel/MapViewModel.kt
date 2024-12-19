import android.content.Context
import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class MapViewModel : ViewModel() {

    private val _postcode = MutableStateFlow("")
    val postcode: StateFlow<String> get() = _postcode

    private val _markers = MutableStateFlow<List<LatLng>>(emptyList())
    val markers: StateFlow<List<LatLng>> get() = _markers

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    lateinit var geocoder: Geocoder

    fun updatePostcode(value: String) {
        _postcode.value = value
    }

    fun getPostcode(): String {
       return _postcode.value
    }

    fun searchPostcode(context: Context) {
        val currentPostcode = _postcode.value
        if (currentPostcode.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                _loading.value = true
                try {
                    print("This is the current: $currentPostcode")
                    val result = fetchMapData(currentPostcode, context)
                    if (result != null) {
                        _markers.value = listOf(result)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    _loading.value = false
                }
            }
        }
    }

    private fun fetchMapData(postcode: String, context: Context): LatLng? {
        geocoder = Geocoder(context, Locale.getDefault())
        val addressList = geocoder.getFromLocationName(postcode, 1)
        return if (addressList?.isNotEmpty() == true) {
            val location = addressList[0]
            LatLng(location.latitude, location.longitude)
        } else {
            null
        }
    }
}
