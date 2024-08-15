package eugene.sytnyk.testtask.model

sealed interface ActionUI {

    data object Animation : ActionUI

    data class ToastMessage(val message: String) : ActionUI

    data object Call : ActionUI

    data class Notification(val message: String) : ActionUI
}
