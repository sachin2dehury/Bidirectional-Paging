package github.sachin2dehury.randomjokes

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RandomData::class],
    version = 1,
    exportSchema = false
)
abstract class RandomDb : RoomDatabase() {
    abstract fun getDao(): RandomDao
}
