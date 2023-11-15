package gr.ote.academy.network

import gr.ote.academy.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.security.KeyStore
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager

class NetworkManager {

    internal val apiClient: ApiClient by lazy {
        initialiseRetrofitClient(
            "".byteInputStream(),
            "".byteInputStream()
        )
    }
    private var websocket: WebSocket? = null
    private fun initialiseRetrofitClient(public: InputStream, private: InputStream): ApiClient {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://app-vpigadas.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(generateOkHttpClient(public, private))
            .build()
        val service = retrofit.create(ApiService::class.java)

        return ApiClient(service)
    }

    private fun generateOkHttpClient(public: InputStream, private: InputStream): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.connectTimeout(20, TimeUnit.SECONDS)
        httpClient.readTimeout(1, TimeUnit.MINUTES)
//        httpClient.writeTimeout(15,TimeUnit.SECONDS)

        httpClient.addInterceptor(HttpLoggingInterceptor().also {
            it.level = when(BuildConfig.DEBUG){
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        })

        // We're going to put our certificates in a Keystore
        val keystore = KeyStore.getInstance("PKC512")
        keystore.load(public, null)

        // Create a KeyManagerFactory with our specific algorithm our our public keys
        // Most of the cases is gonna be "X509"
        val keyManager = KeyManagerFactory.getInstance("X509")
        keyManager.init(keystore, null)

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(
            keyManager.keyManagers,
            CustomTrustManager().acceptedIssuers as Array<TrustManager>,
            SecureRandom()
        )

        return httpClient.sslSocketFactory(sslContext.socketFactory).build()
    }

    fun connectToWebSocket(onReceiveMessage: (String) -> Unit, onFailed: (Exception) -> Unit) {
        val request = Request.Builder().url("ws://").build()
        websocket = OkHttpClient().newWebSocket(request, object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                webSocket.send("username=vassilis")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                onReceiveMessage.invoke(text)
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                onFailed(Exception("Closed"))
                websocket = null
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                onFailed.invoke(Exception(t))
            }
        })
    }

    fun senMessageToWebSocket(message: String) {
        websocket?.send(message) ?: throw Exception("Socket is closed")
    }

}