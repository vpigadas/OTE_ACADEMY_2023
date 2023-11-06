package gr.ote.academy.mvvm.network

import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import gr.ote.academy.mvvm.exceptions.NetworkException
import gr.ote.academy.mvvm.json.MovieDataModel

class MovieApiClient(private val sentRequest: (StringRequest) -> Unit) {

    companion object {
        private const val BASE_URL = "https://app-vpigadas.herokuapp.com/"
    }

    fun get(onSuccess: (MovieDataModel) -> Unit, onError: (NetworkException) -> Unit) {
        val endpoint = BASE_URL.plus("api/movies/")
        val request = StringRequest(
            Request.Method.GET,
            endpoint,
            { apiResponse: String ->
                val jsonObject = Gson().fromJson(apiResponse, MovieDataModel::class.java)
                onSuccess.invoke(jsonObject)
            },
            { error: VolleyError? ->
                onError.invoke(
                    NetworkException(
                        error?.networkResponse?.statusCode ?: 999,
                        error?.message,
                        error?.cause
                    )
                )
            }
        )
        sentRequest.invoke(request)
    }
}