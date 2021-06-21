package com.indialone.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indialone.newsapp.model.NewsEntity
import com.indialone.newsapp.repository.NewsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val topHeadlinesNewsList = MutableLiveData<NewsEntity>()
    private val everythingNewsList = MutableLiveData<NewsEntity>()
    private val topHeadlinesBySourcesNewsList = MutableLiveData<NewsEntity>()
    private val everythingQueryNewsList = MutableLiveData<NewsEntity>()
    private val everythingQueryToNewsList = MutableLiveData<NewsEntity>()

    init {
        fetchTopHeadlinesNews()
        fetchEverythingNews()
        fetchTopHeadlinesNewsBySources()
        fetchEverythingQuery()
        fetchEverythingQueryTo()
    }

    private fun fetchEverythingQueryTo() {
        viewModelScope.launch {
            try {
                coroutineScope {
                    val news = async {
                        newsRepository.getNewsEverythingQueryTo()
                    }
                    everythingQueryToNewsList.postValue(news.await())
                }
            } catch (e: Exception) {
            }
        }
    }

    private fun fetchEverythingQuery() {
        viewModelScope.launch {
            try {
                coroutineScope {
                    val news = async {
                        newsRepository.getNewsEverythingQuery()
                    }
                    everythingQueryNewsList.postValue(news.await())
                }
            } catch (e: Exception) {
            }
        }
    }

    private fun fetchTopHeadlinesNewsBySources() {
        viewModelScope.launch {
            try {
                coroutineScope {
                    val news = async {
                        newsRepository.getNewsTopHeadlinesBySources()
                    }
                    topHeadlinesBySourcesNewsList.postValue(news.await())
                }
            } catch (e: Exception) {
            }
        }
    }

    private fun fetchTopHeadlinesNews() {
        viewModelScope.launch {
            try {
                coroutineScope {
                    val news = async {
                        newsRepository.getNewsTopHeadlines()
                    }
                    topHeadlinesNewsList.postValue(news.await())
                }
            } catch (e: Exception) {

            }
        }
    }

    private fun fetchEverythingNews() {
        viewModelScope.launch {
            try {
                coroutineScope {
                    val news = async {
                        newsRepository.getNewsEverything()
                    }
                    everythingNewsList.postValue(news.await())
                }
            } catch (e: Exception) {

            }
        }
    }

    fun getTopHeadlinesNews(): LiveData<NewsEntity> {
        return topHeadlinesNewsList
    }

    fun getEverythingNews(): LiveData<NewsEntity> {
        return everythingNewsList
    }

    fun getTopHeadlinesBySourcesNews(): LiveData<NewsEntity> {
        return topHeadlinesBySourcesNewsList
    }

    fun getEverythingQueryNews(): LiveData<NewsEntity> {
        return everythingQueryNewsList
    }

    fun getEverythingQueryToNews() : LiveData<NewsEntity> {
        return everythingQueryToNewsList
    }

}