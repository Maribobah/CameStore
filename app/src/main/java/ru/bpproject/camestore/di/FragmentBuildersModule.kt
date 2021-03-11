package ru.bpproject.camestore.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.bpproject.camestore.fragments.catalog.CatalogFragment
import ru.bpproject.camestore.fragments.productItem.ProductItemFragment
import ru.bpproject.camestore.fragments.productList.ProductListFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeProductListFragment() : ProductListFragment
    @ContributesAndroidInjector
    abstract fun contributeProductItemFragment() : ProductItemFragment
    @ContributesAndroidInjector
    abstract fun contributeCatalogFragment() : CatalogFragment
}