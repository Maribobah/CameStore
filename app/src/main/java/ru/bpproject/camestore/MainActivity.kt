package ru.bpproject.camestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), HasAndroidInjector, CartActions {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    private lateinit var navView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        connectNavigation()
    }

    private fun connectNavigation() {
        val navHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navView = findViewById(R.id.nav_view)
        navView.setupWithNavController(navController)
    }

    override fun addToCart(productId: Long) {
        val badge = navView.getOrCreateBadge(R.id.nav_cart)
        navView.apply {
            badge.number++
            badge.backgroundColor = resources.getColor(R.color.redOrange)
            badge.isVisible = true
        }
        val snackbar =Snackbar.make(navView, "Товар добавлен в корзину", Snackbar.LENGTH_SHORT)
        snackbar.setAction("Отменить") {
            navView.apply {
                badge.number--
                badge.isVisible = (badge.number > 0)
            }
        }
        snackbar.show()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}