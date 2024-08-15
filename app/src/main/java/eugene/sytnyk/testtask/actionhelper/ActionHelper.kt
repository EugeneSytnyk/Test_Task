package eugene.sytnyk.testtask.actionhelper

import android.content.Context
import android.widget.Toast
import eugene.sytnyk.testtask.model.ActionUI

object ActionHelper {

    fun createNotification(context: Context, model: ActionUI.Notification) {
        // TODO add notification action handling
    }

    fun showToast(context: Context, model: ActionUI.ToastMessage) {
        Toast.makeText(context, model.message, Toast.LENGTH_SHORT).show()
    }

    fun openChooseContact(context: Context, model: ActionUI.Call) {
        // TODO add choose contact action handling
    }
}
