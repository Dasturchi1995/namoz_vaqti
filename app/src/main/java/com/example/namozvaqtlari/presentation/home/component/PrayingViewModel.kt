package com.example.namozvaqtlari.presentation.home.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ibroxim.model.DailyResponse
import com.example.namozvaqtlari.common.SharedPref
import com.example.namozvaqtlari.common.Util
import com.example.namozvaqtlari.data.remoute.ResponseResult
import com.example.namozvaqtlari.data.repository.PreyingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrayingViewModel @Inject constructor(
    private val repository: PreyingRepository
): ViewModel(){
    private val _state = MutableStateFlow(PrayingState())
    val state = _state.asStateFlow()

    fun onEvent(event: PrayingEvent){
        when(event){
            PrayingEvent.RequestDailyPraying -> {
                requestDailyPraying()
                requestTomorrowPraying()
            }
            is PrayingEvent.SetRegion -> {
                _state.update {
                    it.copy(
                        region = event.region
                    )
                }
                SharedPref.regionIndex = Util.getUzbekistanRegions().indexOf(event.region)
                requestDailyPraying()
                requestTomorrowPraying()
            }

            PrayingEvent.RequestMonthlyPraying -> {
                requestMonthlyPraying()
            }
        }
    }

    private fun requestDailyPraying(){

        if (state.value.region == null){
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true
                )
            }


            val queryMap = HashMap<String, Any>()
            queryMap["city"] = state.value.region?.nameEn?:""
            queryMap["country"] = "UZ"
            queryMap["x7xapikey"] = "b19e9cfeffee0cc418481f6ec739b4c5"
            queryMap["method"] = 3
            queryMap["school"] = 1



            val result = repository.requestDaily(
                date = Util.getTodayDate(),
                queryMap = queryMap
            )
            when(result){
                is ResponseResult.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true,
                            errorMessage = "",
                        )
                    }
                }
                is ResponseResult.Success -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            errorMessage = "",
                            prayingData = result.data?.data
                        )
                    }
                }
                is ResponseResult.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            errorMessage = result.message?:"Something went wrong!",
                            prayingData = null
                        )
                    }
                }
            }
        }
    }

    private fun requestTomorrowPraying(){

        if (state.value.region == null){
            return
        }

        viewModelScope.launch {
            delay(2000)

            val queryMap = HashMap<String, Any>()
            queryMap["city"] = state.value.region?.nameEn?:""
            queryMap["country"] = "UZ"
            queryMap["x7xapikey"] = "b19e9cfeffee0cc418481f6ec739b4c5"
            queryMap["method"] = 3
            queryMap["school"] = 1

            val result = repository.requestDaily(
                date = Util.getTomorrowDate(),
                queryMap = queryMap
            )

            println("requestTomorrowPraying = date=${Util.getTomorrowDate()}")

            when(result){
                is ResponseResult.Loading -> {
                }
                is ResponseResult.Success -> {
                    _state.update {
                        it.copy(
                          ertangiBomdodVaqti = result.data?.data?.timings?.Fajr?:""
                        )
                    }
                }
                is ResponseResult.Error -> {
                }
            }
        }
    }

    private fun requestMonthlyPraying() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true
                )
            }


            val queryMap = HashMap<String, Any>()
            queryMap["city"] = state.value.region?.nameEn ?: ""
            queryMap["country"] = "UZ"
            queryMap["x7xapikey"] = "b19e9cfeffee0cc418481f6ec739b4c5"
            queryMap["method"] = 3
            queryMap["school"] = 1
            queryMap["year"] = Util.getThisYear()
            queryMap["month"] = Util.getThisMonth()


            val result = repository.requestMonthly(
                queryMap = queryMap
            )
            when (result) {
                is ResponseResult.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true,
                            errorMessage = "",
                        )
                    }
                }

                is ResponseResult.Success -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            errorMessage = "",
                            prayingDataList = result.data?.data ?: emptyList()
                        )
                    }
                }

                is ResponseResult.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            errorMessage = result.message ?: "Something went wrong!",
                            prayingDataList = emptyList()
                        )
                    }
                }
            }
        }
    }
}