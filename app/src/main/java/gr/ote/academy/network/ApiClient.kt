package gr.ote.academy.network

import com.google.gson.JsonParseException
import gr.ote.academy.network.json.MovieResponseJson
import gr.ote.academy.network.json.MoviesResponseJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ApiClient(private val service: ApiService) {

    fun get(onSuccess: (MoviesResponseJson?) -> Unit, onFailed: (Exception) -> Unit) =
        service.get("application/json").enqueue(performCommunication(onSuccess, onFailed))

    fun getMovie(
        movieId: String,
        onSuccess: (MovieResponseJson?) -> Unit,
        onFailed: (Exception) -> Unit
    ) =
        service.getMovie(
            headers = mapOf("Content-Type" to "application/json"), movieId, emptyMap()
        ).enqueue(performCommunication(onSuccess, onFailed))

    private fun <T : Any> performCommunication(
        onSuccess: (T?) -> Unit,
        onFailed: (Exception) -> Unit
    ): Callback<T> =
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                when (response.isSuccessful) {
                    true -> {
                        onSuccess.invoke(response.body())
                    }
                    false -> {
                        val status = response.code()
                        val error = response.errorBody()

                        onFailed.invoke(IllegalStateException(error.toString()))
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                when (Exception(t)) {
                    is JsonParseException -> onFailed(IllegalArgumentException("json format error"))
                    else -> onFailed.invoke(Exception(t))
                }

            }

        }

}