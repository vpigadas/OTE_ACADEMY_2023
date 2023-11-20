package gr.ote.academy.storage

import gr.ote.academy.network.json.MovieResponseJson
import gr.ote.academy.storage.database.UserEntity

class DatabaseRepository {

    fun transform(data: MovieResponseJson): UserEntity {
        val primaryKey = data.original_title ?: throw IllegalStateException("")

        return UserEntity(primaryKey, 0, data.original_title, data.overview ?: "")
    }
}