package com.example.app.core.platform.scheduler

import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(private val scheduler: TestScheduler) : SchedulerProvider {
    override fun newThread() = scheduler
    override fun computation() = scheduler
    override fun ui() = scheduler
    override fun io() = scheduler
    override fun cachedPool() = scheduler
    override fun singleThreadScheduler() = scheduler
    override fun single() = scheduler
}