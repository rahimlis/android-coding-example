package com.example.app.core.common

abstract class ProducerUseCase<out Type> : UseCase<Any, Type> {

    abstract fun run(): Type

    operator fun invoke(): Type {
        return run()
    }

    final override fun run(param: Any): Type {
        return run()
    }

    final override fun invoke(param: Any): Type {
        return run()
    }
}