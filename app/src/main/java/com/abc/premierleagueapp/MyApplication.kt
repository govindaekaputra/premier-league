package com.abc.premierleagueapp

import android.app.Application
import com.abc.premierleagueapp.core.di.databaseModule
import com.abc.premierleagueapp.core.di.networkModule
import com.abc.premierleagueapp.core.di.repositoryModule
import com.abc.premierleagueapp.di.useCaseModule
import com.abc.premierleagueapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}