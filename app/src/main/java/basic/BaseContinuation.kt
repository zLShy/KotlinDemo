package basic

import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext

/**
 * Created by zhangli on 2018/12/27.
 */
class BaseContinuation:Continuation<Unit> {
    override val context: CoroutineContext = EmptyCoroutineContext
    override fun resume(value: Unit) {

    }

    override fun resumeWithException(exception: Throwable) {

    }

}