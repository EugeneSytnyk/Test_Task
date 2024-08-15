package eugene.sytnyk.testtask.usecase

import android.icu.util.Calendar
import eugene.sytnyk.testtask.mapper.mapTo
import eugene.sytnyk.testtask.model.ActionUI
import eugene.sytnyk.testtask.network.NetworkStateManager
import eugene.sytnyk.testtask.repository.ActionDataRepository
import eugene.sytnyk.testtask.repository.ActionUsageTimeRepository
import javax.inject.Inject

class GetActionUseCase @Inject constructor(
    private val actionDataRepository: ActionDataRepository,
    private val actionUsageTimeRepository: ActionUsageTimeRepository,
    private val networkStateManager: NetworkStateManager
) {

    companion object {
        private val CHECK_CONNECTION_TYPE = setOf("toast")
    }

    suspend fun getAction(): ActionUI? {
        val actions = actionDataRepository.getActionData().getOrNull() ?: return null
        val selectedAction = actions.filter { it.enabled }
            .sortedByDescending { it.priority }
            .filter { dataMeetsCondition(it.validDays) }
            .filter { lastUsageTimeMeetsCondition(it.type, it.coolDown) }
            .firstOrNull { internetConnectionMeetsCondition(it.type) } ?: return null

        val currentTime = System.currentTimeMillis()
        actionUsageTimeRepository.setLastUsageTimeForActionType(selectedAction.type, currentTime)

        return selectedAction.mapTo()
    }

    private fun dataMeetsCondition(availableDates: List<Int>): Boolean {
        // -1 because calendar returns dates in range 1..7
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1
        return availableDates.contains(currentDay)
    }

    private suspend fun lastUsageTimeMeetsCondition(type: String, coolDown: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUsage = actionUsageTimeRepository.getLastUsageTimeForActionType(type) ?: return true
        return currentTime - lastUsage < coolDown
    }

    private fun internetConnectionMeetsCondition(type: String): Boolean {
        if (type !in CHECK_CONNECTION_TYPE) return true
        return networkStateManager.hasInternetConnection()
    }
}
