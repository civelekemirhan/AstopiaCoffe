package com.example.astopiacoffe.feature.ui.mainflow.screen.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.astopiacoffe.R
import com.example.astopiacoffe.feature.component.CoffeItem
import com.example.astopiacoffe.feature.component.CustomSearchBar
import com.example.astopiacoffe.feature.model.PassArgument
import com.example.astopiacoffe.feature.ui.mainflow.screen.MainViewModel
import com.example.astopiacoffe.network.ConnectivityObserver
import com.example.astopiacoffe.network.NetworkStatus
import com.example.astopiacoffe.ui.theme.appBackground
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, onNavigateToDetail: () -> Unit) {

    val systemUiController = rememberSystemUiController()
    val theme = isSystemInDarkTheme()

    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.appBackground,
        darkIcons = !theme
    )
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.appBackground,
        darkIcons = !theme
    )


    val scope = rememberCoroutineScope()
    val coffeeList by viewModel.filteredCoffeeList.collectAsState()
    val searchBarState by viewModel.searchBarText.collectAsState()
    val context = LocalContext.current
    var isProgrressVisible by remember {
        mutableStateOf(true)
    }
    val connectivityObserver = remember { ConnectivityObserver(context) }
    val networkStatus by connectivityObserver.networkStatus.collectAsState(initial = NetworkStatus.Unavailable)

    val isInternetAvailable = networkStatus == NetworkStatus.Available
    var isErrorMessageVisible by remember {
        mutableStateOf(false)
    }
    var isSearchBarVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(5000)
        if(coffeeList.isEmpty()){
            isErrorMessageVisible = true
        }else{
            isErrorMessageVisible = false
        }
    }

    LaunchedEffect(isInternetAvailable ) {
        if (isInternetAvailable) {
            scope.launch {
                viewModel.loadData()
                isProgrressVisible = false
            }
        }else{
            isProgrressVisible = true
            delay(1000)
            isProgrressVisible = false
        }
    }

    val dummyData = listOf(null, null, null, null)


    Scaffold(
        containerColor = MaterialTheme.colorScheme.appBackground,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(start = 10.dp),
                title = {

                    Text(
                        stringResource(R.string.app_name).uppercase(),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    )

                },
                actions = {

                    IconButton(
                        onClick = {
                            isSearchBarVisible = !isSearchBarVisible
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }


                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.appBackground,
                )

            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.appBackground)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isInternetAvailable && coffeeList.isEmpty()) {
                if(isProgrressVisible){
                    CircularProgressIndicator()
                }else{
                    Text(text = "İnternet Bağlantınızı Kontrol Edin")
                }
            } else {
                AnimatedVisibility(visible = isSearchBarVisible) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CustomSearchBar(
                            value = searchBarState,
                            onValueChange = {
                                viewModel.onEvent(MainEvent.SetSearchBarText(it))
                            })
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                        LazyColumn {
                            if (coffeeList.isEmpty()) {
                                if (searchBarState.isNotEmpty()) {
                                    item {
                                        Text(text = "Ürünler Bulunamadı")
                                    }
                                }else if(searchBarState.isEmpty() && isErrorMessageVisible) {
                                    item {
                                        Text(text = "Bir Hata Oluştu , İşlem çok uzun sürdü")
                                    }
                                } else {
                                    items(dummyData) {
                                        CoffeItem(item = it)
                                        Spacer(modifier = Modifier.height(20.dp))
                                    }
                                }
                            } else {
                                items(coffeeList, key = { it!!.id }) {
                                    CoffeItem(item = it) {
                                        val passArgument = PassArgument(
                                            it!!.title,
                                            it.image,
                                            it.description,
                                            it.ingredients
                                        )
                                        viewModel.onEvent(MainEvent.SetPassArgument(passArgument))
                                        onNavigateToDetail()
                                    }
                                    Spacer(modifier = Modifier.height(20.dp))
                                }
                            }

                        }


                }
            }


        }
    }


}


