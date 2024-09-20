package com.example.myapplication.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.common.NewsTextButton
import com.example.myapplication.presentation.onboarding.Dimens.MediumPadding2
import com.example.myapplication.presentation.onboarding.Dimens.PageIndicatorWidth
import com.example.myapplication.presentation.onboarding.components.OnBoardingPage
import com.example.myapplication.presentation.onboarding.components.PageIndicator
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(0.dp,0.dp,0.dp,30.dp)
        ) {
        val pagerState = rememberPagerState(initialPage = 0)
        {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf{
                when(pagerState.currentPage){
                    0,1,2,3-> listOf("Tiếp Tục")
                    4-> listOf("Bắt Đầu")
                    else -> listOf("")
                }
            }
        }
        TextButton(
            modifier = Modifier.align(Alignment.End).padding(20.dp),
            onClick = {
                navController.navigate("Login")
            }
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Skip",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalPager(state = pagerState) {
                index -> OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            PageIndicator(
                modifier = Modifier
                    .width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage,
                selectedColor = Color("#509f6b".toColorInt()),
                unselectedColor = Color("#c1e5bb".toColorInt())
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        val scope = rememberCoroutineScope()
        if(buttonState.value[0].isNotEmpty()){
            NewsTextButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = MediumPadding2),
                text = buttonState.value[0],
                onClick = {
                    scope.launch {
                        if(pagerState.currentPage ==4){
                            navController.navigate("Login")
                        }else{
                            pagerState.animateScrollToPage(page = pagerState.currentPage +1)

                        }
                    }
                }
            )



    }
//        NewsTextButton(
//            modifier = Modifier.fillMaxWidth(),
//            text = buttonState.value[0],
//            onClick = {
//
//            }
//        )
}}
@Preview(showBackground = true)
@Composable
fun OnBoardingSceenPreview(){
    MyApplicationTheme(){
        val navController = rememberNavController()
        OnBoardingScreen(navController,)
    }
}
