package ru.otus.basicarchitecture.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.databinding.PersonalDataFragmentBinding
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class PersonalDataFragment : Fragment(R.layout.personal_data_fragment) {

    private lateinit var binding: PersonalDataFragmentBinding
    private val viewModel: PersonalDataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonalDataFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.nextButtonState.observe(viewLifecycleOwner) {
            binding.nextButton.isEnabled = it
        }
        binding.dateField.addTextChangedListener {
            viewModel.validateBirthDate(it?.toString())
        }
        binding.firstNameField.setText(viewModel.cache.firstName)
        binding.lastNameField.setText(viewModel.cache.lastName)
        binding.dateField.setText(
            viewModel.cache.birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.personalDataNext)
        }
    }

}