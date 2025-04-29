package com.jishojose.core.android.di

import android.content.Context
import com.jishojose.core.android.util.StringResource
import com.jishojose.core.android.util.StringResourceImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class StringResourceModule {
    @Provides
    @Reusable
    fun bindStringResource(@ApplicationContext context: Context): StringResource =
        StringResourceImpl(context)
}
