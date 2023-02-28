package com.example.tcmusic.core.common.result

import app.cash.turbine.test
import com.example.tcmusic.core.common.result.Result
import com.example.tcmusic.core.common.result.asResult
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * Created by TC on 23/02/2023.
 */
class ResultTest {
    @Test
    fun`Result loading, success and error`() = runTest {
        flow {
            emit(0)
            throw Exception("Error")
        }
            .asResult()
            .test {
                assertEquals(Result.Loading, awaitItem())
                assertEquals(Result.Success(0), awaitItem())

                when (val error = awaitItem()) {
                    is Result.Error -> assertEquals("Error", error.exception?.message)
                    else -> throw IllegalStateException("The flow should have emitted an Error Result")
                }

                awaitComplete()
            }
    }
}
