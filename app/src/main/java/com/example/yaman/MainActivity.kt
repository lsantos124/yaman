package com.example.yaman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.yaman.data.ExpenseDatabase
import com.example.yaman.data.ExpenseRepository
import com.example.yaman.ui.theme.Typography
import com.example.yaman.ui.theme.YamanTheme
import com.example.yaman.view.BudgetScreen
import com.example.yaman.viewmodel.BudgetViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            ExpenseDatabase::class.java,
            "expense_database"
        ).build()

        val repository = ExpenseRepository(db.expenseDao())

        setContent {
            var isDarkMode by remember { mutableStateOf(false) }

            val colorScheme = if (isDarkMode) {
                darkColorScheme()
            } else {
                lightColorScheme()
            }

            MaterialTheme(
                colorScheme = colorScheme,
                typography = Typography
            ) {
                val viewModel = BudgetViewModel(repository)
                BudgetScreen(viewModel, isDarkMode = isDarkMode, onToggleTheme = { isDarkMode = !isDarkMode })
            }
        }
    }
}