package com.boardako.yaman.view.expenses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.boardako.yaman.viewmodel.CategoryViewModel
import com.boardako.yaman.viewmodel.ExpenseViewModel

@Composable
fun ExpensesList(
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    val expenseViewModel: ExpenseViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()

    val expenses by expenseViewModel.expenses.collectAsState()
    val totalAmount by expenseViewModel.totalAmount.collectAsState()

    val categories by categoryViewModel.categories.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = if (isDarkMode) "Dark Mode" else "Light Mode")
            Switch(
                checked = isDarkMode,
                onCheckedChange = { onToggleTheme() }
            )
        }

        Text(
            text = "Total Spent: \$${"%.2f".format(totalAmount)}",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExpensesForm(
            categories = categories,
            onAddExpense = { description, amount, categoryId ->
            expenseViewModel.addExpense(description, amount, categoryId)
        })

        Spacer(modifier = Modifier.height(24.dp))

        // List of expenses
        LazyColumn {
            items(expenses) { expense ->
                val categoryMap by expenseViewModel.categoryMap.collectAsState()
                val categoryName = categoryMap[expense.categoryId] ?: "Uncategorized"

                ExpenseItem(
                    expense = expense,
                    categoryName = categoryName,
                    onDelete = { expenseViewModel.deleteExpense(expense) }
                )
            }
        }
    }
}

