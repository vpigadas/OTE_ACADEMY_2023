package gr.ote.academy.mvi.helper

import android.app.Activity
import androidx.fragment.app.Fragment
import gr.ote.academy.mvi.feature.common.error.ErrorFragment
import gr.ote.academy.mvi.feature.home.main.HomeFragment
import gr.ote.academy.mvi.feature.home.loading.HomeLoadingFragment
import gr.ote.academy.mvi.state.ErrorState
import gr.ote.academy.mvi.state.HomeState
import gr.ote.academy.mvi.state.LoadingState
import gr.ote.academy.mvi.state.State

class StateHelper(parentActivity: Activity) {

    fun transform(state: State): Fragment = when (state) {
        is LoadingState -> HomeLoadingFragment.newInstance()
        is HomeState -> HomeFragment.newInstance()
        is ErrorState -> ErrorFragment.newInstance()
        else -> throw IllegalStateException("This state (${state.javaClass.simpleName}) is not supported yet")
    }
}