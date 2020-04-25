package com.airtel.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airtel.R
import com.airtel.model.SuggestionAddress
import com.airtel.mvvm.MainViewModel
import com.airtel.utils.SharedPrefUtils
import com.airtel.utils.toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.search_suggestion.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    // we get our viewModel from Koin
    private val searchViewModel: MainViewModel by viewModel()
    private val preferences: SharedPrefUtils by inject()
    private val gson: Gson by inject()

    private var suggestionList = mutableListOf<SuggestionAddress>()
    private lateinit var mSuggestionListAdapter:SuggestionListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_suggestion)
        setUpUI()
        setupViewModel()
    }

    private fun setupViewModel(){
        searchViewModel.getSuggestions().observe(this, Observer {
            if(it!=null) {
                progressHideShow(false)
                suggestionList = it as MutableList<SuggestionAddress>
                notifyAdapter()
            }
        })

        searchViewModel.getErrorMessage().observe(this, Observer {
            progressHideShow(false)
            toast(it)
        })
    }

    private fun notifyAdapter(){
        mSuggestionListAdapter=SuggestionListAdapter(suggestionList)
        suggestion_list.adapter = mSuggestionListAdapter
        mSuggestionListAdapter.notifyDataSetChanged()
    }



    private fun setUpUI(){
        suggestion_list.layoutManager = LinearLayoutManager(this)
        search_suggest.requestFocus();
        search_suggest.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(s.length>1) {
                    suggestion_img.visibility = View.VISIBLE
                    progressHideShow(true)
                    searchViewModel.setSuggestions(s.toString())
                }else{
                    closeSuggestion()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })

        search_suggest.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                return@OnEditorActionListener true
            }
            false
        })

        suggestion_img.setOnClickListener {
            closeSuggestion()
            search_suggest.text.clear()
        }
    }

    fun closeSuggestion(){
        if (::mSuggestionListAdapter.isInitialized) {
            suggestionList.clear()
            mSuggestionListAdapter.notifyDataSetChanged()
        }
        suggestion_img.visibility = View.GONE
    }

    fun progressHideShow(flag:Boolean){
        if(flag){
            progress_circular.visibility=View.VISIBLE
        }else{
            progress_circular.visibility=View.GONE
        }
    }


}
