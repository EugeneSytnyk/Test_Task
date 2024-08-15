package eugene.sytnyk.testtask.helper

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.widget.Toast

object ActionHelper {

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun openChooseContact(context: Context) {
        val chooseContactIntent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI).apply {
            flags = flags or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(chooseContactIntent)
    }
}
