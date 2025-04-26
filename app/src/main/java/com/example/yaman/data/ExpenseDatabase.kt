package com.example.yaman.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yaman.data.categories.Category
import com.example.yaman.data.categories.CategoryDao
import com.example.yaman.data.expenses.Expense
import com.example.yaman.data.expenses.ExpenseDao

@Database(entities = [Expense::class, Category::class], version = 1)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
}