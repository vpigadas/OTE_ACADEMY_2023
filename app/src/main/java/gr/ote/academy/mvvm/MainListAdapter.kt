package gr.ote.academy.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gr.ote.academy.databinding.HolderMainListBinding

class MainListAdapter:
    ListAdapter<String, MainListViewHolder>(MainListDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        val binding =
            HolderMainListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MainListViewHolder(private val binding: HolderMainListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: String) {
        binding.holderTitle.text = data
    }
}

class MainListDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

}