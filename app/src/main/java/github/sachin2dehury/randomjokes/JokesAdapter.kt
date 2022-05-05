package github.sachin2dehury.randomjokes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import github.sachin2dehury.randomjokes.databinding.JokeItemViewBinding

class JokesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var jokeList = emptyList<JokeResponse>()

    class JokeDiffer : DiffUtil.ItemCallback<JokeResponse>() {

        override fun areItemsTheSame(oldItem: JokeResponse, newItem: JokeResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JokeResponse, newItem: JokeResponse): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class JokeViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.joke_item_view, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = JokeItemViewBinding.bind(holder.itemView)
        val joke = jokeList[position]
        binding.textView.text = joke.value
        binding.imageView.load(joke.iconUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int {
        return jokeList.size
    }
}
