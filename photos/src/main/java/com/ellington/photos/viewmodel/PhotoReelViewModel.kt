package com.ellington.photos.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ellington.mvvm.repository.Result
import com.ellington.mvvm.utils.Event
import com.ellington.mvvm.viewmodel.BaseViewModel
import com.ellington.photos.R
import com.ellington.photos.data.RandomUser
import com.ellington.photos.data.RandomUserResponse
import com.ellington.photos.data.source.PhotoReelRepository
import com.ellington.photos.view.PhotoReelFragment.Companion.NUMBER_OF_RESPONSES
import kotlinx.coroutines.launch

class PhotoReelViewModel(private val repository: PhotoReelRepository) :
    BaseViewModel<PhotoReelViewModelProvider>() {

    private val _randomUserResponse =
        MutableLiveData<List<RandomUser>>().apply { this.value = emptyList() }
    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<Event<Int>>()

    val randomUsersResponse: LiveData<List<RandomUser>> = _randomUserResponse
    val loading: LiveData<Boolean> = _loading
    val error: LiveData<Event<Int>> = _error

    fun getRandomUsers(numberOfResponses: Int) {
        _loading.value = true
        viewModelScope.launch {
            val response = repository.getRandomUsers(numberOfResponses)

            if (response is Result.Success) {
                setData(response.data)
            } else {
                Log.e("Error", "Error fetching random users")
                _error.value = Event(R.string.error_fetching_users)
            }

            _loading.value = false
        }
    }

    fun loadMoreRandomUsers() {
        getRandomUsers(NUMBER_OF_RESPONSES)
    }

    private fun setData(response: RandomUserResponse) {
        _randomUserResponse.value = _randomUserResponse.value?.plus(response.results)
    }

    fun getRandomUsersCount(): Int? {
        return _randomUserResponse.value?.size
    }

    fun getUseAtPosition(position: Int): RandomUser? {
        return _randomUserResponse.value?.get(position)
    }

    override fun onCleared() {
    }
}