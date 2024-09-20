package com.example.myapplication.presentation.onboarding.components
import android.media.Image
import android.media.TimedText
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.presentation.onboarding.Dimens.MediumPadding1
import com.example.myapplication.presentation.onboarding.Dimens.MediumPadding2
import com.example.myapplication.presentation.onboarding.Page
import com.example.myapplication.presentation.onboarding.pages
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
){
    Column(modifier = modifier){

        Image(modifier= Modifier
            .fillMaxWidth().
            fillMaxHeight(0.6f),
            painter = painterResource(id = page.img),
            contentDescription= null,
            contentScale =  ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(text = page.title,
            modifier.padding(horizontal = MediumPadding2).fillMaxWidth(),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        Text(text = page.description, modifier.padding(horizontal = MediumPadding2).fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center)

    }
}
@Composable
private fun TextBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OnBoardingPagePreview(){
    MyApplicationTheme(){
        OnBoardingPage(page = pages[0])
    }
}
