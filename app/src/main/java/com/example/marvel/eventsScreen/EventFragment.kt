package com.example.marvel.eventsScreen


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.marvel.R
import com.example.marvel.databinding.EventFragmentBinding
import com.example.marvel.util.ProductGridItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var binding : EventFragmentBinding
    private lateinit var adapter: EventAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val viewModel: EventViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.event_fragment,container,false)
        binding.viewModel = viewModel
        swipeRefreshLayout = binding.swipeLayout
        adapter = EventAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL,false)
        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small)
        binding.recyclerView.addItemDecoration(
            ProductGridItemDecoration(
                largePadding,
                smallPadding
            )
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.eventsList.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })

        viewModel.exception.observe(viewLifecycleOwner, Observer {
            it?.let {e->
                val builder = AlertDialog.Builder(activity)
                builder.setMessage(e.message)
                builder.setCancelable(true)
                builder.setPositiveButton("Ok") { dialog: DialogInterface?, _: Int ->
                    dialog?.cancel()
                    viewModel.finishedDialog()
                }
                val alert = builder.create()
                alert.show()
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.refreshing.observe(viewLifecycleOwner, Observer {
            it?.let {isRefreshing->
                swipeRefreshLayout.isRefreshing = isRefreshing
                viewModel.doneRefreshing()
            }
        })
    }

}