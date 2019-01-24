package inspect.cegzm.com

import android.Manifest
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationProvider
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import com.zl.map.Utils.BaseActicity
import com.zl.map.Utils.Constants
import kotlinx.android.synthetic.main.activity_gps_test.*
import utils.QRCodeUtil
import android.telephony.TelephonyManager
import bean.Device
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import okhttp3.ResponseBody
import org.json.JSONObject
import retrifitRequest.ApiMethods
import retrifitRequest.CallBack
import retrifitRequest.ProgressObserver
import retrifitRequest.SdCardUtils
import retrofit2.Response


class GpsTestActivity : BaseActicity() {


    private val GPS_LOCATION_NAME = android.location.LocationManager.GPS_PROVIDER
    var permissions  = arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps_test)
        if (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && hasPermission(Manifest.permission.READ_PHONE_STATE)){
            getGPS()
            getPhoneInfo()
            showOpenInfo();
        }else{
            requestMorePermissions(Constants.MIX_AUTHORITY_CODE,permissions)
        }



    }

    private fun showOpenInfo() {
        if (SdCardUtils.redSavaInfo().equals("")){
            open_info.setText("开机上传失败次数: 0")
        }else{
            open_info.setText("开机上传失败次数: "+SdCardUtils.redSavaInfo())
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    override fun doMixFunction() {
        super.doMixFunction()
        getGPS()
        getPhoneInfo()
        showOpenInfo()
    }
    @SuppressLint("MissingPermission")
    fun getGPS() {
        var  pLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

       var isGpsEnabled = pLocationManager.isProviderEnabled(GPS_LOCATION_NAME)

       if (isGpsEnabled){
           gps_switch_tv.setText("GPS_open")
       }else{
           gps_switch_tv.setText("GPS_close")
       }


        var locateType =  android.location.LocationManager.GPS_PROVIDER

        val location = pLocationManager.getLastKnownLocation(locateType) // 通过GPS获取位置
        if (location != null){
            upUi(location)
        }
        pLocationManager.requestLocationUpdates(locateType,1000,0F,LoctionListener)

    }

    private fun upUi(location: Location?) {
        latitude_tv.setText("latitude="+location!!.latitude)
        longitude_tv.setText("longitude="+location!!.longitude)
    }

    private var LoctionListener = object : LocationListener{
        override fun onLocationChanged(location: Location?) {
            upUi(location)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            when(status){
                LocationProvider.AVAILABLE-> gps_tv.setText("GPS_AVAILABLE")
                LocationProvider.OUT_OF_SERVICE-> gps_tv.setText("GPS_OUT_OF_SERVICE")

            }
        }

        override fun onProviderEnabled(provider: String?) {

        }

        override fun onProviderDisabled(provider: String?) {

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    fun getPhoneInfo() {
        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        phone_imei.setText("IMEI:  "+tm.deviceId)
        phone_imsi.setText("IMSI:  "+tm.subscriberId)
        phone_iccid.setText("ICCID:  "+tm.simSerialNumber)
        try {
            var mDevice = Device(tm.deviceId,tm.subscriberId,tm.simSerialNumber)
            var requestStr = Gson().toJson(mDevice)
            qrcode.setImageBitmap(QRCodeUtil.createQRCodeBitmap(requestStr, 400))
        }catch (e:Exception){
            qrcode.setImageBitmap(QRCodeUtil.createQRCodeBitmap(Gson().toJson(Device(phone_imei.text.toString().replace("IMEI:  ",""),phone_imsi.text.toString().replace("IMSI:  ",""),phone_iccid.text.toString().replace("ICCID:  ",""))), 400))
            e.printStackTrace()
        }

    }

}
