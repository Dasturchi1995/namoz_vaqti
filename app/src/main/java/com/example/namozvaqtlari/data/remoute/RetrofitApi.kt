package com.example.namozvaqtlari.data.remoute
import com.example.ibroxim.model.DailyResponse
import com.example.ibroxim.model.MonthlyResponse
import com.example.namozvaqtlari.common.Util
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RetrofitApi {

    @GET("/v1/timings/date={date}")
    suspend fun requestDaily(
        @Path("date") date: String = Util.getTodayDate(),
        @QueryMap queryMap:HashMap<String, Any>,
    ): Response<DailyResponse>

    @GET("/v1/calendar")
    suspend fun requestMonthly(
        @QueryMap queryMap: HashMap<String, Any>
    ): Response<MonthlyResponse>
}