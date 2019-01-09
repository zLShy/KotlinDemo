package basic

import android.util.Log
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by zhangli on 2018/12/27.
 */
fun 我要开始协程啦(block: suspend ()-> Unit){
    block.startCoroutine(BaseContinuation())
}

suspend fun 我要开始加载图片啦(url: String) = suspendCoroutine<ByteArray> {
    continuation ->
    Log.e("TGA","耗时操作，下载图片")
    try {
        val responseBody = HttpService.service.getLogo().execute()
        if(responseBody.isSuccessful){
            responseBody.body()?.byteStream()?.readBytes()?.let(continuation::resume)
        }else{
            continuation.resumeWithException(Exception("hhh"))
        }
    } catch(e: Exception) {
        continuation.resumeWithException(e)
        Log.e("CON",e.toString())
    }
}