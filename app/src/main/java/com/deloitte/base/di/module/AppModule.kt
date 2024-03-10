package com.deloitte.base.di.module

import android.content.Context
import android.content.SharedPreferences
import com.deloitte.base.data.repository.mock.PreferencesRepositoryMockImpl
import com.deloitte.base.data.repository.persistence.PreferencesRepositoryPreferencesImpl
import com.deloitte.base.utils.Constants
import com.example.msspa_megusta_library.data.factory.PreferenceRepositoryFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(Constants.Preferences.PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    fun providePreferenceFactory(
        sharedPreferences: SharedPreferences
    ): PreferenceRepositoryFactory =
        PreferenceRepositoryFactory(
            PreferencesRepositoryPreferencesImpl(sharedPreferences),
            PreferencesRepositoryMockImpl()
        )


}