package ru.bpproject.camestore.fragments.catalog

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.bpproject.camestore.data.Repository
import ru.bpproject.camestore.data.localdb.entity.CategoryEntity
import ru.bpproject.camestore.vo.Resource
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _categories = MediatorLiveData<Resource<List<CategoryEntity>>>()
    val categories : LiveData<Resource<List<CategoryEntity>>> get() = _categories
    private var categoriesSource : LiveData<Resource<List<CategoryEntity>>> = MutableLiveData()

    init {
        getCategories()
    }

    fun retry() = getCategories()

    private fun getCategories() = viewModelScope.launch(Dispatchers.Main) {
        _categories.removeSource(categoriesSource)
        withContext(Dispatchers.IO) {
            categoriesSource = repository.getCategories()
        }
        _categories.addSource(categoriesSource) {
            _categories.value = it
        }
    }
}