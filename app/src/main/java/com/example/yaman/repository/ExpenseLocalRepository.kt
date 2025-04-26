package com.example.yaman.repository

import com.example.yaman.data.expenses.Expense
import com.example.yaman.data.expenses.ExpenseDao
import kotlinx.coroutines.flow.Flow

class ExpenseLocalRepository(
    private val expenseDao: ExpenseDao
) {
    val allExpenses: Flow<List<Expense>> = expenseDao.getAllExpenses()

    suspend fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
        expenseDao.deleteExpense(expense)
    }
}