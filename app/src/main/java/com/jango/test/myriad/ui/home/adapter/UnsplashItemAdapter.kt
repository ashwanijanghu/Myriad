package com.jango.test.myriad.ui.home.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jango.test.myriad.R
import com.jango.test.myriad.data.model.UnsplashItem
import kotlinx.android.synthetic.main.fragment_unsplash_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [UnsplashItem]
 */
class UnsplashItemAdapter(
    private val context:Context,
    var mValues: MutableList<UnsplashItem>
) : RecyclerView.Adapter<UnsplashItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_unsplash_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        val w = context.resources.getInteger(R.integer.image_width)
        val h = context.resources.getInteger(R.integer.image_height)
        Glide.with(context)
            .load(item.urls?.raw+"&w=$w&h=$h&fit=clamp")
            .apply(RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_picture)
                .error(R.drawable.ic_picture))
            .into(holder.imageView)

        with(holder.mView) {
            tag = item
            setOnClickListener {
                var desc = item.description
                if(item.description.isNullOrEmpty() || item.description.isNullOrBlank()){
                    desc = context.resources.getString(R.string.no_desc)
                }
                Toast.makeText(it.context, desc, Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun getItemCount(): Int = mValues.size

    fun updateData(list:List<UnsplashItem>){
        if(mValues != null){
            mValues.addAll(list)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val imageView: ImageView = mView.imageItem
    }
}
