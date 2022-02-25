package com.example.composetopnav

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.composetopnav.ui.theme.ComposeTopNavTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTopNavTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyTab()
//                    MySwipingTab()
                }
            }
        }
    }
}

@Composable
fun MyTab(){
    var tabIndex by remember { mutableStateOf(0) } // 1.
    val tabTitles = listOf("Open Requests", "Request Status")

    //From top to bottom
    Column {
        //Tab Layout first
        TabRow(
            selectedTabIndex = tabIndex,
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text( text = title)}
                )
            }
        }

        when(tabIndex){
            0 -> OpenRequestFragment()
            1 -> RequestStatusFragment()
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MySwipingTab() {
    val tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Open Requests", "Request Status")
    val pagerState = rememberPagerState() // 2.
    val scope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = {tabPositions ->
                TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset (pagerState, tabPositions), height = 5.dp, color = Color.Red)
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) }  },
                    text = { Text( text = title, color = Color.Red)},
                )
            }
        }
        HorizontalPager(
            count = tabTitles.size,
            state = pagerState
        ) { tabIndex ->

            when(tabIndex){
                0 -> OpenRequestFragment()
                1 -> RequestStatusFragment()
            }
        }
    }
}

@Composable
private fun OpenRequestFragment() {
    Surface(modifier = Modifier.padding(16.dp,8.dp)) {
        Column {
            MySearchView()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Hello open request fragment")
        }
        
    }
}

@Composable
fun MySearchView(
    onSearch : (String) -> Unit = {}
){
    var text by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxWidth()){
        OutlinedTextField(value = text, onValueChange ={
            text = it
            //trigger your onSearch function with the new string
            onSearch(it)
        },
            placeholder = {
                Text(text = "Search Requests")
            },
            label = {
                Text(text = "Search Requests")
            },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Search, contentDescription = "Search" )
                    
                }
            },
            keyboardActions = KeyboardActions(
                onSearch = { Log.d("SEARCH_CLICK", "Search icon on keyboard clicked")}
            )
        )
    }
}

@Composable
private fun RequestStatusFragment() {
    Column {
        Text(text = "Hello request status fragment")
    }
}
