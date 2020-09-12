package com.curso.intermedia_kotlin.database
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultipleProduct(product: List<Product>)

    @Update
    suspend fun updateOneProduct(product: Product)

    @Delete
    fun deleteOneProduct(product: Product)

    @Query("SELECT * FROM products")
    fun getAllTaskFromDb(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE id =:id")
    fun getOneTaskByID(id: Int): LiveData<Product>

    @Query("DELETE  FROM products")
    suspend fun deleteAllTask()


}
