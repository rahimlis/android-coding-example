package com.example.app.core.platform.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class DefaultSchedulerProvider : SchedulerProvider {
    override fun cachedPool() = Schedulers.from(Executors.newCachedThreadPool())
    override fun singleThreadScheduler() = Schedulers.from(Executors.newSingleThreadExecutor())
    override fun newThread() = Schedulers.newThread()
    override fun computation() = Schedulers.computation()
    override fun ui() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
    override fun single() = Schedulers.single()
}