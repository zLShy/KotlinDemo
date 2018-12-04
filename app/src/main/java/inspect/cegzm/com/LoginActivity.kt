package inspect.cegzm.com

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.zl.map.Utils.BaseActicity
import kotlinx.android.synthetic.main.activity_main.*
import presenter.LoginPresenterImp
import view.ILoginView

class LoginActivity : BaseActicity(),ILoginView {

    override fun loginSuccess() {
        var mIntent = Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(mIntent)
    }

    override fun loginFailure() {
        Toast.makeText(this@LoginActivity,"loginFailure~",Toast.LENGTH_SHORT).show()
    }

    private var mILoginPresenterImp:LoginPresenterImp? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mILoginPresenterImp = LoginPresenterImp(this@LoginActivity)

        login_btn.setOnClickListener { v ->

            mILoginPresenterImp!!.doLogin()
        }
    }

    /**
     * 登陆失败清空数据
     */
    override fun onClearText() {

    }

    /**
     * 获取用户名
     */
    override fun getUserName(): String {

        return ""
    }

    /**
     * 获取用户密码
     */
    override fun getUserPass(): String {
        return ""
    }

    /**
     * 显示加载菊花
     */
    override fun showProgress() {

    }

    /**
     * 隐藏加载菊花
     */
    override fun hideProgress() {

    }
}