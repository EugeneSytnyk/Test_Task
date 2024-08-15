package eugene.sytnyk.testtask.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eugene.sytnyk.testtask.model.ActionUI
import eugene.sytnyk.testtask.usecase.GetActionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getActionUseCase: GetActionUseCase
) : ViewModel() {

    private val _actionEvents: MutableSharedFlow<ActionUI> = MutableSharedFlow(extraBufferCapacity = 1)
    val actionEvents: SharedFlow<ActionUI> = _actionEvents.asSharedFlow()

    fun onButtonClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val action = getActionUseCase.getAction() ?: return@launch
            _actionEvents.emit(action)
        }
    }
}
