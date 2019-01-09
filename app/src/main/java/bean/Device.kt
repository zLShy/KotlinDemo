package bean

/**
 * Created by zhangli on 2019/1/9.
 */
class Device {
    var imei:String? = null
    var imsi:String? = null
    var iccid:String? = null
    constructor( imei: String, imsi: String,iccid: String){
        this.imei = imei
        this.imsi = imsi
        this.iccid = iccid
    }


    override fun toString(): String {
        return "Device(imei=$imei, imsi=$imsi, iccid=$iccid)"
    }
}