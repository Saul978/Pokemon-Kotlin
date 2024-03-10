package com.deloitte.base.domain.base

import com.deloitte.base.domain.base.schedulers.BaseSchedulerProvider
import com.deloitte.base.domain.base.schedulers.SchedulerProvider
import io.reactivex.Single

abstract class SingleUseCase<T>(private val schedulerProvider: BaseSchedulerProvider = SchedulerProvider()) :
    UseCaseDisposable() {

    open fun execute(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) =
        addDispose(createSingle().subscribe(onSuccess, onError))

    private fun createSingle() =
        buildUseCase().subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())

    abstract fun buildUseCase(): Single<T>
}
