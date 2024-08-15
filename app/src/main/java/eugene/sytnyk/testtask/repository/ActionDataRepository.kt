package eugene.sytnyk.testtask.repository

import eugene.sytnyk.testtask.model.ActionData
import eugene.sytnyk.testtask.network.ActionDataService
import retrofit2.awaitResponse
import javax.inject.Inject

class ActionDataRepository @Inject constructor(
    private val actionDataService: ActionDataService
) {

    suspend fun getActionData(): Result<List<ActionData>> {
        return runCatching {
            actionDataService.getActionConfigs().awaitResponse()
        }.map {
            it.body().orEmpty()
        }
    }
}
