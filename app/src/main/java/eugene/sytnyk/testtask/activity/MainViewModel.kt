package eugene.sytnyk.testtask.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eugene.sytnyk.testtask.repository.ActionDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val actionDataRepository: ActionDataRepository
) : ViewModel() {

    fun test() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("MYOWNTAG", actionDataRepository.getActionData().toString())
        }
    }
}
