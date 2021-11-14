package `in`.iot.lab.blogengine.database.data

import `in`.iot.lab.blogengine.database.model.item
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface Userdao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun adduser(item : item)

    @Update
    suspend fun updateuser(item :item)

    @Delete
    suspend fun deleteuser(item:item)

    @Query("DELETE FROM user_data")
    suspend fun deleteallusers()

    @Query("SELECT * from user_data order by id asc")
    fun readalldata() : LiveData<List<item>>
}