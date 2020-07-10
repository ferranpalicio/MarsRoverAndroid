package com.pal.marsroverandroid.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.lifecycle.Observer
import com.pal.marsroverandroid.R
import com.pal.marsroverandroid.di.injector
import com.pal.marsroverandroid.viewmodel.AppViewModel
import com.pal.marsroverandroid.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModelFactory by lazy { ViewModelFactory(injector.dataReader()) }
    private val viewModel: AppViewModel by viewModels { viewModelFactory }

    private val buttonStart: Button by bind(R.id.buttonStart)
    private val tvResult: TextView by bind(R.id.tvResult)

    private val stringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.result.observe(this, Observer { position ->
            stringBuilder.append("\n$position")
            tvResult.text = stringBuilder.toString()

        })

        val file = "rover_instructions.json"
        viewModel.readFile(file)
        buttonStart.setOnClickListener {
            buttonStart.isEnabled = false
            viewModel.moveVehicle()
        }


    }

    private fun <T : View> Activity.bind(@IdRes res: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
    }
}
