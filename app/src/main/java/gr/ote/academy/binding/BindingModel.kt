package gr.ote.academy.binding

import androidx.lifecycle.MutableLiveData

data class BindingModel(
    val username: MutableLiveData<String>,
    val password: MutableLiveData<String>,
    val address: MutableLiveData<String>,
) {


}