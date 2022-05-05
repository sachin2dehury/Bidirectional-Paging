package github.sachin2dehury.randomjokes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokeDao {

    @Query("SELECT * FROM jokes")
    fun fetchJokes(): List<JokeResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: JokeResponse)
}
