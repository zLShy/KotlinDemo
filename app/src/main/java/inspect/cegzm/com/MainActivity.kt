package inspect.cegzm.com

import retrifitRequest.ApiMethods
import retrifitRequest.CallBack
import retrifitRequest.ProgressObserver
import android.os.Bundle
import android.util.Log
import com.zl.map.Utils.BaseActicity

class MainActivity : BaseActicity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var add = {x:Int,y:Int ->
            x+y
        }
        Log.e("TGA", add(2,3).toString())
        ApiMethods().getInspectInfo(ProgressObserver(object : CallBack {
            override fun onSuccess(any: Any) {
                Log.e("TGA",any.toString());
            }

            override fun onFailure(any: Any) {
                Log.e("TGA","Failure");

            }

        }))
    }
}
