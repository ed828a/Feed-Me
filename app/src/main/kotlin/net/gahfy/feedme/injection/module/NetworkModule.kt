package net.gahfy.feedme.injection.module

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import net.gahfy.feedme.network.PostApi
import net.gahfy.feedme.network.YoutubeApi
import net.gahfy.feedme.utils.BASE_URL
import net.gahfy.feedme.utils.YOUTUBE_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideYoutubeApi(): YoutubeApi{
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("YoutubeApi", it)
        })
        logger.level = HttpLoggingInterceptor.Level.BASIC

        val okHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()

        return Retrofit.Builder()
                .baseUrl(YOUTUBE_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YoutubeApi::class.java)
    }
}