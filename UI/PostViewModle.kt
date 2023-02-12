package com.example.taskpost.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskpost.model.Meme
import com.example.taskpost.model.PostApi
import com.example.taskpost.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PostViewModle @Inject constructor(private val postApi: PostApi):ViewModel() {

    private val _posts: MutableLiveData<Resource<List<Meme>>> =
        MutableLiveData(Resource.Loading())
    val posts: LiveData<Resource<List<Meme>>> get() = _posts
    init {
        getmeems()
    }

    fun getmeems(){
        viewModelScope.launch {
            _posts.value=Resource.Loading()
            _posts.value=postApi.getmeem()
        }
    }

}