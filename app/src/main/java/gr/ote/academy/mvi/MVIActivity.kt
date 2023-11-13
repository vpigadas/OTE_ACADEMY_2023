package gr.ote.academy.mvi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import gr.ote.academy.R
import gr.ote.academy.databinding.ActivityMviactivityBinding
import gr.ote.academy.mvi.helper.StateHelper

class MVIActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMviactivityBinding

    private val viewModel: MVIViewModel by viewModels<MVIViewModel>()
    private val stateHelper: StateHelper by lazy { StateHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_mviactivity)

        setContentView(_binding.root)
    }

    override fun onPostResume() {
        super.onPostResume()

        viewModel.observe(this, Observer {
            val fragment = stateHelper.transform(state = it)

            supportFragmentManager.beginTransaction()
                .replace(_binding.fragmentContainer.id, fragment).commit()
        })
    }
}