package com.example.scotiabankproject.di

import com.example.scotiabankproject.network.GithubApi
import com.example.scotiabankproject.repository.UserRepository
import com.example.scotiabankproject.viewmodel.RepoDetailsViewModel
import com.example.scotiabankproject.viewmodel.UserViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit() }
    single { provideApiService(get()) }

}

val repositoryModule = module {
    single { UserRepository(get()) }
}

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { RepoDetailsViewModel() }
}

fun provideRetrofit(): Retrofit {
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(logging) // Add the logging interceptor
        .build()

    return Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideApiService(retrofit: Retrofit): GithubApi {
    return retrofit.create(GithubApi::class.java)
}