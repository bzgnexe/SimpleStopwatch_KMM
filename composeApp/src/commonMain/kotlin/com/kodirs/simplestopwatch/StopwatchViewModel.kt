package com.kodirs.simplestopwatch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class StopwatchViewModel {
    private val _time = MutableStateFlow(0L) 
    val time: StateFlow<Long> = _time

    private var running = false
    private val scope = CoroutineScope(Dispatchers.Default)

    fun start() {
        if (running) return
        running = true
        scope.launch {
            while (running) {
                delay(10)
                _time.value += 10
            }
        }
    }

    fun stop() {
        running = false
    }

    fun reset() {
        stop()
        _time.value = 0L
    }
}
