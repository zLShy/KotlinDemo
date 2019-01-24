package retrifitRequest

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * Created by zhangli on 2018/11/8.
 */
class ProgressObserver:Observer<Response<ResponseBody>> {
    private var mCallBacks: CallBack? = null

    constructor(mCallBacks: CallBack?) {
        this.mCallBacks = mCallBacks
    }

    override fun onNext(t: Response<ResponseBody>) {
        mCallBacks!!.onSuccess(t!!)
    }

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable?) {
    }

    override fun onError(e: Throwable?) {
        mCallBacks!!.onFailure(e!!)
    }
}