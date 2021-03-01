package com.ixs.testing.hilt.ui

import androidx.lifecycle.*
import com.ixs.testing.hilt.model.Blog
import com.ixs.testing.hilt.repository.MainRepository
import com.ixs.testing.hilt.utils.DataState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
 @Inject
 constructor(
     private val savedStateHandle: SavedStateHandle,
     private val repository: MainRepository
 ):ViewModel(){

    private val _datastate : MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState : LiveData<DataState<List<Blog>>>
        get() =_datastate

    fun setStateEvent(event: MainStateEvent){
        viewModelScope.launch {
            when(event){
                is MainStateEvent.GetLBlogEvent ->{
                    repository.getBlog()
                        .onEach {
                            dataState ->
                            _datastate.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None ->{

                }
            }
        }
    }
}

sealed class MainStateEvent{
    object GetLBlogEvent :MainStateEvent()
    object None :MainStateEvent()
}