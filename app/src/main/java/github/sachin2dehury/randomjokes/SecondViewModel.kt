package github.sachin2dehury.randomjokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class SecondViewModel : ViewModel() {

    private val pagingConfig =
        PagingConfig(10, 10, false, 10)

    fun fetchData(dao: RandomDao): Flow<PagingData<RandomData>> {
        return Pager(pagingConfig, 1000) {
            RandomDataSource(dao)
        }.flow
    }
}
