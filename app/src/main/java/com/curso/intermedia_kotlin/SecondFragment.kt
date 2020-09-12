package com.curso.intermedia_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.curso.intermedia_kotlin.database.Product
import kotlinx.android.synthetic.main.fragment_second.*
import kotlin.reflect.typeOf

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var viewModel: ProductViewModel
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        arguments?.let {
            id = it.getInt("id")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id?.let {
            viewModel.getOneProductByID(it).observe(viewLifecycleOwner, Observer {
                et_nombre.setText(it.name)
                et_cantidad.setText(it.quantity.toString())
                et_precio.setText(it.unitPrice.toString())
            })
        }

        et_cantidad.doOnTextChanged{ charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            if(!charSequence.isNullOrBlank() && !et_precio.text.toString().isNullOrBlank())
            {  val numver =charSequence.toString().toInt() * et_precio.text.toString().toInt()
                textView.setText(numver.toString())}
        }

        et_precio.doOnTextChanged{ charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            if(!charSequence.isNullOrBlank() && !et_cantidad.text.toString().isNullOrBlank() )

            {   val number = charSequence.toString().toInt() * et_cantidad.text.toString().toInt()
                textView.setText(number.toString())
            }
         }

        button_guardar.setOnClickListener {
            val name = et_nombre.text.toString()
            val quantity = et_precio.text.toString()
            val price = et_precio.text.toString()
            val valid: Boolean =
                listOf<String>(name, quantity, price).any{ x -> x.isNullOrBlank()}

            if (!valid) {
                viewModel.insertProduct(
                    Product(
                        name = name,
                        unitPrice = price.toInt(),
                        quantity = quantity.toInt()

                    )
                )
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }

        }

        /*    view.findViewById<Button>(R.id.button_second).setOnClickListener {
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }*/
    }


}