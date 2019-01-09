package basic

import okhttp3.ResponseBody
import retrofit2.http.GET

/**
 * Created by zhangli on 2018/12/27.
 */
object HttpService {

    val service by lazy{
        val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl("http://www.imooc.com")
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()

        retrofit.create(basic.Service::class.java)
    }

}

interface Service{

    @GET("/static/img/index/logo.png?t=1.1")
    fun getLogo(): retrofit2.Call<ResponseBody>

}