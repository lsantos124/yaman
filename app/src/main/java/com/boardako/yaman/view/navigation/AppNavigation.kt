package com.boardako.yaman.view.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.boardako.yaman.view.categories.ManageCategoriesScreen
import com.boardako.yaman.view.expenses.ExpensesList

@Composable
fun AppNavigation(
    navController: NavHostController,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Screen.Home.route) {
            // TODO
        }
        composable(Screen.Expenses.route) {
            ExpensesList(isDarkMode = isDarkMode, onToggleTheme = onToggleTheme)
        }
        composable(Screen.Categories.route) {
            ManageCategoriesScreen()
        }
        composable(Screen.Budgets.route) {
            // TODO
        }
        composable(Screen.Assets.route) {
            // TODO
        }
        composable(Screen.NetWorth.route) {
            // TODO
        }
        composable(Screen.Chatbot.route) {
            // TODO
        }
        composable(Screen.Graphs.route) {
            // TODO
        }
    }
}
