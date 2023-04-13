package com.imams.animalia.presentation

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun String.plurals(): String = this + if (this.last().equals('x', false)) "es" else "s"