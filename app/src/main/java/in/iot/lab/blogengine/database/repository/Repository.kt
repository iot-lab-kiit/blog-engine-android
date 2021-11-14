package `in`.iot.lab.blogengine.database.repository

import `in`.iot.lab.blogengine.database.data.Userdao
import `in`.iot.lab.blogengine.database.model.item

class repository(private val userdao: Userdao) {

    val readAllData = userdao.readalldata()

    fun addUser(item : item)
    {
        userdao.adduser(item)
    }

    suspend fun updateuser(item:item)
    {
        userdao.updateuser(item)
    }

    suspend fun deleteuser(item:item)
    {
        userdao.deleteuser(item)
    }

    suspend fun deleteallusers()
    {
        userdao.deleteallusers()
    }

}