package gr.ote.academy.mvvm

import androidx.lifecycle.ViewModel

class MainFragmentViewModel : ViewModel() {

    val adapter: MainListAdapter = MainListAdapter()

    fun onStart() {}

    fun onResume() {}

    fun onStop() {}

    fun updateList(data: List<String>) {
        adapter.submitList(data)
    }
}