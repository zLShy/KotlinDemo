package view

/**
 * Created by zhangli on 2018/11/12.
 */
interface ILoginView {
    fun onClearText()
    fun getUserName():String
    fun getUserPass():String
    fun showProgress()
    fun hideProgress()
    fun loginSuccess()
    fun loginFailure()
}