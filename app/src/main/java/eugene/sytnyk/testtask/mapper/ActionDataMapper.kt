package eugene.sytnyk.testtask.mapper

import eugene.sytnyk.testtask.model.ActionData
import eugene.sytnyk.testtask.model.ActionUI

fun ActionData.mapTo(): ActionUI? {
    return when (type) {
        "animation" -> ActionUI.Animation
        "toast" -> ActionUI.ToastMessage("Action is Toast!")
        "call" -> ActionUI.Call
        "notification" -> ActionUI.Notification
        else -> {
            null // Unsupported type handling
        }
    }
}
