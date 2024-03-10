package com.deloitte.base.domain.base

import com.deloitte.base.domain.base.schedulers.BaseSchedulerProvider
import com.deloitte.base.domain.base.schedulers.SchedulerProvider
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

open class PublishSubjectUseCase<T : Any>(private val schedulerProvider: BaseSchedulerProvider = SchedulerProvider()) :
    UseCaseDisposable() {

    private val subject: Subject<T> = PublishSubject.create()

    fun execute(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) =
        addDispose(createPublishSubject().subscribe(onNext, onError))

    fun publish(item: T) = subject.onNext(item)

    private fun createPublishSubject() =
        subject.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
}
