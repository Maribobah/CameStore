package ru.bpproject.camestore.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Deferred
import ru.bpproject.camestore.data.extdb.ApiResponse
import ru.bpproject.camestore.data.extdb.ApiService
import ru.bpproject.camestore.data.localdb.Dao
import ru.bpproject.camestore.data.localdb.entity.CategoryEntity
import ru.bpproject.camestore.data.localdb.entity.CategoryWithProducts
import ru.bpproject.camestore.data.localdb.entity.ProductEntity
import ru.bpproject.camestore.vo.Resource
import javax.inject.Inject

class Repository @Inject constructor(
    private val dao: Dao,
    private val apiService: ApiService
) {

    suspend fun getCategories(): LiveData<Resource<List<CategoryEntity>>> {
        return object : NetworkBoundResource<List<CategoryEntity>, List<CategoryEntity>>() {
            override suspend fun loadFromDb(): List<CategoryEntity> =
                dao.getCategories()

            override fun shouldFetch(data: List<CategoryEntity>): Boolean =
                data == null || data.isEmpty()

            override fun createCallAsync(): Deferred<ApiResponse<List<CategoryEntity>>> =
                apiService.getCategoriesAsync()

            override suspend fun saveCallResults(item: List<CategoryEntity>) =
                dao.insertCategories(item)

            override fun onFetchFailed() {}

        }.build().asLiveData()
    }

    suspend fun getCategoryWithProducts(categoryId : Long): List<CategoryWithProducts> =
        dao.getCategoryWithProducts(categoryId)

    suspend fun getProductById(productId: Long) : ProductEntity =
        dao.getProductById(productId)

    suspend fun getProductAccessories(productId: Long) : List<ProductEntity> =
        dao.getProductAccessories(productId)
}