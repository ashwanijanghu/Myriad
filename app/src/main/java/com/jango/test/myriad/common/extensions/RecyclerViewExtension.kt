package com.jango.test.myriad.common.extensions

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.jango.test.myriad.ui.home.adapter.EndlessScrollListener

/**
 * Helpful RecycleView extension functions
 */

// Add scroll listener to the asked view and invoke the parameter function when scroll boundary is reached
fun RecyclerView.onScrollBoundary(invoke: (Int) -> Unit, layoutManager: StaggeredGridLayoutManager) =
    addOnScrollListener(object : EndlessScrollListener(layoutManager = layoutManager){
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
            invoke(page)
        }
    })