package github.sachin2dehury.randomjokes

import retrofit2.http.GET

interface JokeService {

    @GET("jokes/random")
    suspend fun fetchJoke(): JokeResponse
}
