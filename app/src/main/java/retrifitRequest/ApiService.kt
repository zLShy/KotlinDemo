package retrifitRequest

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by zhangli on 2018/11/8.
 */
interface ApiService {

//    @FormUrlEncoded
//    @POST
    @GET("top250")
    fun getMovies(@Query("start") start :Int, @Query("count") count :Int): Observable<Any>

    @FormUrlEncoded
    @POST("")
    fun checkUser(@Field("") userName :String,@Field("") userPass :String):Observable<Any>

    @GET("hello")
    fun getHello():Observable<Response<ResponseBody>>
}