package com.example.viewmodelsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.viewmodelsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /* by viewModels를 써야 구성 변경시에도 참조를 잃지 않을 수 있다. */
    private val viewmodel: MyViewModel by viewModels { LiveDataVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Data Binding
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )

        // Set the LifecycleOwner to be able to observe LiveData objects
        binding.lifecycleOwner = this

        // Bind ViewModel
        // data binding 없었으면 Observer 선언해서 사용해야했음
        binding.viewmodel = viewmodel

        binding.editText1.setOnKeyListener { v, keyCode, event ->
            when (keyCode) {
                KeyEvent.KEYCODE_ENTER -> {
                    viewmodel.setTitle(binding.editText1.text.toString())
                    true
                }
                else -> false
            }
        }
    }
}
