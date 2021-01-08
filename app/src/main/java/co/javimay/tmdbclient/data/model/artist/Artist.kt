package co.javimay.tmdbclient.data.model.artist

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.javimay.tmdbclient.utils.tableNames

@Entity(tableName = tableNames.POPULAR_ARTISTS)
data class Artist(

    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val gender: Int,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String
)