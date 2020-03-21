package com.example.app.core.platform.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
    fun newThread(): Scheduler
    fun computation(): Scheduler
    fun ui(): Scheduler
    fun cachedPool(): Scheduler
    fun singleThreadScheduler(): Scheduler
    fun single(): Scheduler
}