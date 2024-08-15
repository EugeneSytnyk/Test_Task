package eugene.sytnyk.testtask.network

import eugene.sytnyk.testtask.model.ActionData
import retrofit2.Call
import retrofit2.http.GET

interface ActionDataService {

    @GET("butto_to_action_config.json")
    fun getActionConfigs(): Call<List<ActionData>>
}
