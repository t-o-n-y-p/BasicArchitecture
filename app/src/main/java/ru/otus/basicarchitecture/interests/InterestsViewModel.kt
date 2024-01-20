package ru.otus.basicarchitecture.interests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.otus.basicarchitecture.WizardCache
import javax.inject.Inject

@HiltViewModel
class InterestsViewModel @Inject constructor(
    val useCase: InterestsUseCase,
    val cache: WizardCache
) : ViewModel() {

    private val mInterestsGroupState = MutableLiveData<InterestsGroupState>(InterestsGroupState.NotSet)
    val interestsGroupState: LiveData<InterestsGroupState> get() = mInterestsGroupState

    fun loadAvailableInterests() = viewModelScope.launch {
        mInterestsGroupState.value = InterestsGroupState.Loading
        try {
            val interests = withContext(Dispatchers.IO) {
                useCase.getAvailableInterests()
            }
            mInterestsGroupState.value = InterestsGroupState.Content(interests)
        } catch (t: Throwable) {
            mInterestsGroupState.value = InterestsGroupState.NotSet
        }
    }



    sealed class InterestsGroupState {

        data object NotSet: InterestsGroupState()

        data object Loading: InterestsGroupState()

        data class Content(val value: List<String>): InterestsGroupState()
    }

}