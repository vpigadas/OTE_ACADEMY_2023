package gr.ote.academy.mvi.feature.common.abstract

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gr.ote.academy.mvi.state.State

abstract class ParentViewModel : ViewModel() {
    protected val stateStream: MutableLiveData<State> by lazy { MutableLiveData() }

    fun performState(state: State) {
        stateStream.postValue(state)
    }
}

abstract class FragmentViewModel(application: Application) : AndroidViewModel(application) {
    private var onFlowFinish: (State) -> Unit = {}

    fun setOnFlowFinish(onFlowFinish: (State) -> Unit) {
        this.onFlowFinish = onFlowFinish
    }

    fun onFinishFlow(state: State) {
        onFlowFinish.invoke(state)
    }
}