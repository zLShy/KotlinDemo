package model

import retrifitRequest.CallBack

/**
 * Created by zhangli on 2018/11/12.
 */
interface ILoginDao {
    fun checkUser(userName:String,userPass:String,mCallBack: CallBack)
}