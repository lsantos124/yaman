package com.boardako.yaman.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.boardako.yaman.data.categories.Category
import com.boardako.yaman.data.categories.CategoryDao
import com.boardako.yaman.data.expenses.Expense
import com.boardako.yaman.data.expenses.ExpenseDao

@Database(entities = [Expense::class, Category::class], version = 1)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
}