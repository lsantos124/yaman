package com.example.yaman.view.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
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

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text(Screen.Home.title) },
                    selected = false,
                    onClick = { navController.navigate(Screen.Home.route) }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Expenses.title) },
                    selected = false,
                    onClick = { navController.navigate(Screen.Expenses.route) }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Categories.title) },
                    selected = false,
                    onClick = { navController.navigate(Screen.Categories.route) }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Budgets.title) },
                    selected = false,
                    onClick = { navController.navigate(Screen.Budgets.route) }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Assets.title) },
                    selected = false,
                    onClick = { navController.navigate(Screen.Assets.route) }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.NetWorth.title) },
                    selected = false,
                    onClick = { navController.navigate(Screen.NetWorth.route) }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Chatbot.title) },
                    selected = false,
                    onClick = { navController.navigate(Screen.Chatbot.route) }
                )
                NavigationDrawerItem(
                    label = { Text(Screen.Graphs.title) },
                    selected = false,
                    onClick = { navController.navigate(Screen.Graphs.route) }
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
    }
}