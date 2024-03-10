package com.deloitte.base.di.module

import android.content.Context
import androidx.room.Room
import com.deloitte.base.data.api.Api
import com.deloitte.base.data.factory.ApiRepositoryFactory
import com.deloitte.base.data.factory.room.LikesDatabase
import com.deloitte.base.data.repository.mock.ApiRepositoryMockImpl
import com.deloitte.base.data.repository.network.ApiRepositoryNetworkImpl
import com.deloitte.base.data.api.LikeDAO
import com.deloitte.base.domain.entity.Likes
import com.deloitte.base.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {

    @Provides
    @Singleton
    fun provideApiRepositoryFactory(
        api: Api
    ): ApiRepositoryFactory =
        ApiRepositoryFactory(
            ApiRepositoryMockImpl(), ApiRepositoryNetworkImpl(api)
        )

    @Provides
    fun provideGson(): Gson =
        GsonBuilder().create()

    @Named("okHttpClient")
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(
                Constants.General.HTTP_CONNECT_TIMEOUT,
                TimeUnit.MILLISECONDS
            )
            .readTimeout(Constants.General.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideRetrofit(
        gson: Gson,
        @Named("okHttpClient") okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.Api.URL_API)
            .client(okHttpClient)
            .build()


    @Provides
    fun provideApiService(
        retrofit: Retrofit,
        @Named("okHttpClient") okHttpClient: OkHttpClient
    ): Api = retrofit.newBuilder().client(okHttpClient).build().create(Api::class.java)



    @Singleton
    @Provides
    fun provideLikeDatabase(@ApplicationContext context:Context)= Room.databaseBuilder(
        context,LikesDatabase::class.java,"likes")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: LikesDatabase)=db.likeDao()

    @Provides
    fun provideEntity()=Likes()
}