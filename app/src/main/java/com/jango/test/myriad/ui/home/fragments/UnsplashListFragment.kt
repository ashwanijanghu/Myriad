package com.jango.test.myriad.ui.home.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jango.test.myriad.R
import com.jango.test.myriad.common.extensions.onScrollBoundary
import com.jango.test.myriad.ui.home.adapter.GridSpacingItemDecoration
import com.jango.test.myriad.ui.home.adapter.UnsplashItemAdapter
import com.jango.test.myriad.ui.home.viewmodels.UnsplashViewModel
import kotlinx.android.synthetic.main.fragment_unsplash_item_list.*


/**
 * A fragment representing a list of Items.
 */
class UnsplashListFragment : Fragment() {

    private lateinit var unsplashViewModel: UnsplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        unsplashViewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_unsplash_item_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeViewModel()
        loadData(1)
    }

    private fun loadData(page:Int) {
        unsplashViewModel.pageIndex.postValue(page)
    }

    private fun loadMoreData(page: Int){
        loadMore.visibility = View.VISIBLE
        loadData(page)
    }

    private fun observeViewModel() {
        unsplashViewModel.unsplashItems.observe(this, Observer {
            it?.let { it1 ->
                if(loadMore.visibility == View.VISIBLE){
                    loadMore.visibility = View.GONE
                }
                unsplashViewModel.unsplashItemList.addAll(it1)
                (unsplashList.adapter as UnsplashItemAdapter).updateData(it1)
            }

        })
    }

    private fun initAdapter() {
        val gridLayoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        unsplashList.layoutManager =  gridLayoutManager
        unsplashList.addItemDecoration(GridSpacingItemDecoration(2,
            resources.getDimensionPixelSize(R.dimen.dp16), false))
        val adapter = UnsplashItemAdapter(context!!, mutableListOf())
        unsplashList.adapter = adapter
        unsplashList.onScrollBoundary(::loadMoreData,gridLayoutManager)
    }

    private fun getViewModel(): UnsplashViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return UnsplashViewModel() as T
            }
        })[UnsplashViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UnsplashListFragment().apply {
            }
    }
}