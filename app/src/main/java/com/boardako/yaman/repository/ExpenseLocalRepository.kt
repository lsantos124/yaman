package com.boardako.yaman.repository

import com.boardako.yaman.data.expenses.Expense
import com.boardako.yaman.data.expenses.ExpenseDao
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