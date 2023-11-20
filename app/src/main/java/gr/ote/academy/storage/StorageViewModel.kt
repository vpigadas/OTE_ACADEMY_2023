package gr.ote.academy.storage

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import gr.ote.academy.storage.database.DatabaseInstance
import gr.ote.academy.storage.database.UserEntity
import gr.ote.academy.utils.DATABASE_USERS
import gr.ote.academy.utils.PREFERENCES_STORAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StorageViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences by lazy {
        getApplication<Application>().getSharedPreferences(
            PREFERENCES_STORAGE, Activity.MODE_PRIVATE
        )
    }
    private val database: DatabaseInstance by lazy {
        Room.databaseBuilder(
            getApplication(), DatabaseInstance::class.java, DATABASE_USERS
        ).build()
    }

    val streamUsername: MutableLiveData<List<UserEntity>> = MutableLiveData(emptyList())

    fun onStart() {
        viewModelScope.launch {
            val usernames = database.getUserDAO().get()
            streamUsername.postValue(usernames)
        }

    }

    fun onStop() {

    }

    fun getUsernames():LiveData<List<UserEntity>> = database.getUserDAO().getAsStream()

    fun getUsernamesAsFlow(): Flow<List<UserEntity>> = database.getUserDAO().getAsFlow()

    fun retrieveUserName(): String? {
        return sharedPreferences.getString(KEY_USERNAME, null)
    }

    fun saveUsername(user: String) {
        viewModelScope.launch {
            val editor = sharedPreferences.edit()
            editor.putString(KEY_USERNAME, user)
            //editor.commit()
            editor.apply()
        }
    }

    companion object {
        const val KEY_USERNAME = "username"
    }

}