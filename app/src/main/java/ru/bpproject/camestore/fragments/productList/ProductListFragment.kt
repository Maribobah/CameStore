package ru.bpproject.camestore.fragments.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bpproject.camestore.R
import ru.bpproject.camestore.data.localdb.entity.ProductEntity
import ru.bpproject.camestore.di.Injectable
import javax.inject.Inject

class ProductListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args by navArgs<ProductListFragmentArgs>()

    private val viewModel: ProductListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
        initToolbar(view)
        viewModel.getProducts(args.id)
    }

    private fun initRecycler(view: View) {
        val recycler = view.findViewById<RecyclerView>(R.id.rv_products)
        val adapter = ProductListAdapter()
        recycler.adapter = adapter
        val gridSize = resources.getInteger(R.integer.products_grid_size)
        recycler.layoutManager = GridLayoutManager(requireContext(), gridSize)
        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(
            ProductSpaceItemDecoration(
                resources.getDimensionPixelSize(R.dimen.product_card_padding),
                gridSize
            )
        )
        viewModel.products.observe(viewLifecycleOwner) { products ->
            products?.let { adapter.submitList(it as MutableList<ProductEntity>) }
        }
        viewModel.categoryName.observe(viewLifecycleOwner, this::updateToolbar)
    }

    private fun initToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.tb_product_list)
        toolbar.setNavigationOnClickListener {
            val action = ProductListFragmentDirections.actionNavProductListToNavCatalog()
            view.findNavController().navigate(action)
        }
    }

    private fun updateToolbar(title: String) {
        view?.let {
            val toolbar = it.findViewById<Toolbar>(R.id.tb_product_list)
            toolbar.title = title
        }
    }
}