package gr.ote.academy.mvi.feature.home.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import gr.ote.academy.R
import gr.ote.academy.mvi.MVIViewModel
import gr.ote.academy.mvi.feature.common.abstract.AbstractFragment

class HomeLoadingFragment : Fragment() {

    val parentViewModel: MVIViewModel by activityViewModels()

    val viewModel: HomeLoadingViewModel by viewModels()

    fun startOperations() {
        viewModel.onStart()
    }

    fun stopOperations() {
        viewModel.onStop()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_loading, container, false)
    }

    companion object {
        fun newInstance() = HomeLoadingFragment()
    }
}