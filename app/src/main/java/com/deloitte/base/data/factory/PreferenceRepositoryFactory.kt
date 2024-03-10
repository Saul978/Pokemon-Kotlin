package com.example.msspa_megusta_library.data.factory

import com.deloitte.base.data.factory.base.BaseRepositoryFactory
import com.deloitte.base.data.repository.mock.PreferencesRepositoryMockImpl
import com.deloitte.base.data.repository.persistence.PreferencesRepositoryPreferencesImpl
import com.deloitte.base.domain.Strategy
import com.deloitte.base.domain.repository.PreferencesRepository
import javax.inject.Inject

class PreferenceRepositoryFactory @Inject constructor(
    private val preferencesRepositoryPreferencesImpl: PreferencesRepositoryPreferencesImpl,
    private val preferencesRepositoryMockImpl: PreferencesRepositoryMockImpl
) :
    BaseRepositoryFactory<PreferencesRepository>() {
    override fun create(strategy: Strategy): PreferencesRepository =
        when (strategy) {
            Strategy.PREFERENCES -> preferencesRepositoryPreferencesImpl
            else -> preferencesRepositoryMockImpl
        }

}