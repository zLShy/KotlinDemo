package retrifitRequest

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * Created by zhangli on 2018/11/8.
 */
class ApiMethods {

    /**
     * 订阅
     */
    fun ApiSubscribe(mObservable: Observable<Response<ResponseBody>>, mObserver: Observer<Response<ResponseBody>>) {
        mObservable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver)
    }

//    fun getInspectInfo(mObserver: ProgressObserver) {
//        ApiSubscribe(RetrifitUtils().getApiService().getMovies(1,10),mObserver)
//    }

    /**
     * 检查用户登陆API
     */
//    fun getUserInfo(mObserver: ProgressObserver,userName: String, userPass: String) {
//        ApiSubscribe(RetrifitUtils().getApiService().checkUser(userName,userPass),mObserver)
//    }

    fun getHello(mObserver: ProgressObserver) {
        ApiSubscribe(RetrifitUtils().getApiService().getHello(),mObserver)
    }
}