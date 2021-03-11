package ru.bpproject.camestore.fragments.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bpproject.camestore.data.Repository
import ru.bpproject.camestore.data.localdb.entity.ProductEntity
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var categoryId : Long = -1

    private val _mutableProducts = MutableLiveData<List<ProductEntity>>()
    private val _categoryName = MutableLiveData<String>()

    val products : LiveData<List<ProductEntity>> get() = _mutableProducts
    val categoryName : LiveData<String> get() = _categoryName

    fun getProducts(id: Long) {
        categoryId = id
        fetchProductsFromDb()
    }

    private fun fetchProductsFromDb() {
        viewModelScope.launch {
            val data = repository.getCategoryWithProducts(categoryId)
            _mutableProducts.value = data[0].products
            _categoryName.value = data[0].category.name
        }
    }

}