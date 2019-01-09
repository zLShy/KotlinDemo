package com.zl.map.Utils

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

/**
 * Created by zhangli on 2018/7/27.
 */
open class BaseActicity: AppCompatActivity() {

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
            Constants.WRITE_READ_EXTERNAL_CODE -> doSDcardPermission()
            Constants.CAMERA_CODE -> takePhoto()
            Constants.CALL_PHONE_CODE -> callPhone()
            Constants.SEM_MESSAGE_CODE -> sendSEM()
            Constants.PHONE_LOCATION_CODE -> getLocation()
            Constants.PHONE_STATE_CODE -> readPhone()
            Constants.MIX_AUTHORITY_CODE -> doMixFunction()
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
}