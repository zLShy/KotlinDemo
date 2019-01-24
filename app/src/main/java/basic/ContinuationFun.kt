package basic

import android.util.Log
import okhttp3.*
import retrifitRequest.ApiMethods
import retrifitRequest.CallBack
import retrifitRequest.ProgressObserver
import java.io.IOException
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by zhangli on 2018/12/27.
 */
class ContinuationFun {

    fun beforeContinuation(block: suspend() -> Unit) {
        block.startCoroutine(BaseContinuation())
    }

    suspend fun startContinuation(url:String) = suspendCoroutine<ByteArray> {

        continuation ->

        Log.e("CON","start")
       try {
//           var client = OkHttpClient()
//           var request = Request.Builder().url("http://www.imooc.com/static/img/index/logo.png?t=1.1").build()
//           var call = client.newCall(request)
//           Log.e("CON","start")
//           var data = call.execute()
//           val responseBody = HttpService.service.getLogo(url).execute()
//           if(responseBody.isSuccessful){
//               responseBody.body()?.byteStream()?.readBytes()?.let(continuation::resume)
//           }else{
//               continuation.resumeWithException(throw Exception("hhh"))
//           }
//           ApiMethods().getInspectInfo(ProgressObserver(object : CallBack {
//               override fun onSuccess(any: Any) {
//                   any?.let { continuation::resume }
//               }
//
//               override fun onFailure(any: Any) {
//                   Log.e("TGA","Failure");
//
//               }
//
//           }))
           Log.e("CON","finish")
       }catch (e:Exception){
           continuation.resumeWithException(e)
           Log.e("CON",e.printStackTrace().toString())


       }
    }
}