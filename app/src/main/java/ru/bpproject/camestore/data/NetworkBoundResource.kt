package ru.bpproject.camestore.data

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import ru.bpproject.camestore.data.extdb.ApiEmptyResponse
import ru.bpproject.camestore.data.extdb.ApiErrorResponse
import ru.bpproject.camestore.data.extdb.ApiResponse
import ru.bpproject.camestore.data.extdb.ApiSuccessResponse
import ru.bpproject.camestore.vo.Resource
import kotlin.coroutines.coroutineContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MutableLiveData<Resource<ResultType>>()

    suspend fun build() : NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) {
            result.value = Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch {
            val dbResult = loadFromDb()
            if (shouldFetch(dbResult)) {
                fetchFromNetwork(dbResult)
            } else {
                withContext(Dispatchers.Main) {
                    setValue(Resource.success(dbResult))
                }
            }
        }
        return this
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private suspend fun fetchFromNetwork(dbResult: ResultType) {
        setValue(Resource.loading(dbResult))
        when (val apiResponse = createCallAsync().await()) {
            is ApiSuccessResponse -> {
                saveCallResults(processResponse(apiResponse))
                setValue(Resource.success(loadFromDb()))
            }
            is ApiEmptyResponse -> {
                setValue(Resource.success(dbResult))
            }
            is ApiErrorResponse -> {
                onFetchFailed()
                setValue(Resource.error(apiResponse.errorMessage, dbResult))
            }
        }
    }

    protected abstract suspend fun loadFromDb(): ResultType

    protected abstract fun shouldFetch(data: ResultType): Boolean

    protected abstract fun createCallAsync(): Deferred<ApiResponse<RequestType>>

    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    protected abstract suspend fun saveCallResults(item: RequestType)

    protected abstract fun onFetchFailed()

}