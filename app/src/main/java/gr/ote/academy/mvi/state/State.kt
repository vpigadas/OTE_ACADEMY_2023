package gr.ote.academy.mvi.state

import androidx.lifecycle.LiveData

interface State

data class LoadingState(
    val message: LiveData<String>
) : State

data class HomeState(
    val title: String,
    val message: LiveData<String>
) : State

data class ErrorState(
    val message: LiveData<String>? = null,
    val exception: Exception? = null
) : State