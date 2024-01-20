package ru.otus.basicarchitecture.address

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.otus.basicarchitecture.WizardCache
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    val useCase: AddressUseCase,
    val cache: WizardCache
) : ViewModel() {
}