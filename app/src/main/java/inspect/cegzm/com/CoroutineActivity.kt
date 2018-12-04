package inspect.cegzm.com

import android.os.Bundle
import android.util.Log
import com.zl.map.Utils.BaseActicity

class CoroutineActivity : BaseActicity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        Thread(TestRunable()).start()
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
