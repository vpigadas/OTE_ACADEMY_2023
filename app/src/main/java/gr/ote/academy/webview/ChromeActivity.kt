package gr.ote.academy.webview

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import gr.ote.academy.R

class ChromeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chrome)
    }

    @SuppressLint("ResourceAsColor")
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val url = "https://www.google.com"
        val intent = CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(
                CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(android.R.color.holo_red_light).build()
            ).setColorSchemeParams(
                CustomTabsIntent.COLOR_SCHEME_DARK,
                CustomTabColorSchemeParams.Builder()
                    .build()
            ).setColorSchemeParams(
                CustomTabsIntent.COLOR_SCHEME_LIGHT,
                CustomTabColorSchemeParams.Builder().build()
            ).build()
        intent.launchUrl(this, Uri.parse(url))
    }
}