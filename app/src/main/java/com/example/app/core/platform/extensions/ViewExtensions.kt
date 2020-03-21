package com.example.app.core.platform.extensions

import android.view.View


fun View.show() {
    visibility = View.VISIBLE
}


fun View.hide() {
    visibility = View.GONE
}

fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}