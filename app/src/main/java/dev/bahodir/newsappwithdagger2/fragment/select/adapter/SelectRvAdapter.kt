package dev.bahodir.newsappwithdagger2.fragment.select.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dev.bahodir.newsappwithdagger2.R
import dev.bahodir.newsappwithdagger2.databinding.SelectRvItemBinding
import dev.bahodir.newsappwithdagger2.fragment.select.model.SelectUser
import dev.bahodir.newsappwithdagger2.fragment.select.room.SelectDB

class SelectRvAdapter(private var context: Context, private var list: List<SelectUser>, private var listener: OnSelectItem) :
    RecyclerView.Adapter<SelectRvAdapter.VH>() {

    interface OnSelectItem {
        fun selectItem(selectUser: SelectUser, position: Int)
    }

    inner class VH(var binding: SelectRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(selectUser: SelectUser, position: Int) {
            binding.textLoad.text = selectUser.tv

            if (selectUser.bool) {
                binding.textLoad.setBackgroundResource(R.drawable.next_back)
                binding.textLoad.setTextColor(Color.WHITE)
            }
            else {
                binding.textLoad.setBackgroundResource(R.drawable.white_back)
                binding.textLoad.setTextColor(Color.BLACK)
            }

            itemView.setOnClickListener {
                if (!selectUser.bool) {
                    selectUser.bool = true
                    binding.textLoad.setBackgroundResource(R.drawable.next_back)
                    binding.textLoad.setTextColor(Color.WHITE)
                    Toast.makeText(binding.root.context, "like", Toast.LENGTH_SHORT).show()
                }
                else if (selectUser.bool) {
                    selectUser.bool = false
                    binding.textLoad.setBackgroundResource(R.drawable.white_back)
                    binding.textLoad.setTextColor(Color.BLACK)
                    Toast.makeText(binding.root.context, "dislike", Toast.LENGTH_SHORT).show()
                }

                listener.selectItem(selectUser, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(SelectRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}