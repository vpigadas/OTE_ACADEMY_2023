package gr.ote.academy.binding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import gr.ote.academy.R
import gr.ote.academy.databinding.ActivityBindingBinding

class BindingActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityBindingBinding
    private val viewModel by viewModels<BindingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_binding)

        setContentView(_binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        _binding.viewModel = viewModel

//        _binding.uiModel = BindingModel("", "", "")
//        _binding.setLoginCallback { view ->
//            val username = _binding.inputUsername
//            val password = _binding.inputPassword
//            val address = _binding.inputAddress

//            login(username, password, address)
//        }
    }
//
//    private fun login(username: String?, password: String?, address: String?) {
//        TODO("Not yet implemented")
//    }
}