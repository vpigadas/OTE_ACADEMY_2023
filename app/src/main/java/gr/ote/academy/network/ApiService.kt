package gr.ote.academy.network

import gr.ote.academy.network.json.MovieResponseJson
import gr.ote.academy.network.json.MoviesResponseJson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {


    @GET("api/movies/")
    fun get(@Header("Content-Type") contentType: String): Call<MoviesResponseJson>

    @GET("api/movies/{id}")
    fun getMovie(
        @HeaderMap headers: Map<String, String>,
        @Path("id") movieId: String,
        @QueryMap queries: Map<String, String>
    ): Call<MovieResponseJson>

    @POST("api/movies/")
    fun post(@Body body: MovieResponseJson)

    @Multipart
    @POST("uploadUser")
    fun uploadUser(
        @Part("email") email: RequestBody?,
        @Part("phone") phone: RequestBody?,
        @Part("name") name: RequestBody?,
        @Part("password") password: RequestBody?,
        @Part imageFile: MultipartBody.Part?
    ): Call<MovieResponseJson?>?

//    @GET()
//    fun<T:Any> demo2(@Url url: String): Call<MovieResponseJson>

}