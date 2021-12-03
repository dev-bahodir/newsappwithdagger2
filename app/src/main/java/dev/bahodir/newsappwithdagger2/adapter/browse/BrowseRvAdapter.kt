package dev.bahodir.newsappwithdagger2.adapter.browse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.databinding.BrowseRvItemBinding
import dev.bahodir.newsappwithdagger2.fragment.select.model.SelectUser
import dev.bahodir.newsappwithdagger2.model.Article
import java.text.FieldPosition

class BrowseRvAdapter(var listener: OnClickListener) : ListAdapter<Article, BrowseRvAdapter.MyVH>(MyDU()) {

    class MyDU : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.source.name == newItem.source.name
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    inner class MyVH(var binding: BrowseRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article, position: Int) {
            Picasso.get().load(article.urlToImage).placeholder(R.drawable.img1).into(binding.imageLoad)
            binding.title.text = article.main
            binding.description.text = article.description

            itemView.setOnClickListener {
                listener.itemClick(article, position)
            }
        }
    }
    interface OnClickListener {
        fun itemClick(article: Article, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(BrowseRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.bind(article = getItem(position), position = position)
    }
}