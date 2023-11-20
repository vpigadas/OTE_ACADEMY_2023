package gr.ote.academy.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gr.ote.academy.network.NetworkManager
import gr.ote.academy.storage.database.UserEntity
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val networkManager: NetworkManager by lazy { NetworkManager() }
    private val databaseManager: DatabaseManager by lazy {
        DatabaseManager.getInstance(getApplication())
    }
    private val repository: DatabaseRepository by lazy {
        DatabaseRepository()
    }

    private val _streamMovies = MutableLiveData<List<UserEntity>>()
    val streamMovies: LiveData<List<UserEntity>> = _streamMovies

    val streamDbMovies : LiveData<List<UserEntity>> by lazy {
        databaseManager.getUserDb().getUserDAO().getAsStream()
    }

    init {
        viewModelScope.launch {
            _streamMovies.postValue(databaseManager.getUserDb().getUserDAO().get())
        }
    }

    fun onStart() {
        networkManager.apiClient.get(onSuccess = {
            it?.results?.also { movies ->
                val results = movies.map { repository.transform(it) }

                _streamMovies.postValue(results)
                databaseManager.save(results)
            }
        }, onFailed = {

        })
    }

    fun onLiveStart() {
        networkManager.apiClient.get(onSuccess = {
            it?.results?.also { movies ->
                val results = movies.map { repository.transform(it) }
                databaseManager.save(results)
            }
        }, onFailed = {

        })
    }

}