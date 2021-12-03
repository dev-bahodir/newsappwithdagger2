package dev.bahodir.newsappwithdagger2.fragment.welcome.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.bahodir.newsappwithdagger2.databinding.FragmentWelcomeScreenBinding
import dev.bahodir.newsappwithdagger2.databinding.WelcomeScreenVpItemBinding

class WelcomeScreenVpAdapter(var list: List<Int>) : RecyclerView.Adapter<WelcomeScreenVpAdapter.VH>() {

    inner class VH(var binding: WelcomeScreenVpItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.imageLoad.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(WelcomeScreenVpItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}