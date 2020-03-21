package com.example.app.core.platform.extensions

import io.reactivex.Flowable
import io.reactivex.Observable

fun <T> List<T>.toObservable(): Observable<T> {
    return Observable.fromIterable(this)
}

fun <E> List<E>.toFlowable(): Flowable<E> {
    return Flowable.fromIterable(this)
}