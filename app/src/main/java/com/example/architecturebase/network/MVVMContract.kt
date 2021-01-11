package com.example.architecturebase.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.architecturebase.network.model.Post

interface MVVMContract {
    interface IPresenter{
        val data: LiveData<List<Post>>
        fun getPosts()
    }
}