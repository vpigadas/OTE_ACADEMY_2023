package gr.ote.academy.mvvm.network

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class NetworkClient(private val context: Context) {

    val movieClient = MovieApiClient(::sentRequest)
    val stockClient = StockApiClient(::sentRequest)

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context)
    }

    fun onStart() {
        requestQueue.start()
    }

    fun onDismiss() {
        requestQueue.stop()
    }

    private fun sentRequest(request: StringRequest) {
        requestQueue.add(request)
    }

}