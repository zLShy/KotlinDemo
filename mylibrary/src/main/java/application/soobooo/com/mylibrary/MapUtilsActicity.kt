package application.soobooo.com.mylibrary

import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager

/**
 * Created by zhangli on 2018/7/27.
 */
open class MapUtilsActicity : AppCompatActivity() {

    /**
     * 判断是否有权限
     *
     * @param permissions
     * @return
     */
    fun hasPermission(vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * 申请权限方法
     *
     * @param code        返回码
     * @param permissions 具体权限
     */
    fun requestPermission(code: Int, vararg permissions: String) {
        ActivityCompat.requestPermissions(this, permissions, code)
    }

    /**
     * 申请权限方法
     *
     * @param code        返回码
     * @param permissions 具体权限
     */
    fun requestMorePermissions(code: Int, permissions: Array<String>) {
        ActivityCompat.requestPermissions(this, permissions, code)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.Companion.WRITE_READ_EXTERNAL_CODE -> doSDcardPermission()
            Constants.Companion.CAMERA_CODE -> takePhoto()
            Constants.Companion.CALL_PHONE_CODE -> callPhone()
            Constants.Companion.SEM_MESSAGE_CODE -> sendSEM()
            Constants.Companion.PHONE_LOCATION_CODE -> getLocation()
            Constants.Companion.PHONE_STATE_CODE -> readPhone()
            Constants.Companion.MIX_AUTHORITY_CODE -> doMixFunction()
            else -> {
            }
        }
    }

    open fun readPhone() {

    }

    open fun getLocation() {

    }

   open fun sendSEM() {

    }

    open fun callPhone() {

    }

    open fun takePhoto() {

    }

    open fun doSDcardPermission() {

    }
    open fun doMixFunction() {

    }

    override fun onResume() {
        super.onResume()
//        hideBottomUIMenu()
//        steepStatusBar()
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected fun hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
            decorView.systemUiVisibility = uiOptions
        }

    }

    /**
     * [沉浸状态栏]
     */
    private fun steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            window.addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // 透明导航栏
            window.addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)


        }
    }

}