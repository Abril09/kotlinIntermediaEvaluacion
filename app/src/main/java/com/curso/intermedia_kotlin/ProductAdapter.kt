package com.curso.intermedia_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.curso.intermedia_kotlin.database.Product
import kotlinx.android.synthetic.main.product_list.view.*

class ProductAdapter(var mPassTheData: PassTheData) : RecyclerView.Adapter<ProductAdapter.TaskViewHolder>(){
    private var dataList = emptyList<Product>()

    // funcion que va actualiza el listado del adapter.
    fun updateDataList(mDataList: List<Product>){
        dataList = mDataList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val productNameText = itemView.tV_nombre
        val productCantidad = itemView.tv_cantidad
        val itemView = itemView.setOnClickListener(this)

        override fun onClick(p0: View?) {
            mPassTheData.passTheData(dataList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val product: Product = dataList[position]
        holder.productNameText.text = "-${product.id} ${product.name}  total"
        holder.productCantidad.text = (product.quantity * product.unitPrice).toString()
    }

    override fun getItemCount() = dataList.size

    //Esta interface va a pasar el dato Al primerFragmento
    interface PassTheData{
        fun passTheData(product: Product)
    }
}
