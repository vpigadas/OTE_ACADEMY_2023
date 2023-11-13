package gr.ote.academy.mvi.feature.home.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import gr.ote.academy.R
import gr.ote.academy.mvi.MVIViewModel
import gr.ote.academy.mvi.feature.common.abstract.AbstractFragment

class HomeLoadingFragment : AbstractFragment<MVIViewModel, HomeLoadingViewModel>() {

    override val parentViewModel: MVIViewModel by activityViewModels()
    override val viewModel: HomeLoadingViewModel by viewModels()

    override fun startOperations() {
        viewModel.onStart()
    }

    override fun stopOperations() {
        viewModel.onStop()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_loading, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeLoadingFragment()
    }
}