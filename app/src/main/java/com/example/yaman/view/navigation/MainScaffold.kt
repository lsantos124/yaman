package com.example.yaman.view.navigation

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var navigationTarget by remember { mutableStateOf<String?>(null) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text(Screen.Home.title) },
                    selected = false,
                    onClick = { navigationTarget = Screen.Home.route }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Expenses.title) },
                    selected = false,
                    onClick = { navigationTarget = Screen.Expenses.route }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Categories.title) },
                    selected = false,
                    onClick = { navigationTarget = Screen.Categories.route }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Budgets.title) },
                    selected = false,
                    onClick = { navigationTarget = Screen.Budgets.route }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Assets.title) },
                    selected = false,
                    onClick = { navigationTarget = Screen.Assets.route }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.NetWorth.title) },
                    selected = false,
                    onClick = { navigationTarget = Screen.NetWorth.route }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Chatbot.title) },
                    selected = false,
                    onClick = { navigationTarget = Screen.Chatbot.route }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Graphs.title) },
                    selected = false,
                    onClick = { navigationTarget = Screen.Graphs.route }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Yaman App") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            AppNavigation(
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleTheme = onToggleTheme,
                innerPadding = innerPadding
            )
        }

        LaunchedEffect(navigationTarget) {
            navigationTarget?.let { target ->
                navController.navigate(target) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                drawerState.close()
                navigationTarget = null // Reset after navigation
            }
        }
    }
}