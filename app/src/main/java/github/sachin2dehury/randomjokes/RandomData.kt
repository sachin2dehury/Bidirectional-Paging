package github.sachin2dehury.randomjokes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "random_data")
data class RandomData(
    @PrimaryKey
    val id: Int = 0,
    val value: String = UUID.randomUUID().toString(),
)
