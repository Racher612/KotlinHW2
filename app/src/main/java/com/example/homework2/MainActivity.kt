package com.example.homework2

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.homework2.ui.theme.HomeWork2Theme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.load()
        setContent {
            val isLoading by viewModel.isLoading.collectAsState()
            val content by viewModel.ExternalUrls
            HomeWork2Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    when {
//                        isLoading -> LoadingUi()
                        else -> ListOfObjects(content)
                    }
                    Log.d("Main", "here")
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("Touch", viewModel.ExternalUrls.toString())
        viewModel.load()
        return super.onTouchEvent(event)
    }
}