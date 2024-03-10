package com.deloitte.base.data.factory.base

import com.deloitte.base.domain.Strategy

abstract class BaseRepositoryFactory<T> {
    abstract fun create(strategy: Strategy = Strategy.MOCK): T?
}
