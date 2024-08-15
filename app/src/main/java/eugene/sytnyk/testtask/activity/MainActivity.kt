package eugene.sytnyk.testtask.activity

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import eugene.sytnyk.testtask.R
import eugene.sytnyk.testtask.actionhelper.ActionHelper
import eugene.sytnyk.testtask.model.ActionUI
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainButton = findViewById(R.id.btnMain)
        mainButton.setOnClickListener { viewModel.onButtonClick() }
        subscribeToActionEvents()
    }

    private fun subscribeToActionEvents() {
        lifecycleScope.launch {
            viewModel.actionEvents.flowWithLifecycle(
                lifecycle = this@MainActivity.lifecycle
            ).collect { action ->
                when (action) {
                    is ActionUI.Animation -> performAnimation()
                    is ActionUI.Call -> ActionHelper.openChooseContact(this@MainActivity, action)
                    is ActionUI.Notification -> ActionHelper.createNotification(this@MainActivity, action)
                    is ActionUI.ToastMessage -> ActionHelper.showToast(this@MainActivity, action)
                }
            }
        }
    }

    private fun performAnimation() {
        val rotate = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 1000
        }
        mainButton.startAnimation(rotate)
    }
}