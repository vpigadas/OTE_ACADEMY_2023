package gr.ote.academy.mvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import gr.ote.academy.databinding.ActivityMain3Binding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainAndroidViewModel by viewModels<MainAndroidViewModel>()

    private lateinit var _binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(_binding.root)
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