package gr.ote.academy.webview

import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.webkit.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import gr.ote.academy.BuildConfig
import gr.ote.academy.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWebBinding.inflate(layoutInflater)
        WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
        setContentView(_binding.webview)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        _binding.webview.settings.apply {
            this.allowFileAccess = true
            this.allowContentAccess = true
            this.javaScriptEnabled = true
        }

        _binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {

                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            override fun onTooManyRedirects(
                view: WebView?, cancelMsg: Message?, continueMsg: Message?
            ) {
                super.onTooManyRedirects(view, cancelMsg, continueMsg)
            }

            override fun onReceivedError(
                view: WebView?, errorCode: Int, description: String?, failingUrl: String?
            ) {
                super.onReceivedError(view, errorCode, description, failingUrl)
            }

            override fun onReceivedError(
                view: WebView?, request: WebResourceRequest?, error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
            }

            override fun onReceivedHttpError(
                view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
            }

            override fun onReceivedSslError(
                view: WebView?, handler: SslErrorHandler?, error: SslError?
            ) {
                val cert = error?.certificate
                when (error?.primaryError) {
                    SslError.SSL_EXPIRED -> Unit
                    SslError.SSL_IDMISMATCH -> Unit
                    SslError.SSL_UNTRUSTED -> Unit
                    SslError.SSL_DATE_INVALID -> Unit
                    SslError.SSL_INVALID -> Unit
                }

                AlertDialog.Builder(this@WebActivity).setTitle("WebView").setMessage("")
                    .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, action: Int) {
                            dialog?.dismiss()
                        }

                    }).create()

                super.onReceivedSslError(view, handler, error)
            }

            override fun onReceivedHttpAuthRequest(
                view: WebView?,
                handler: HttpAuthHandler?,
                host: String?,
                realm: String?
            ) {
                super.onReceivedHttpAuthRequest(view, handler, host, realm)
            }
        }

        _binding.webview.webChromeClient = object : WebChromeClient() {
            override fun onCloseWindow(window: WebView?) {
                super.onCloseWindow(window)
            }

            override fun onJsAlert(
                view: WebView?, url: String?, message: String?, result: JsResult?
            ): Boolean {
                return super.onJsAlert(view, url, message, result)
            }

            override fun onJsConfirm(
                view: WebView?, url: String?, message: String?, result: JsResult?
            ): Boolean {
                return super.onJsConfirm(view, url, message, result)
            }

            override fun onJsPrompt(
                view: WebView?,
                url: String?,
                message: String?,
                defaultValue: String?,
                result: JsPromptResult?
            ): Boolean {
                return super.onJsPrompt(view, url, message, defaultValue, result)
            }

            override fun onGeolocationPermissionsShowPrompt(
                origin: String?, callback: GeolocationPermissions.Callback?
            ) {
                super.onGeolocationPermissionsShowPrompt(origin, callback)
            }

            override fun onGeolocationPermissionsHidePrompt() {
                super.onGeolocationPermissionsHidePrompt()
            }

            override fun onPermissionRequest(request: PermissionRequest?) {
                super.onPermissionRequest(request)
            }

            override fun onPermissionRequestCanceled(request: PermissionRequest?) {
                super.onPermissionRequestCanceled(request)
            }

            override fun onJsTimeout(): Boolean {
                return super.onJsTimeout()
            }

            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                when(consoleMessage?.messageLevel()){
                    ConsoleMessage.MessageLevel.DEBUG -> Log.d("Website",consoleMessage.message() ?: "not handled")
                    ConsoleMessage.MessageLevel.ERROR -> Log.e("Website",consoleMessage.message() ?: "not handled")
                    ConsoleMessage.MessageLevel.TIP -> Log.i("Website",consoleMessage.message() ?: "not handled")
                    ConsoleMessage.MessageLevel.WARNING -> Log.w("Website",consoleMessage.message() ?: "not handled")
                    ConsoleMessage.MessageLevel.LOG -> Log.i("Website",consoleMessage.message() ?: "not handled")
                    else -> Log.d("Website", "not handled")
                }
                return super.onConsoleMessage(consoleMessage)
            }

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
            }
        }

        _binding.webview.loadUrl("https://www.google.com")
    }

    override fun onBackPressed() {
        when (_binding.webview.canGoBack()) {
            true -> _binding.webview.goBack()
            false -> super.onBackPressed()
        }
    }
}