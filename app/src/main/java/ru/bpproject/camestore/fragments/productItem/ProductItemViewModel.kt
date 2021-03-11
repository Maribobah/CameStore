package ru.bpproject.camestore.fragments.productItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bpproject.camestore.data.Repository
import ru.bpproject.camestore.data.localdb.entity.ProductEntity
import javax.inject.Inject

class ProductItemViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var productId: Long = -1

    private val _product = MutableLiveData<ProductEntity>()
    private val _functionality = MutableLiveData<List<String>>()
    private val _accessories = MutableLiveData<List<ProductEntity>>()

    val product : LiveData<ProductEntity> get() = _product
    val functionality : LiveData<List<String>> get() = _functionality
    val accessories: LiveData<List<ProductEntity>> get() =_accessories

    fun loadProduct(id: Long) {
        productId = id
        fetchProductFromDb()
    }

    private fun fetchProductFromDb() {
        viewModelScope.launch {
            val productEntity = repository.getProductById(productId)
            _product.value = productEntity
            _functionality.value = productEntity.functionality.split(';')
            _accessories.value = repository.getProductAccessories(productEntity.id)
        }
    }

}