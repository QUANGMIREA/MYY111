package com.example.myapplication.addProfile


import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme


@Composable
fun CameraScreen(isPreview: Boolean = false,navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current


    Scaffold(

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f),  // Chiếm 60% chiều cao màn hình
                    contentAlignment = Alignment.Center
                ) {
                    if (!isPreview) {
                        CameraPreviewView(context = context, lifecycleOwner = lifecycleOwner)
                    } else {
                        Text("Camera Preview (in Preview mode)")
                    }
                }
                Text("* Use the camera to scan the body with the included scanner / QR code scanner",
                    modifier = Modifier.padding(16.dp,0.dp).fillMaxWidth(),
                    color = Color(0xFF469E67),
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp
                    )
                Spacer(modifier = Modifier.height(16.dp))
                BottomCameraControls()


            }

        }

    )

}
@Composable
fun BottomCameraControls() {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Nút đầu
            IconButton(onClick = {

            }) {
                Image(
                    modifier = Modifier.size(70.dp).padding(12.dp),
                    painter = painterResource(id = R.drawable.gallery),
                    contentDescription = "Capture Button",
                    contentScale = ContentScale.Fit
                )
            }

            // Nút chụp (giữa)
            IconButton(onClick = {

            }) {
                Box(
                    modifier = Modifier
                        .size(70.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icontakephoto),
                            contentDescription = "Capture Button",
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            // Nut cuoois
            IconButton(onClick = {

            }) {
                Image(
                    modifier = Modifier.size(70.dp).padding(16.dp),
                    painter = painterResource(id = R.drawable.flash),
                    contentDescription = "Capture Button",
                    contentScale = ContentScale.Fit
                )
            }
        }


}

@Composable
fun CameraPreviewView(context: Context, lifecycleOwner: LifecycleOwner) {
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val previewView = remember { PreviewView(context) }

    DisposableEffect(Unit) {
        val executor = ContextCompat.getMainExecutor(context)

        cameraProviderFuture.addListener({
            try {
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, preview
                )
            } catch (exc: Exception) {
                Log.e("CameraX", "Use case binding failed", exc)
            }
        }, executor)

        onDispose {
            val cameraProvider = cameraProviderFuture.get()
            cameraProvider.unbindAll()
        }
    }

    // Hiển thị PreviewView
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentAlignment = Alignment.Center
    ) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun ViewAddProfilePreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        CameraScreen(isPreview = true, navController = navController)
    }
}
