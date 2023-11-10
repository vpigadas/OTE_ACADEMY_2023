package gr.ote.academy.binding

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class BindingViewModel(application: Application) : AndroidViewModel(application) {

    val uiModel: BindingModel by lazy { BindingModel(MutableLiveData(""),MutableLiveData(""),MutableLiveData("")) }

    fun login(view: View) {
        uiModel.username.value
    }

    fun loginEmpty() {
        TODO()
    }
}