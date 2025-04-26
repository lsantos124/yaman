package com.boardako.yaman.repository

import com.boardako.yaman.data.categories.Category
import com.boardako.yaman.data.categories.CategoryDao
import kotlinx.coroutines.flow.Flow

class CategoryLocalRepository(
    private val categoryDao: CategoryDao
) {
    val allCategories: Flow<List<Category>> = categoryDao.getAllCategories()

    suspend fun insertCategory(category: Category): Long {
        return categoryDao.insertCategory(category)
    }

    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }

    suspend fun updateCategoryName(category: Category, newName: String) {
        categoryDao.updateCategoryName(category.localId, newName)
    }

    suspend fun updateCategoryFirebaseId(categoryId: Int, firebaseId: String) {
        categoryDao.updateCategoryFirebaseID(categoryId, firebaseId)
    }
}