package com.deloitte.base.data.factory

import com.deloitte.base.data.factory.base.BaseRepositoryFactory
import com.deloitte.base.data.repository.mock.ApiRepositoryMockImpl
import com.deloitte.base.data.repository.network.ApiRepositoryNetworkImpl
import com.deloitte.base.domain.Strategy
import com.deloitte.base.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryFactory @Inject constructor(
    private val likeItRepositoryMockImpl: ApiRepositoryMockImpl,
    private val likeItRepositoryNetworkImpl: ApiRepositoryNetworkImpl
) : BaseRepositoryFactory<ApiRepository>() {

    override fun create(strategy: Strategy): ApiRepository =
        when (strategy) {
            Strategy.NETWORK -> likeItRepositoryNetworkImpl
            else -> likeItRepositoryMockImpl
        }
}
