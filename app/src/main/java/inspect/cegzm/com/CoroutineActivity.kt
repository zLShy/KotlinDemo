package inspect.cegzm.com

import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.util.Log
import basic.AndroidCommonPool
import basic.HttpService
import com.zl.map.Utils.BaseActicity
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

class CoroutineActivity : BaseActicity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
//        Thread(TestRunable()).start()



        testContinuation()
    }


    fun testContinuation() = runBlocking {
        var job = launch(UI) {
            repeat(10) {
                i ->
                Log.e("TGA","挂起==")
                delay(1000L)

            }
        }
        val job2 = async(AndroidCommonPool) {
            delay(2000L)
           val data = HttpService.service.getLogo().execute()
            return@async data
        }
        Log.e("TGA","job2 返回的内容：${job2.await()}")
        var date = job2.await()?.body()?.byteStream()?.readBytes()
        var bitmap = BitmapFactory.decodeByteArray(date,0,date!!.size)
        logo_iv.setImageBitmap(bitmap)
        Log.e("TGA","主线程开始等待-----")
        delay(3000L)
        Log.e("TGA","主线程等待结束-----取消launch开启的协程")
        job.cancel()//协程的启动和停止都是代码可控的

        Log.e("TGA","主线程执行完毕，即将退出-----")
    }

    private class TestRunable : Runnable{
        override fun run() {
            while (true){
                try {
                    Thread.sleep(2000)
                }catch (e:Exception){
                    e.printStackTrace()
                }
                Log.e("TGA","Test")
            }
        }

    }

}
