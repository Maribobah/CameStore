package ru.bpproject.camestore.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.bpproject.camestore.fragments.ViewModelFactory
import ru.bpproject.camestore.fragments.catalog.CatalogViewModel
import ru.bpproject.camestore.fragments.productItem.ProductItemViewModel
import ru.bpproject.camestore.fragments.productList.ProductListViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    abstract fun bindProductListViewModel(productListViewModel: ProductListViewModel) : ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(ProductItemViewModel::class)
    abstract fun bindProductItemViewModel(productItemViewModel: ProductItemViewModel) : ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    abstract fun bindCatalogViewModel(catalogViewModel: CatalogViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}