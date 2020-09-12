package com.curso.intermedia_kotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.curso.intermedia_kotlin.database.Product
import com.curso.intermedia_kotlin.database.ProductDataBase
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository
     val allProduct: LiveData<List<Product>>

    init {
        val productDao = ProductDataBase.getDatabase(application).getProductDao()
        repository = ProductRepository(productDao)
        allProduct = repository.listAllProduct
    }

    fun insertProduct(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    fun deleteAllProduct() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getOneProductByID(id: Int): LiveData<Product> {
        return repository.getOneProductByID(id)
    }

    fun updateProduct(product: Product) = viewModelScope.launch {
        repository.updateProduct(product)
    }

}