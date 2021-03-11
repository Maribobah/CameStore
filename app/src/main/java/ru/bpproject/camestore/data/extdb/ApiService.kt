package ru.bpproject.camestore.data.extdb

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import ru.bpproject.camestore.data.localdb.entity.CategoryEntity

interface ApiService {
    @GET("data/categories")
    fun getCategoriesAsync() : Deferred<ApiResponse<List<CategoryEntity>>>
}