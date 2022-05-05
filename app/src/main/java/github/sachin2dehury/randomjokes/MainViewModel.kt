package github.sachin2dehury.randomjokes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val jokeLiveData = MutableLiveData<List<JokeResponse>>()

    fun fetchJokes(database: JokeDatabase) {
        viewModelScope.launch(Dispatchers.IO) {
            jokeLiveData.postValue(database.getJokeDao().fetchJokes())
            repeat(10) {
                database.getJokeDao().insertJoke(JokeApi.api.fetchJoke())
            }
            jokeLiveData.postValue(database.getJokeDao().fetchJokes())
        }
    }
}
