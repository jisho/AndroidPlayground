package com.jishojose.androidplayground.androiddev.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jishojose.core.android.connectivity.NetworkStatusRepository
import com.jishojose.core.android.connectivity.isNotConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class CoroutineViewModel @Inject constructor(
    private val networkStatusRepository: NetworkStatusRepository,
): ViewModel() {
    val logs: SnapshotStateList<String> = mutableStateListOf()

    private fun log(message: String) {
        logs.add(message)
    }
    init {
        observeNetworkStatus()
        if (networkStatusRepository.state.value.isNotConnected()){
            log("Network is disconnected")
        }else
            log("Network is connected")
    }

    private fun observeNetworkStatus() {
        viewModelScope.launch {
            networkStatusRepository.state.collect()
        }
    }

    // Consecutive execution or Sequential execution
    fun coroutineConsecutiveExecution() {
        viewModelScope.launch {
            log("ConsecutiveExecution initialized")
            someSuspendFunction(2000,1)
            someSuspendFunction(2000,2)
        }
    }

    /* Parallel execution or Concurrent execution
    * async().await() each one immediately Sequential
    * async() all, then await()	⚡ Parallel	*/
    fun coroutineParallelExecution() {
        viewModelScope.launch {
            println("coroutineParallelExecution initialized")

            val task1 = async { someSuspendFunction(2000,1) }
            val task2 = async { someSuspendFunction(1000,2) }
            val task3 = async { someSuspendFunction(3000,3) }

            task1.await()
            task2.await()
            task3.await()
            //or
           // awaitAll(task1, task2, task3)
           // joinAll(task1, task2, task3)
        }
    }

    // long running operation
    fun coroutineLongRunningTask() {
        viewModelScope.launch(Dispatchers.IO) {
            log("Long Task Started")
            val result = performLongTask()
            withContext(Dispatchers.Main) {
                // update UI with result
                log("Result: $result")
            }
        }
    }

    private suspend fun performLongTask(): String {
        delay(5000) // simulate long operation like network call, heavy computation, etc.
        return "Long Task Completed"
    }


    private suspend fun someSuspendFunction(timeMillis: Long,count: Int) {
        log("task $count started")
        delay(timeMillis)
        log("task $count completed")
    }

}
