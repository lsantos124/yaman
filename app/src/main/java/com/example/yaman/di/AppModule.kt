package com.example.yaman.di

import android.app.Application
import androidx.room.Room
import com.example.yaman.data.ExpenseDatabase
import com.example.yaman.data.expenses.ExpenseDao
import com.example.yaman.data.expenses.ExpenseRepository
import com.example.yaman.data.categories.CategoryDao
import com.example.yaman.data.categories.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ExpenseDatabase {
        return Room.databaseBuilder(
            app,
            ExpenseDatabase::class.java,
            "expense_database"
        ).build()
    }

    @Provides
    fun provideExpenseDao(db: ExpenseDatabase): ExpenseDao {
        return db.expenseDao()
    }

    @Provides
    fun provideCategoryDao(db: ExpenseDatabase): CategoryDao {
        return db.categoryDao()
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(expenseDao: ExpenseDao): ExpenseRepository {
        return ExpenseRepository(expenseDao)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return CategoryRepository(categoryDao)
    }
}