package gr.ote.academy.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gr.ote.academy.databinding.HolderMainListBinding

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerViewHolder>() {

    private val dataList = mutableListOf<String>()

    fun updateList(data: List<String>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = dataList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val binding =
            HolderMainListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = dataList.size
}

class MainRecyclerViewHolder(private val binding: HolderMainListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: String) {
        binding.holderTitle.text = data
    }
}