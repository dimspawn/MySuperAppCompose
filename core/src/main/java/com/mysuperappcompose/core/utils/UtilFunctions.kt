package com.mysuperappcompose.core.utils

import java.text.NumberFormat
import java.util.Locale

object UtilFunctions {
    private val localeID = Locale("in", "ID")

    fun Int?.fromDollarToRupiah(): String {
        val localId = localeID
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val fakeDollarToday = 15000
        val intValue = (this ?: 0) * fakeDollarToday
        return when {
            intValue > 0 -> formatter.format(intValue).replace(",00", "")
            intValue < 0 -> formatter.format(intValue).replace(",00", "")
            else -> "Rp0"
        }
    }

    fun Long?.fromDollarToRupiah(): String {
        val localId = localeID
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val fakeDollarToday = 15000
        val intValue = (this ?: 0) * fakeDollarToday
        return when {
            intValue > 0 -> formatter.format(intValue).replace(",00", "")
            intValue < 0 -> formatter.format(intValue).replace(",00", "")
            else -> "Rp0"
        }
    }
}