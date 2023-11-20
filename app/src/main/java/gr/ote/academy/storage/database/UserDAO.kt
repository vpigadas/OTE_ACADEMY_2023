package gr.ote.academy.storage.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(userEntity: UserEntity)

//    @Insert
//    fun insert(userEntity: UserEntity)
//
//    @Insert
//    fun insert(userEntity: List<UserEntity>)
//
//    @Insert
//    fun insert(vararg userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

//    @Update
//    fun update(userEntity: UserEntity)

    @Query("SELECT * FROM User")
    fun get(): List<UserEntity>

    @Query("SELECT * FROM User")
    fun getAsStream(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM User")
    fun getAsFlow(): Flow<List<UserEntity>>

    @Query("SELECT * FROM User LIMIT 1")
    fun getFirst(): UserEntity?

    @Query("SELECT * FROM User WHERE user_name LIKE :name")
    fun filterName(name: String): List<UserEntity>
}