package retrifitRequest

import okhttp3.ResponseBody
import retrofit2.Response

/**
 * Created by zhangli on 2018/11/8.
 */
interface CallBack {
    fun onSuccess(any: Response<ResponseBody>)
    fun onFailure(any: Any)
}