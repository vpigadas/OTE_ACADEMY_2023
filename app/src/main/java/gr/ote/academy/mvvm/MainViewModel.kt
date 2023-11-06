package gr.ote.academy.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gr.ote.academy.mvvm.network.NetworkClient

class MainViewModel : ViewModel() {

    val adapter: MainListAdapter = MainListAdapter()
    val adapter2: MainRecyclerAdapter = MainRecyclerAdapter()

    private val _titleStream: MutableLiveData<String> = MutableLiveData("")
    val titleStream: LiveData<String> = _titleStream

    private val repository: MainRepository = MainRepository(_titleStream)

    private fun getMockData(): List<String> = (1..100).map { it.toString() }

    fun onStart() {
        adapter.submitList(getMockData())
    }

    fun onResume() {

    }

    fun onStop() {

    }

    fun performRefresh() {
        TODO("Not yet implemented")
    }
}