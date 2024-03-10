package com.deloitte.base.domain.base

abstract class SynchronousUseCase<T>() {
    abstract fun execute(): T
}
