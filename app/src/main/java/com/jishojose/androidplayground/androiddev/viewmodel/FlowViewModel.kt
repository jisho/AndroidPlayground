package com.jishojose.androidplayground.androiddev.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowViewModel @Inject constructor(): ViewModel() {
    val logs: SnapshotStateList<String> = mutableStateListOf()
    /**
     * Hot Flow - StateFlow, SharedFlow, Channel
     * Cold Flow - emit only when collect
     * */

    fun flowList()  {
        viewModelScope.launch {
            flow {
                emit("1")
                delay(2000)
                emit("2")
                delay(3000)
                emit("3")
            }.collect {
                logs.add(it)
            }
        }
    }

    val flowCollectsWithinCompose : Flow<String> = flow {
        emit("1")
        delay(2000)
        emit("2")
        delay(3000)
        emit("3")
    }

    //val flowCollectsWithinCompose = listOf("1", "2", "3").asFlow()
    //val flowCollectsWithinCompose = flowOf("1", "2", "3")
}
