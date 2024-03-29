package com.deloitte.base.domain.base

import com.deloitte.base.domain.Strategy
import com.deloitte.base.domain.base.schedulers.BaseSchedulerProvider
import com.deloitte.base.domain.base.schedulers.SchedulerProvider
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject

abstract class SingleSubjectUseCase<T>(private val schedulerProvider: BaseSchedulerProvider = SchedulerProvider()) :
    UseCaseDisposable() {

    private lateinit var strategy: Strategy
    private var subject: SingleSubject<T>? = null

    fun execute(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        strategy: Strategy
    ) =
        addDispose(createSubject(strategy).subscribe(onSuccess, onError))

    private fun createSubject(strategy: Strategy): SingleSubject<T> {
        if (subject == null || this.strategy != strategy) {
            this.strategy = strategy
            subject = SingleSubject.create<T>()
            buildUseCase(strategy).subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(subject!!)
        }
        return subject!!
    }

    abstract fun buildUseCase(strategy: Strategy): Single<T>
}
