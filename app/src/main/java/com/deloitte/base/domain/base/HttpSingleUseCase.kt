package com.deloitte.base.domain.base

import android.webkit.HttpAuthHandler
import com.deloitte.base.domain.base.schedulers.BaseSchedulerProvider
import com.deloitte.base.domain.base.schedulers.SchedulerProvider
import javax.inject.Inject

abstract class HttpSingleUseCase<T>(private val schedulerProvider: BaseSchedulerProvider = SchedulerProvider()) :
    SingleUseCase<T>() {

    @Inject
    lateinit var httpHandler: HttpAuthHandler

    override fun execute(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ): Boolean {

        return addDispose(createSingle().subscribe(onSuccess, this::onError))
    }

    private fun createSingle() =
        buildUseCase().subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())

    fun onError(throwable: Throwable) {
    }
}
