package com.example.yaman.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yaman.data.Expense
import com.example.yaman.data.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BudgetViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    // Expose all expenses as StateFlow
    val expenses: StateFlow<List<Expense>> = repository.allExpenses
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Expose total spending
    val totalAmount: StateFlow<Double> = repository.allExpenses
        .map { expenses -> expenses.sumOf { it.amount } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    // Insert a new expense
    fun addExpense(description: String, amount: Double) {
        viewModelScope.launch {
            repository.insertExpense(
                Expense(description = description, amount = amount)
            )
        }
    }

    // Delete an expense
    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
    }
}