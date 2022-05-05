package github.sachin2dehury.randomjokes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RandomDao {

    @Query("select * from random_data where id>=:key and id<(:key+:loadSize) order by id asc")
    suspend fun getPagedItemsAsc(key: Int, loadSize: Int): List<RandomData>

    @Query("select * from random_data where id<:key and id>=(:key-:loadSize) order by id asc")
    suspend fun getPagedItemsDesc(key: Int, loadSize: Int): List<RandomData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: RandomData)
}
