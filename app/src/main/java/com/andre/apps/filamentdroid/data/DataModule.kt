package com.andre.apps.filamentdroid.data

import com.andre.apps.filamentdroid.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    @Reusable
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                var request = chain.request()
                val url = request.url.newBuilder().build()
                val cacheControl = CacheControl.Builder()
                    .maxAge(5, TimeUnit.MINUTES)
                    .build()

                request = request.newBuilder().header(
                    "Cache-Control", cacheControl.toString()
                ).url(url).build()

                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideTypeAdapter(): TypeAdapterFactory {
        return object : TypeAdapterFactory {

            override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T> {
                val delegate = gson!!.getDelegateAdapter(this, type)
                val elementAdapter = gson.getAdapter(JsonElement::class.java)

                return object : TypeAdapter<T>() {

                    override fun write(out: JsonWriter?, value: T) {
                        delegate.write(out, value)
                    }

                    override fun read(`in`: JsonReader?): T {
                        val jsonElement = elementAdapter.read(`in`)
                        return delegate.fromJsonTree(jsonElement)
                    }
                }.nullSafe()
            }
        }
    }

    @Provides
    @Reusable
    fun provideGson(typeAdapterFactory: TypeAdapterFactory): Gson {
        return GsonBuilder()
            .registerTypeAdapterFactory(typeAdapterFactory)
            .create()
    }

    @Provides
    @Reusable
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}