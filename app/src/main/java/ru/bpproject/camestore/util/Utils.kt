package ru.bpproject.camestore.util

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import ru.bpproject.camestore.R
import java.text.NumberFormat
import java.util.*

fun getCurrencyFormat() : NumberFormat {
    return NumberFormat.getCurrencyInstance().also {
        it.currency = Currency.getInstance("rub")
        it.maximumFractionDigits = 0
    }
}

fun getCircularProgress(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 12f
        centerRadius= 48f
        setColorSchemeColors(ContextCompat.getColor(context, R.color.primary))
        start()
    }
}