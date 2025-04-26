package com.boardako.yaman.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boardako.yaman.data.categories.Category
import com.boardako.yaman.repository.CategoryLocalRepository
import com.boardako.yaman.repository.CategoryNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val localRepository: CategoryLocalRepository,
    private val networkRepository: CategoryNetworkRepository
) : ViewModel() {

    val categories: StateFlow<List<Category>> = localRepository.allCategories
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun refreshCategoriesFromCloud() {
        viewModelScope.launch {
            val cloudCategories = networkRepository.getCategories()
            val localCategoryNames = categories.value.map { it.name }.toSet()

            cloudCategories.forEach { category ->
                if (category.name !in localCategoryNames) {
                    localRepository.insertCategory(category)
                }
            }
        }
    }

    fun addCategory(name: String) {
        viewModelScope.launch {
            val newCategory = Category(name = name)

            // Insert locally first
            val localId = localRepository.insertCategory(newCategory)

            try {
                // Push to Firebase
                val firebaseId = networkRepository.addCategory(newCategory)

                // If successful, update the local entry to have the firebaseId
                if (firebaseId != null) {
                    localRepository.updateCategoryFirebaseId(localId.toInt(), firebaseId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // TODO: Handle sync failure (retry later?)
            }
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            localRepository.deleteCategory(category)
            category.firebaseId?.let { networkRepository.deleteCategory(it) }
        }
    }

    fun renameCategory(category: Category, newName: String) {
        viewModelScope.launch {
            localRepository.updateCategoryName(category, newName)

            val newCategory = Category(localId = category.localId, firebaseId = category.firebaseId, name = newName)
            category.firebaseId?.let { networkRepository.updateCategory(category.firebaseId, newCategory) }
        }
    }
}