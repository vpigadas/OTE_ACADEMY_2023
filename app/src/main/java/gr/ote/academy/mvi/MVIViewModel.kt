package gr.ote.academy.mvi

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import gr.ote.academy.mvi.state.LoadingState
import gr.ote.academy.mvi.state.State

class MVIViewModel() : ViewModel() {


    fun onStart() {
        when (stateStream.value) {
            null -> startFlow()
            else -> Unit
        }
    }

    private fun startFlow() {
        stateStream.postValue(LoadingState(message = MutableLiveData("")))
    }

    fun onStop() {

    }


    fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        stateStream.observe(owner, observer)
    }

    protected val stateStream: MutableLiveData<State> by lazy { MutableLiveData() }

    fun performState(state: State) {
        stateStream.postValue(state)
    }
}