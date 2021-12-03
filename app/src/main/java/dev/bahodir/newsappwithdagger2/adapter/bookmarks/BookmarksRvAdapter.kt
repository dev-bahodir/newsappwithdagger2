package dev.bahodir.newsappwithdagger2.adapter.bookmarks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.data.SaveEntity
import dev.bahodir.newsappwithdagger2.databinding.BrowseRvItemBinding

class BookmarksRvAdapter(var listener: OnClickListener) : ListAdapter<SaveEntity, BookmarksRvAdapter.MyVH>(MyDU()) {

    class MyDU : DiffUtil.ItemCallback<SaveEntity>() {
        override fun areItemsTheSame(oldItem: SaveEntity, newItem: SaveEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SaveEntity, newItem: SaveEntity): Boolean {
            return oldItem == newItem
        }

    }
    inner class MyVH(var binding: BrowseRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(save: SaveEntity, position: Int) {
            Picasso.get().load(save.image).placeholder(R.drawable.img1).into(binding.imageLoad)
            binding.title.text = save.title
            binding.description.text = save.description

            itemView.setOnClickListener {
                listener.itemClick(save, position)
            }
        }
    }
    interface OnClickListener {
        fun itemClick(save: SaveEntity, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(BrowseRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.bind(save = getItem(position), position = position)
    }
}