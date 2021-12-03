package dev.bahodir.newsappwithdagger2.adapter.bookmarks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.data.SaveEntity
import dev.bahodir.newsappwithdagger2.databinding.BrowseItemFragmentRvItemBinding
import dev.bahodir.newsappwithdagger2.model.Article
import kotlin.coroutines.coroutineContext

class BrowseItemVpFragmentRvAdapter(
    private var listener: OnClickListener
) : ListAdapter<Article, BrowseItemVpFragmentRvAdapter.MyVh>(MyDiffUtil()) {

    interface OnClickListener {
        fun saveClick(saveEntity: SaveEntity, position: Int, bool: Boolean)
        fun itemClick(article: Article, position: Int)
    }

    class MyDiffUtil : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.source.name == newItem.source.name
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    inner class MyVh(var binding: BrowseItemFragmentRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article, position: Int) {
            var bool: Boolean = false

            Picasso.get().load(article.urlToImage).placeholder(R.drawable.img1).into(binding.imageLoad)
            binding.title.text = article.main
            binding.description.text = article.description

            itemView.setOnClickListener {
                listener.itemClick(article, position)
            }

            binding.save.setOnClickListener {
            val saveEntity = SaveEntity(id = article.publishedAt, image = article.urlToImage, title = article.main, description = article.description)

                if (!bool) {
                    binding.save.setImageResource(R.drawable.ic_bookmark_all_white)
                    bool = true
                }
                else {
                    binding.save.setImageResource(R.drawable.ic_save_white)
                    bool = false
                }
                listener.saveClick(saveEntity, position, bool)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVh {
        return MyVh(
            BrowseItemFragmentRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyVh, position: Int) {
        holder.bind(getItem(position), position)
    }
}