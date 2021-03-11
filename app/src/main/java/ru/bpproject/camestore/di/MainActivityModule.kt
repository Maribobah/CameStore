package ru.bpproject.camestore.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.bpproject.camestore.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : MainActivity
}