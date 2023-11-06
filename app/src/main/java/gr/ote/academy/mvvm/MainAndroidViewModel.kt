package gr.ote.academy.mvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gr.ote.academy.mvvm.network.NetworkClient
import kotlinx.coroutines.launch

class MainAndroidViewModel(application: Application) : AndroidViewModel(application) {

    val adapter: MainListAdapter = MainListAdapter()
    val adapter2: MainRecyclerAdapter = MainRecyclerAdapter()

    private val _titleStream: MutableLiveData<String> = MutableLiveData("")
    val titleStream: LiveData<String> = _titleStream

    private val repository: MainRepository = MainRepository(_titleStream)
    private val networkClient = NetworkClient(application.applicationContext)

    fun onStart() {
        adapter.submitList(repository.getMockListData())

        performRefresh()
    }

    fun onResume() {
        networkClient.onStart()
    }

    fun onStop() {
        networkClient.onDismiss()
    }

    fun performRefresh() {
        viewModelScope.launch {
            networkClient.movieClient.get(onSuccess = { response ->
                val titleOnlyList = repository.transform(response)
                adapter.submitList(titleOnlyList)
            }, onError = {
                Log.d("MAIN", it.toString())
            })
        }
    }
}