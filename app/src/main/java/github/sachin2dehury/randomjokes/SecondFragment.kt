package github.sachin2dehury.randomjokes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import github.sachin2dehury.randomjokes.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel by viewModels<SecondViewModel>()
    private val randomAdapter = RandomAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        val database =
            Room.databaseBuilder(requireContext(), RandomDb::class.java, "random").build()
        _binding?.run {
            randomAdapter.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            recyclerView.adapter = randomAdapter
            lifecycleScope.launch(Dispatchers.Default) {
                val dao = database.getDao()
                for (i in 0..2000) {
                    dao.insertData(RandomData(i, ""))
                }
                launch(Dispatchers.Main) {
                    viewModel.fetchData(dao).collectLatest {
                        randomAdapter.submitData(it)
                    }
                }
                Log.w("Sachin", "Started")
//                delay(3000)
                for (i in 0..2000) {
//                    delay(2)
                    dao.insertData(RandomData(i))
                }
                randomAdapter.refresh()
                withContext(Dispatchers.Main) {
//                    _binding?.recyclerView?.swapAdapter(randomAdapter, true)
                }
                Log.w("Sachin", "Ended")
            }
        }
    }
}
