package com.example.core.utils

interface ResourceProvider {

    fun getString(id: Int): String

    fun getColor(id: Int): Int
}
