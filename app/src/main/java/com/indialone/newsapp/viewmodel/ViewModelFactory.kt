package com.indialone.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.indialone.newsapp.repository.NewsRepository
import java.lang.IllegalArgumentException

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(NewsRepository()) as T
        }
        throw IllegalArgumentException("unknown view model class found")
    }


}