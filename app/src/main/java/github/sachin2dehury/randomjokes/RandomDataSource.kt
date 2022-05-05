package github.sachin2dehury.randomjokes

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class RandomDataSource(private val dao: RandomDao) : PagingSource<Int, RandomData>() {
    override fun getRefreshKey(state: PagingState<Int, RandomData>): Int? {
        return 1000
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RandomData> {
        val key = params.key ?: 1000
        Log.w("Paging", "${params.key}, ${params.loadSize}")
        return if (key >= 1000) {
            LoadResult.Page(
                dao.getPagedItemsAsc(key, params.loadSize),
                key - params.loadSize, key + params.loadSize
            )
        } else {
            LoadResult.Page(
                dao.getPagedItemsDesc(key, params.loadSize),
                key - params.loadSize,
                key + params.loadSize
            )
        }
    }
}
