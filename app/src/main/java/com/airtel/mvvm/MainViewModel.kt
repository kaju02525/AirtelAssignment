package com.airtel.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airtel.model.SuggestionAddress
import com.airtel.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

    fun setSuggestions(query:String){
        return repository.setSuggestions(query)
    }
    fun getSuggestions(): MutableLiveData<List<SuggestionAddress>> {
        return repository.getSuggestions()
    }
    fun getErrorMessage(): MutableLiveData<String> {
        return repository.getErrorMessage()
    }
}
