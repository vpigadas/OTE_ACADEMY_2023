package gr.ote.academy.mvvm

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import gr.ote.academy.databinding.ActivityMain3Binding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainAndroidViewModel by viewModels<MainAndroidViewModel>()

    private lateinit var _binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(_binding.root)

        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            when (it) {
                true -> Unit
                false -> Unit
            }
        }

        listOf<Int>(
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        ).count { it !=  PackageManager.PERMISSION_GRANTED} > 0

        val resultWriteExPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        when (resultWriteExPermission == PackageManager.PERMISSION_GRANTED) {
            true -> Unit
            false -> {
                when (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                )) {
                    true -> {
                        startActivity(Intent(Settings.ACTION_APPLICATION_SETTINGS))
                    }
                    false -> {
                        requestPermissions(
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            2000
                        )
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewModel.titleStream.observe(this) {
            it?.also { title ->
                _binding.mainTitle.text = title
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(_binding.mainFragmentContainer.id, MainRecyclerViewFragment.newInstance())
            .commit()

        _binding.mainBtnRefresh.setOnClickListener {
            viewModel.performRefresh()
        }

        viewModel.onStart()
    }

    override fun onPostResume() {
        super.onPostResume()

        viewModel.onResume()
    }

    override fun onPause() {
        viewModel.onStop()
        super.onPause()
    }
}