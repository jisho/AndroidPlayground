package com.jishojose.androidplayground.androiddev.viewmodel

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
    init {
        println("CoroutineViewModel initialized")

        observeNetworkStatus()
        if (networkStatusRepository.state.value.isNotConnected()){
            println("CoroutineViewModel: Network is disconnected")
        }else
            println("CoroutineViewModel: Network is connected")

        viewModelScope.launch {
           // coroutineConsecutiveExecution()
            coroutineParallelExecution()
           // callLongRunningTask()
        }
    }

    private fun observeNetworkStatus() {
        viewModelScope.launch {
            networkStatusRepository.state.collect()
        }
    }

    // Consecutive execution or Sequential execution
    private fun coroutineConsecutiveExecution() {
        viewModelScope.launch {
            println("coroutineConsecutiveExecution initialized")
            someSuspendFunction()
            someOtherSuspendFunction()
        }
    }

    /* Parallel execution or Concurrent execution
    * async().await() each one immediately Sequential
    * async() all, then await()	⚡ Parallel	*/
    private fun coroutineParallelExecution() {
        viewModelScope.launch {
            println("coroutineParallelExecution initialized")
            val task1 = async { someSuspendFunctionForParallelExecution(2000,1) }
            val task2 = async { someSuspendFunctionForParallelExecution(1000,2) }
            val task3 = async { someSuspendFunctionForParallelExecution(3000,3) }

           // task1.await()
           // task2.await()
           // task3.await()
            //or
           // awaitAll(task1, task2, task3)
           // joinAll(task1, task2, task3)
        }
    }

    private fun callLongRunningTask() {
        viewModelScope.launch(Dispatchers.IO) {
            println("Long Task Started")
            // long running operation
            val result = performLongTask()
            withContext(Dispatchers.Main) {
                // update UI with result
                println("Result: $result")
            }
        }
    }

    private suspend fun performLongTask(): String {
        delay(5000) // simulate long operation like network call, heavy computation, etc.
        return "Long Task Completed"
    }


    private suspend fun someSuspendFunctionForParallelExecution(timeMillis: Long,count: Int) {
        println("CoroutineViewModel: ParallelExecution $count started")
        delay(timeMillis)
        println("CoroutineViewModel: ParallelExecution $count completed")
    }

    private suspend fun someSuspendFunction() {
        println("CoroutineViewModel: ConsecutiveExecution 1 started")
        delay(2000)
        println("CoroutineViewModel: ConsecutiveExecution 1 completed")
    }

    private suspend fun someOtherSuspendFunction() {
        println("CoroutineViewModel: ConsecutiveExecution 2 started")
        delay(2000)
        println("CoroutineViewModel:  ConsecutiveExecution 2 completed")
    }
}
