package com.imams.animalia.presentation

fun String.plurals(): String = this + if (this.last().equals("x")) "es" else "s"