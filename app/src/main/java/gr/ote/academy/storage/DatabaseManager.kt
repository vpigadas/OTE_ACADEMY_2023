package gr.ote.academy.storage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import gr.ote.academy.storage.database.DatabaseInstance
import gr.ote.academy.storage.database.Table
import gr.ote.academy.storage.database.UserEntity
import gr.ote.academy.utils.DATABASE_USERS
import kotlin.reflect.KClass

class DatabaseManager private constructor(
    private val userDb: DatabaseInstance
) {

    companion object {
        fun getInstance(context: Context): DatabaseManager = DatabaseManager(
            Room.databaseBuilder(context, DatabaseInstance::class.java, DATABASE_USERS).build()
        )
    }

    fun getUserDb() = userDb

    fun save(data: Table) = when (data) {
        is UserEntity -> userDb.getUserDAO().save(data)
        else -> throw IllegalStateException("not supported Table ${data.javaClass.simpleName}")
    }

    fun save(data: List<Table>) = data.forEach { save(it) }

    fun <T : Any> getList(className: KClass<T>): LiveData<List<T>> = when (className) {
        UserEntity::class -> userDb.getUserDAO().getAsStream() as LiveData<List<T>>
        else ->  throw IllegalStateException("not supported Table")
    }

}