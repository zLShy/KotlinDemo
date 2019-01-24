package inspect.cegzm.com

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import retrifitRequest.ApiMethods
import retrifitRequest.CallBack
import retrifitRequest.ProgressObserver
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import basic.ContinuationFun
import basic.我要开始加载图片啦
import basic.我要开始协程啦
import com.zl.map.Utils.BaseActicity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.launch
import okhttp3.*
import java.io.IOException

class MainActivity : BaseActicity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var add = {x:Int,y:Int ->
            x+y
        }
        Log.e("TGA", add(2,3).toString())
//        ApiMethods().getInspectInfo(ProgressObserver(object : CallBack {
//            override fun onSuccess(any: Any) {
//                Log.e("TGA",any.toString());
//            }
//
//            override fun onFailure(any: Any) {
//                Log.e("TGA","Failure");
//
//            }
//
//        }))

        load_btn.setOnClickListener { v ->

            var job = launch {
//                ApiMethods().getInspectInfo(ProgressObserver(object : CallBack {
//                    override fun onSuccess(any: Any) {
//                        Log.e("TGA",any.toString());
//                    }
//
//                    override fun onFailure(any: Any) {
//                        Log.e("TGA","Failure");
//
//                    }
//
//                }))
            }
            var date = "s".let {
                "123"
                "123"
                "123"
                "456"
            }
            Log.e("TGA","let==="+date)
            ContinuationFun().beforeContinuation{
                Log.e("CON","before")
                val data = ContinuationFun().startContinuation("")
//                var bitmap = BitmapFactory.decodeByteArray(data,0,data.size)
//                imagedata.setImageBitmap(bitmap)
                login_btn.setText(data.toString())
            }
//            var client = OkHttpClient()
//            var request = Request.Builder().url("http://www.imooc.com/static/img/index/logo.png?t=1.1").build()
//            var call = client.newCall(request)
//            Log.e("CON","start")
//            call.enqueue(object : Callback {
//                override fun onFailure(call: Call?, e: IOException?) {
//                }
//
//                override fun onResponse(call: Call?, response: Response?) {
//                    if (response!!.isSuccessful){
//                        var data = response.body()?.byteStream()?.readBytes();
//                        var bitmap = BitmapFactory.decodeByteArray(data,0,data!!.size)
//                        var msg = mHandler.obtainMessage()
//                        msg.obj = bitmap
//                        mHandler.sendMessage(msg)
//
//                    }else{
//                        Log.e("TGA","sb")
//                    }
//                }
//
//            })
//
        }

    }

    var mHandler = object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            var bitmap = msg!!.obj as Bitmap
            imagedata.setImageBitmap(bitmap)
        }
    }

}
