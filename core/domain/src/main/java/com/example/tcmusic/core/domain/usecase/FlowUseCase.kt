/**
 * Created by Nguyen Thanh Cong on 1/5/2021.
 * Beowulf company.
 * Copyright © 2020 Beowulf Inc. All rights reserved.
 */

package com.example.tcmusic.core.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import com.example.tcmusic.core.common.result.Result
import kotlinx.coroutines.flow.*

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<Result<R>> = execute(parameters)
        .map<R, Result<R>> { Result.Success(it) }
        .onStart { Result.Loading }
        .catch { t -> emit(Result.Error(t)) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<R>
}
