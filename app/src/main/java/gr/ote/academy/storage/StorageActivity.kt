package gr.ote.academy.storage

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import gr.ote.academy.R
import gr.ote.academy.utils.PREFERENCES_STORAGE

class StorageActivity : AppCompatActivity() {
    private val viewModel by viewModels<StorageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences(PREFERENCES_STORAGE, Activity.MODE_PRIVATE)
        val preferences = getPreferences(Activity.MODE_PRIVATE)

        viewModel.streamUsername.observe(this, Observer {

        })

        viewModel.getUsernames().observe(this, Observer {

        })

        viewModel.getUsernamesAsFlow().asLiveData().observe(this@StorageActivity, Observer {

        })
    }
}