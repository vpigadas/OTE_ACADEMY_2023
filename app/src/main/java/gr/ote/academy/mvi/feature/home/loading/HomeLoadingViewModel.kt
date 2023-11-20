package gr.ote.academy.mvi.feature.home.loading

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gr.ote.academy.mvi.feature.common.abstract.FragmentViewModel
import gr.ote.academy.mvi.state.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeLoadingViewModel(application: Application) : FragmentViewModel(application) {

    fun onStart() {
        viewModelScope.launch {
            delay(5000)
            onLoadingFinish()
        }
    }

    private fun onLoadingFinish() {
        onFinishFlow(HomeState("Home", MutableLiveData("")))
    }

    fun onStop() {

    }
}