package presenter

import retrifitRequest.CallBack
import model.ILoginDao
import model.LoginImp
import view.ILoginView

/**
 * Created by zhangli on 2018/11/12.
 */
class LoginPresenterImp(iLoginView: ILoginView):ILoginPresenter {
    private var mILoginDao: ILoginDao? = null
    private var mILoginView: ILoginView? = null

    init {
        this.mILoginView = iLoginView
        this.mILoginDao = LoginImp()
    }
    override fun doLogin() {
        mILoginView!!.showProgress()
//        mILoginDao!!.checkUser(mILoginView!!.getUserName(),mILoginView!!.getUserPass(),object : CallBack {
//            override fun onSuccess(any: Any) {
//                mILoginView!!.hideProgress()
//
//            }
//
//            override fun onFailure(any: Any) {
//                mILoginView!!.hideProgress()
//            }
//
//        })
    }

}