package ru.bpproject.camestore.fragments.productItem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import ru.bpproject.camestore.CartActions
import ru.bpproject.camestore.R
import ru.bpproject.camestore.data.localdb.entity.ProductEntity
import ru.bpproject.camestore.di.Injectable
import ru.bpproject.camestore.fragments.auxiliaryProductList.AuxiliaryProductListAdapter
import ru.bpproject.camestore.fragments.general.StringListAdapter
import ru.bpproject.camestore.util.*
import javax.inject.Inject

class ProductItemFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args by navArgs<ProductItemFragmentArgs>()

    private val viewModel: ProductItemViewModel by viewModels { viewModelFactory }

    private var cartActions : CartActions? = null

    private lateinit var ivImage : ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var price: TextView
    private lateinit var oldPrice: TextView
    private lateinit var btnAddCart: ExtendedFloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initRecyclers(view)
        viewModel.product.observe(viewLifecycleOwner, this::updateProductInfo)
        viewModel.loadProduct(args.id)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CartActions) {
            cartActions = context
        }
    }

    override fun onDetach() {
        cartActions = null
        super.onDetach()
    }

    private fun initViews(view: View) {
        ivImage = view.findViewById(R.id.iv_image_product)
        toolbar = view.findViewById(R.id.tb_product_item)
        price = view.findViewById(R.id.tv_price_product_item)
        oldPrice = view.findViewById(R.id.tv_old_price)
        btnAddCart = view.findViewById(R.id.btn_add_cart_product_item)
    }

    private fun initRecyclers(view: View) {
        val recyclerFunc = view.findViewById<RecyclerView>(R.id.rv_functionality)
        val adapterFunc = StringListAdapter()
        recyclerFunc.apply {
            adapter = adapterFunc
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.functionality.observe(viewLifecycleOwner) { funcs ->
            adapterFunc.submitList(funcs as MutableList<String>)
        }
        val recyclerAccessories = view.findViewById<RecyclerView>(R.id.rv_accessories)
        val adapterAccessories = AuxiliaryProductListAdapter(cartActions)
        recyclerAccessories.apply {
            adapter = adapterAccessories
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        viewModel.accessories.observe(viewLifecycleOwner) { accessories ->
            adapterAccessories.submitList(accessories as MutableList<ProductEntity>)
        }
    }

    private fun updateProductInfo(product: ProductEntity) {

        toolbar.title = product.title
        price.text = getCurrencyFormat().format(product.price)
        product.oldPrice?.let { value ->
            oldPrice.visibility = View.VISIBLE
            oldPrice.text = getCurrencyFormat().format(value)
        } ?: run { oldPrice.visibility = View.GONE }

        Glide.with(this)
            .load(product.image)
            .fitCenter()
            .placeholder(getCircularProgress(requireContext()))
            .into(ivImage)

        btnAddCart.setOnClickListener {
            cartActions?.addToCart(product.id)
        }
    }
}