package com.abc.premierleagueapp.core.di

import androidx.room.Room
import com.abc.premierleagueapp.core.data.ClubRepository
import com.abc.premierleagueapp.core.data.source.local.LocalDataSource
import com.abc.premierleagueapp.core.data.source.local.room.ClubDatabase
import com.abc.premierleagueapp.core.data.source.remote.RemoteDataSource
import com.abc.premierleagueapp.core.data.source.remote.network.ApiService
import com.abc.premierleagueapp.core.domain.repository.IClubRepository
import com.abc.premierleagueapp.core.util.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<ClubDatabase>().clubDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("password".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            ClubDatabase::class.java, "Club.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}
val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IClubRepository> {
        ClubRepository(get(),get(),get())
    }
}