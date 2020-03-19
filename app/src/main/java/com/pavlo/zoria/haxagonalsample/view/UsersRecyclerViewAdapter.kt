package com.pavlo.zoria.haxagonalsample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pavlo.zoria.haxagonalsample.R
import com.pavlo.zoria.haxagonalsample.view.user.model.UserModel
import kotlinx.android.synthetic.main.user_view_holder.view.*

class UsersRecyclerViewAdapter(
    private val newsList: ArrayList<UserModel>
) : RecyclerView.Adapter<UsersRecyclerViewAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view_holder, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val new = newsList[position]
        holder.bind(new)

        with(holder.mView) {
            tag = new
        }
    }

    override fun getItemCount(): Int = newsList.size

    fun setItems(items: List<UserModel>, position: Int) {
        newsList.clear()
        newsList.addAll(position, items)
        notifyItemRangeInserted(position, items.size)
    }

    fun addItems(items: List<UserModel>, position: Int) {
        newsList.addAll(position, items)
        notifyItemRangeInserted(position, items.size)
    }

    inner class UserViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        private val imageView: ImageView = mView.image
        private val title: TextView = mView.title

        fun bind(news: UserModel) {
            title.text = news.name
//            Glide
//                .with(itemView)
//                .load(news.imageUrl)
//                .centerCrop()
//                .into(imageView)
        }
    }
}
