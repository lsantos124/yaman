package com.example.yaman.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yaman.repository.CategoryLocalRepository
import com.example.yaman.data.expenses.Expense
import com.example.yaman.repository.ExpenseLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseLocalRepository: ExpenseLocalRepository,
    private val categoryRepository: CategoryLocalRepository
) : ViewModel() {

    // Expose all expenses as StateFlow
    val expenses: StateFlow<List<Expense>> = expenseLocalRepository.allExpenses
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Expose total spending
    val totalAmount: StateFlow<Double> = expenseLocalRepository.allExpenses
        .map { expenses -> expenses.sumOf { it.amount } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    val categoryMap: StateFlow<Map<Int, String>> = categoryRepository.allCategories
        .map { categories ->
            categories.associateBy({ it.localId }, { it.name })
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyMap()
        )

    // Insert a new expense
    fun addExpense(description: String, amount: Double, categoryId: Int?) {
        viewModelScope.launch {
            expenseLocalRepository.insertExpense(
                Expense(
                    description = description,
                    amount = amount,
                    categoryId = categoryId
                )
            )
        }
    }

    // Delete an expense
    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expenseLocalRepository.deleteExpense(expense)
        }
    }
}