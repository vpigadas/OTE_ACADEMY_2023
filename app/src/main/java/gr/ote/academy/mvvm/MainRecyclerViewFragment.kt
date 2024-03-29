package gr.ote.academy.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import gr.ote.academy.databinding.FragmentMainRecyclerViewBinding

class MainRecyclerViewFragment : Fragment() {
    private lateinit var _binding: FragmentMainRecyclerViewBinding

    private val viewModel by viewModels<MainFragmentViewModel>()
    private val activityViewModel by activityViewModels<MainAndroidViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainRecyclerViewBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onStart()

        activityViewModel.listItemsStream.observe(viewLifecycleOwner, Observer {
            viewModel.updateList(it)
        })

        _binding.mainRecycler.adapter = viewModel.adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        viewModel.onStop()
        super.onPause()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainRecyclerViewFragment()
    }
}