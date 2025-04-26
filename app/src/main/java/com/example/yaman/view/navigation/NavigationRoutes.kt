package com.example.yaman.view.navigation

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Overview")
    object Expenses : Screen("expenses", "Expenses")
    object Categories : Screen("categories", "Categories")
    object Budgets : Screen("budgets", "Budgets")
    object Assets : Screen("assets", "Assets")
    object NetWorth : Screen("net_worth", "Net Worth")
    object Chatbot : Screen("chatbot", "Chatbot")
    object Graphs : Screen("graphs", "Graphs")
}