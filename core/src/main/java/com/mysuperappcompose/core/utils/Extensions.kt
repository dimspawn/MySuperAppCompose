package com.mysuperappcompose.core.utils

import android.content.Context
import android.widget.Toast

object Extensions {
    fun Context.myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}