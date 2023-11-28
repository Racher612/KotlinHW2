package com.example.homework2

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.provider.Contacts
import android.provider.Contacts.Intents.UI
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toDrawable
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking


@Composable
fun PosView(url : String){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build(),
        contentDescription = "Post image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .aspectRatio(16f / 9f)
            .padding(20.dp)
    )
}

@Composable
fun ListClass(url : String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Log.d("from LISTCLASS", url)
        PosView(url)
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ListOfObjects(content : List<String>){
//    Log.d("Lazy", content[0])
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(content) { url ->
            ListClass(url = url)

        }
    }
}