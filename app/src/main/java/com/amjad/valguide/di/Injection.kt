package com.amjad.valguide.di

import android.content.Context
import com.amjad.valguide.data.Repository

object Injection {

    fun provideRepository(context: Context): Repository {
//        val database = TrafficDatabase.getDatabase(context)
        return Repository.getInstance()
    }

}