package `in`.iot.lab.blogengine.database.model

import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user_data")
data class item (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val data : String,
    val image :String
    )