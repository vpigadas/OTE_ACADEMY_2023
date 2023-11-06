package gr.ote.academy.mvvm

import androidx.lifecycle.MutableLiveData
import gr.ote.academy.mvvm.json.MovieDataModel

class MainRepository(_titleStream: MutableLiveData<String>) {

    fun getMockListData(): List<String> = (1..100).map { it.toString() }

    fun transform(data:MovieDataModel): List<String> = data.results.map { it.title }
}