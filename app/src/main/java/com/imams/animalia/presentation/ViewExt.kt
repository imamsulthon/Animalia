package com.imams.animalia.presentation

import android.view.View
import androidx.appcompat.widget.AppCompatButton

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun AppCompatButton.enabled() {
    this.isEnabled = true
}

fun AppCompatButton.disabled() {
    this.isEnabled = false
}

fun String.plurals(): String = this + if (this.last().equals('x', false)) "es" else "s"