package inspect.cegzm.com

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zl.map.Utils.BaseActicity
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.app.usage.NetworkStats
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.TrafficStats
import android.os.RemoteException
import android.provider.Settings
import android.util.Log
import android.telephony.TelephonyManager
import com.zl.map.Utils.Constants
import kotlinx.android.synthetic.main.activity_flow_test.*
import utils.FlowUtils
import java.util.*
import java.util.jar.Manifest
import kotlin.text.Typography.tm


class FlowTestActivity : BaseActicity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_test)
        var intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
//        startActivity(intent)
       if (hasPermission(android.Manifest.permission.READ_PHONE_STATE)){
           Log.e("TGA",FlowUtils.getPrintSize(getAllBytesMobile())+"====uid"+getUid())
           flow_tv.setText(FlowUtils.getPrintSize(getAllBytesMobile()))
       }else{
           requestPermission(Constants.PHONE_STATE_CODE,android.Manifest.permission.READ_PHONE_STATE)

       }
    }

    @SuppressLint("NewApi")
            /**
     * 本机使用的 mobile 总流量
     */
    fun getAllBytesMobile():Long {
       var  bucket:NetworkStats.Bucket
        val networkStatsManager = getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager

        try {
            bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE,
                    getSubscriberId(this@FlowTestActivity,ConnectivityManager.TYPE_MOBILE),
                    getTimesMonthMorning(),
                    System.currentTimeMillis())
        } catch (e: RemoteException) {
            return -1
        }
        //这里可以区分发送和接收
        return bucket.getTxBytes() +bucket.getRxBytes()
    }

    @SuppressLint("MissingPermission")
    fun getSubscriberId(context:Context, networkType:Int):String {
        if (ConnectivityManager.TYPE_MOBILE == networkType) {
            var tm =  context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            Log.e("TGA","phoneid==="+tm)
            return tm.getSubscriberId()
        }
        return ""
    }

    override fun readPhone() {
        super.readPhone()
        Log.e("TGA",getAllBytesMobile().toString())
    }

    fun getTimesMonthMorning():Long {
        var cal = Calendar.getInstance()
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0)
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH))
        return cal.getTimeInMillis()
    }

    @SuppressLint("WrongConstant")
    fun getUid():String {
        try {
            val pm = packageManager
            val ai = pm.getApplicationInfo("com.soobooo.application", PackageManager.GET_ACTIVITIES)
            Log.d("!!", "!!" + ai.uid)
            return ai.uid.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ""
    }
}
