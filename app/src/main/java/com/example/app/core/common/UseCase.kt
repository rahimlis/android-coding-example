package com.example.app.core.common

interface UseCase<in Params, out Type> {

    fun run(param: Params): Type

    operator fun invoke(param: Params): Type {
        return run(param)
    }
}