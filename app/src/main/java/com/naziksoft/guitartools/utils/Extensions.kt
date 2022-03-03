package com.naziksoft.guitartools.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

object Extensions {
    fun View.makeVisibleOrGone(isVisible: Boolean) {
        visibility = if (isVisible) VISIBLE else GONE
    }
}