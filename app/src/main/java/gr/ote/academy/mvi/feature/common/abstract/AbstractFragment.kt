package gr.ote.academy.mvi.feature.common.abstract

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class AbstractFragment<PVM : ParentViewModel, VM : FragmentViewModel> : Fragment() {

    protected abstract val parentViewModel: PVM
    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setOnFlowFinish {
            parentViewModel.performState(it)
        }

        startOperations()
    }

    abstract fun startOperations()

    override fun onPause() {
        stopOperations()
        super.onPause()
    }

    abstract fun stopOperations()
}