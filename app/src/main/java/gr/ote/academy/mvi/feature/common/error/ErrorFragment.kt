package gr.ote.academy.mvi.feature.common.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import gr.ote.academy.R
import gr.ote.academy.mvi.MVIViewModel

class ErrorFragment : Fragment() {
    private val parentViewModel: MVIViewModel by activityViewModels<MVIViewModel>()
    private val viewModel: ErrorViewModel by viewModels<ErrorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {

        }
        //activity?.setActionBar(toolbar)

        viewModel.onStart()
    }

    override fun onPause() {
        viewModel.onStop()
        super.onPause()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ErrorFragment()
    }
}