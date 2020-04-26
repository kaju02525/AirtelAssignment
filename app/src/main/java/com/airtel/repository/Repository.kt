package com.airtel.repository

import androidx.lifecycle.MutableLiveData
import com.airtel.App
import com.airtel.R
import com.airtel.model.SuggestionAddress
import com.airtel.network.ApiStatus.isCheckAPIStatus
import com.airtel.network.RestClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository(val restClient: RestClient) {

    private val userList = MutableLiveData<List<SuggestionAddress>>()
    private val errorMess = MutableLiveData<String>()


    fun setSuggestions(query:String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().setSuggestionsAsync(query).await().let {
                    if (it.isSuccessful)
                        userList.postValue(it.body()!!.data!!.addressList)
                    else
                        errorMess.value = isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errorMess.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }

    fun getSuggestions(): MutableLiveData<List<SuggestionAddress>> {
        return userList
    }

    fun getErrorMessage(): MutableLiveData<String> {
        return errorMess
    }
}

