package ru.otus.basicarchitecture.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.databinding.InterestsFragmentBinding

@AndroidEntryPoint
class SummaryFragment : Fragment(R.layout.summary_fragment) {

    private lateinit var binding: InterestsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = InterestsFragmentBinding.inflate(inflater)
        return binding.root
    }

}