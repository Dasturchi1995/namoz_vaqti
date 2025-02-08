package com.example.namozvaqtlari.data.repository

import com.example.ibroxim.model.DailyResponse
import com.example.namozvaqtlari.data.remoute.ResponseResult
import com.example.namozvaqtlari.data.remoute.RetrofitApi
import javax.inject.Inject

class PreyingRepository @Inject constructor(
    private val api: RetrofitApi
) {

    suspend fun requestDaily(date:String, queryMap:HashMap<String, Any>): ResponseResult<DailyResponse>{
        return try {
            val response = api.requestDaily(
                date = date,
                queryMap = queryMap
            )
            println("requestDaily response=$response")
            if (response.isSuccessful && response.code() == 200){
                if (response.body() != null){
                    val dailyResponse = response.body()
                    ResponseResult.Success(data = dailyResponse)
                }
                else{
                    ResponseResult.Error(message = "There is not data.")
                }
            }
            else if (response.code() == 404){
                ResponseResult.Error(message = "There is an error on the server.")
            }
            else ResponseResult.Error(message = "Something went wrong!")
        }
        catch (e:Exception){
            e.printStackTrace()
            ResponseResult.Error(message = "Server connection error")
        }
    }
}