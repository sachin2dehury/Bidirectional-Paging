package github.sachin2dehury.randomjokes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.sachin2dehury.randomjokes.databinding.JokeItemViewBinding

class RandomAdapter : PagingDataAdapter<RandomData, RecyclerView.ViewHolder>(RandomDiffer()) {
    class RandomDiffer : DiffUtil.ItemCallback<RandomData>() {
        override fun areItemsTheSame(oldItem: RandomData, newItem: RandomData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RandomData, newItem: RandomData): Boolean {
            return oldItem == newItem
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = JokeItemViewBinding.bind(holder.itemView)
        val item = getItem(position)
        binding.textView.text = "${item?.id}\t${item?.value}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.joke_item_view, parent, false)
        return RandomViewHolder((view))
    }

    inner class RandomViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
