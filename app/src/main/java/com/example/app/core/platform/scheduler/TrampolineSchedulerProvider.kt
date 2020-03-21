package com.example.app.core.platform.scheduler

import io.reactivex.schedulers.Schedulers

class TrampolineSchedulerProvider : SchedulerProvider {
    override fun newThread() = Schedulers.trampoline()
    override fun computation() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
    override fun cachedPool() = Schedulers.trampoline()
    override fun singleThreadScheduler() = Schedulers.trampoline()
    override fun single() = Schedulers.trampoline()
}