package `in`.iot.lab.blogengine.database.viewmodel

import `in`.iot.lab.blogengine.database.data.userDatabase
import `in`.iot.lab.blogengine.database.model.item
import `in`.iot.lab.blogengine.database.repository.repository
import `in`.iot.lab.blogengine.model.BlogListItem
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class userviewmodel(application: Application) : AndroidViewModel(application) {

    val readalldata : LiveData<List<item>>

    private val repository: repository

    init {
        val userdao = userDatabase.getDatabase(application).userDao()
        repository = repository(userdao)
        readalldata = repository.readAllData
    }

    fun adduser(item: item)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(item)
        }
    }

    fun updateuser(item:item)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateuser(item)
        }
    }

    fun deleteuser(item:item)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteuser(item)
        }
    }

    fun deleteallusers()
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteallusers()
        }
    }
}