package com.exal.grocerease.helper

import java.text.NumberFormat
import java.util.Locale

fun rupiahFormatter(number: Int): String {
    val localeID = Locale("in", "ID")
    val formatter = NumberFormat.getCurrencyInstance(localeID)
    return formatter.format(number)
}