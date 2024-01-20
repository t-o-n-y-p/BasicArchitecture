package ru.otus.basicarchitecture.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.otus.basicarchitecture.WizardCache
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

@HiltViewModel
class PersonalDataViewModel @Inject constructor(
    private val useCase: PersonalDataUseCase,
    val cache: WizardCache
) : ViewModel() {

    private val mNextButtonState = MutableLiveData(false)
    val nextButtonState: LiveData<Boolean> get() = mNextButtonState

    fun validateBirthDate(date: String?) {
        try {
            mNextButtonState.value =
                date?.let { useCase.isBirthDateValid(
                    LocalDate.parse(it, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                } ?: false
        } catch (e: DateTimeParseException) {
            mNextButtonState.value = false
        }
    }

}