package eugene.sytnyk.testtask.model

import com.google.gson.annotations.SerializedName

data class ActionData(
    val type: String,
    val enabled: Boolean,
    val priority: Int,
    @SerializedName("valid_days") val validDays: List<Int>,
    @SerializedName("cool_down") val coolDown: Long
)
