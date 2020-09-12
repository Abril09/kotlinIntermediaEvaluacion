package com.curso.intermedia_kotlin

import androidx.lifecycle.LiveData
import com.curso.intermedia_kotlin.database.Product
import com.curso.intermedia_kotlin.database.ProductDao

class ProductRepository(private val productDao: ProductDao) {
    val listAllProduct: LiveData<List<Product>> = productDao.getAllTaskFromDb()

    suspend fun insertProduct(product: Product) {
        productDao.insertOneProduct(product = product)
    }

    suspend fun deleteAll() {
        productDao.deleteAllTask()
    }

    fun getOneProductByID(id: Int): LiveData<Product> {
        return productDao.getOneTaskByID(id)
    }

    suspend fun updateProduct(product: Product) {
        productDao.updateOneProduct(product)
    }
}