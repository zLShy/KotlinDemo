package model

import retrifitRequest.ApiMethods
import retrifitRequest.CallBack
import retrifitRequest.ProgressObserver

/**
 * Created by zhangli on 2018/11/12.
 */
class LoginImp:ILoginDao {

    override fun checkUser(userName: String, userPass: String, mCallBack: CallBack) {

        ApiMethods().getUserInfo(ProgressObserver(object : CallBack {
            override fun onSuccess(any: Any) {
                mCallBack.onSuccess(any!!)
            }

            override fun onFailure(any: Any) {
                mCallBack.onFailure(any!!)
            }

        }),userName,userPass)
    }

}