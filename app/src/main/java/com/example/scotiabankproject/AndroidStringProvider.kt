package com.example.scotiabankproject

import android.content.Context

class AndroidStringProvider(private val context: Context) : StringProvider {
    override fun getString(resId: Int): String = context.getString(resId)
}
