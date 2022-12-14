package com.example.domain

import kotlinx.coroutines.flow.*

/**
 * Created by TC on 14/12/2022.
 */

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class SharedFlowUseCase<in P, R> {
    operator fun invoke(parameters: P): SharedFlow<Result<R>> = execute(parameters)

    protected abstract fun execute(parameters: P): SharedFlow<Result<R>>
}
