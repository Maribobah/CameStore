package ru.bpproject.camestore.fragments.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bpproject.camestore.R
import ru.bpproject.camestore.data.localdb.entity.CategoryEntity
import ru.bpproject.camestore.di.Injectable
import ru.bpproject.camestore.vo.Resource
import ru.bpproject.camestore.vo.Status
import javax.inject.Inject

class CatalogFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CatalogViewModel by viewModels { viewModelFactory }

    private lateinit var layoutLoadingState : LinearLayout
    private lateinit var adapter: CatalogAdapter
    private lateinit var pbLoadingState: ProgressBar
    private lateinit var tvErrorMessage: TextView
    private lateinit var btnRetry : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog_main_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initRecycler(view)
    }

    private fun initViews(view: View) {
        layoutLoadingState = view.findViewById(R.id.ll_loading_state)
        pbLoadingState = view.findViewById(R.id.pb_loading_state)
        tvErrorMessage = view.findViewById(R.id.tv_error_message)
        btnRetry = view.findViewById(R.id.btn_retry)
        btnRetry.setOnClickListener { viewModel.retry() }
    }

    private fun initRecycler(view: View) {
        val recycler = view.findViewById<RecyclerView>(R.id.rv_catalog)
        adapter = CatalogAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycler.setHasFixedSize(true)
        viewModel.categories.observe(viewLifecycleOwner, this::updateViews)
    }

    private fun updateViews(resource: Resource<List<CategoryEntity>>) {
        if (resource.data != null) {
            layoutLoadingState.isVisible = false
            adapter.submitList(resource.data)

        } else {
            adapter.submitList(emptyList())
            layoutLoadingState.isVisible = true
            pbLoadingState.isVisible = (resource.status == Status.LOADING)
            tvErrorMessage.isVisible = (resource.status == Status.ERROR)
            tvErrorMessage.text = resource.message ?: resources.getString(R.string.unknown_error)
            btnRetry.isVisible = (resource.status == Status.ERROR)
        }
    }
}