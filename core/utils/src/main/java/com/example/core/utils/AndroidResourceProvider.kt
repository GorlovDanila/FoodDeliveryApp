package com.example.core.utils

import android.content.Context

class AndroidResourceProvider(
    private val context: Context
) : ResourceProvider {

    override fun getString(id: Int): String = context.getString(id)

    override fun getColor(id: Int): Int = context.getColor(id)

}
