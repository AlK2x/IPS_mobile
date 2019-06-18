package ips.mobile.gitrockstars.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SearchItemAdapter(
    private val callback: (UserSearchItemViewModel) -> Unit
) : ListAdapter<UserSearchItemViewModel, ItemViewHolder>(
    object : DiffUtil.ItemCallback<UserSearchItemViewModel>() {
        override fun areItemsTheSame(oldItem: UserSearchItemViewModel, newItem: UserSearchItemViewModel): Boolean {
            return oldItem.repo.full_name == newItem.repo.full_name
        }

        override fun areContentsTheSame(oldItem: UserSearchItemViewModel, newItem: UserSearchItemViewModel): Boolean {
            return oldItem.repo.description == newItem.repo.description
                    && oldItem.repo.starts == newItem.repo.starts
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RepoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.root.setOnClickListener {
            binding.viewModel?.let {
                callback(it)
            }
        }
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ItemViewHolder(val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: UserSearchItemViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

}