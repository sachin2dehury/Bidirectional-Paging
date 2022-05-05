package github.sachin2dehury.randomjokes

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object JokeApi {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .baseUrl("https://api.chucknorris.io/")
        .build()

    val api: JokeService = retrofit.create(JokeService::class.java)

    private var jokeDatabase: JokeDatabase? = null

    fun getJokeDatabase(context: Context): JokeDatabase {
        if (jokeDatabase == null) {
            jokeDatabase = Room.databaseBuilder(context, JokeDatabase::class.java, "app_db")
                .build()
        }
        return jokeDatabase!!
    }
}
