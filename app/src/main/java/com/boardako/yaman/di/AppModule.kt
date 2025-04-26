package com.boardako.yaman.di

import android.app.Application
import androidx.room.Room
import com.boardako.yaman.data.ExpenseDatabase
import com.boardako.yaman.data.expenses.ExpenseDao
import com.boardako.yaman.repository.ExpenseLocalRepository
import com.boardako.yaman.data.categories.CategoryDao
import com.boardako.yaman.repository.CategoryLocalRepository
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
    fun provideExpenseRepository(expenseDao: ExpenseDao): ExpenseLocalRepository {
        return ExpenseLocalRepository(expenseDao)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryLocalRepository {
        return CategoryLocalRepository(categoryDao)
    }
}