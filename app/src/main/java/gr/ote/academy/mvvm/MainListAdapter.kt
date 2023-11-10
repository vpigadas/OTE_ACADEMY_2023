package gr.ote.academy.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gr.ote.academy.R
import gr.ote.academy.databinding.HolderMainList2Binding
import gr.ote.academy.databinding.HolderMainListBinding

class MainListAdapter :
    ListAdapter<String, CustomViewHolder>(MainListDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        when (viewType) {
            R.layout.holder_main_list_2 -> {
                val binding =
                    HolderMainList2Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return MainListViewHolder2(binding)
            }
            else -> {
                val binding =
                    HolderMainListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return MainListViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        val data = getItem(position)
        return when (position % 2 == 0) {
            true -> R.layout.holder_main_list
            false -> R.layout.holder_main_list_2
        }
    }
}

abstract class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: String)
}

class MainListViewHolder(private val binding: HolderMainListBinding) :
    CustomViewHolder(binding.root) {

    override fun bind(data: String) {
        binding.holderTitle.text = data
    }
}

class MainListViewHolder2(private val binding: HolderMainList2Binding) :
    CustomViewHolder(binding.root) {

    override fun bind(data: String) {
        binding.holderTitle.text = data
    }
}

class MainListDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

}