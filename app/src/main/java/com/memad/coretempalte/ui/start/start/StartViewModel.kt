package com.memad.coretempalte.ui.start.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memad.coretempalte.data.remote.StartClient
import com.memad.coretempalte.utils.AccessNative
import com.memad.coretempalte.utils.Resource
import com.skydoves.sandwich.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val startClient: StartClient
) : ViewModel() {
    /*private val _createSessionStatus = MutableLiveData<Resource<AuthResponse>>()
    val createSessionStatus: LiveData<Resource<AuthResponse>> = _createSessionStatus


    fun createSession() {
        _createSessionStatus.value = Resource.Loading()
        viewModelScope.launch {
            val response = startClient.getStarted(AccessNative.getApiKey())
            response.onSuccess {
                _createSessionStatus.value = Resource.Success(data)
            }
                .onError {
                    _createSessionStatus.value =
                        when (statusCode) {
                            StatusCode.InternalServerError -> Resource.Error("InternalServerError")
                            StatusCode.BadGateway -> Resource.Error("BadGateway")
                            else -> Resource.Error("$statusCode(${statusCode.code}): ${message()}")
                        }
                }
                .onException {
                    _createSessionStatus.value = Resource.Error("Something went wrong!")
                }
        }
    }*/
}