package ru.bpproject.camestore.util

import android.os.SystemClock
import androidx.collection.ArrayMap
import java.util.concurrent.TimeUnit

class RateLimiter<in KEY> (timeout: Int, timeUnit: TimeUnit){
    private val timestamps = ArrayMap<KEY, Long>()
    private val timeout = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(key: KEY): Boolean {
        val lastFetched = timestamps[key]
        val now = SystemClock.uptimeMillis()
        if (lastFetched == null || now - lastFetched > timeout) {
            timestamps[key] = now
            return true
        }
        return false
    }

}