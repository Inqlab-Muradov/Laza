package com.inqlab.laza.DI

import android.content.Context
import android.content.SharedPreferences
import com.inqlab.laza.api.CommentService
import com.inqlab.laza.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideService() : ProductService{
       return  Retrofit.Builder().baseUrl("https://api.escuelajs.co/api/v1/")
           .addConverterFactory(GsonConverterFactory.create()).build()
           .create(ProductService::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentService() : CommentService{
        return Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CommentService::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context) : SharedPreferences{
        return context.getSharedPreferences("MySharedPref",Context.MODE_PRIVATE)
    }

}