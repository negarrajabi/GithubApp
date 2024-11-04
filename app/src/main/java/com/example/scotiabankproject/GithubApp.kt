package com.example.scotiabankproject

import android.app.Application
import com.example.scotiabankproject.di.networkModule
import com.example.scotiabankproject.di.repositoryModule
import com.example.scotiabankproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class GithubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start KOIN with your modules
        startKoin {
            androidContext(this@GithubApp)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}