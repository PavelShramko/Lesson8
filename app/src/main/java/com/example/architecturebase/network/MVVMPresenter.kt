package com.example.architecturebase.network

import android.widget.Toast
import androidx.lifecycle.*
import com.example.architecturebase.network.model.Post
import com.example.architecturebase.network.model.UseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MVVMPresenter() : ViewModel(), MVVMContract.IPresenter, LifecycleObserver {

    override val data: MutableLiveData<List<Post>> = MutableLiveData()
    private val retrofit: com.example.architecturebase.adapter.Retrofit = com.example.architecturebase.adapter.Retrofit()
    private val useCase = UseCase()

    override fun getPosts() {
        retrofit.postApi.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        useCase.createUseCase(posts)
                        data.postValue(posts)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                data.postValue(null)
                t.printStackTrace()
            }
        })
    }

    /*@OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        getPosts()
    }*/
}