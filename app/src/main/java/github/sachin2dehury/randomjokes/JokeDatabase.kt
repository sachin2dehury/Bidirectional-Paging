package github.sachin2dehury.randomjokes

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [JokeResponse::class],
    exportSchema = false
)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun getJokeDao(): JokeDao
}
