package gr.ote.academy.storage.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_name") val username: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "job_title") val jobTitle: String,
    @ColumnInfo(name = "address") val address: String
): Table