package es.dpr.marvelsampleapp.ui.screens.character.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.dpr.marvelsampleapp.domain.character.GetCharacterUseCase
import es.dpr.marvelsampleapp.domain.model.character.CharacterDomainModel
import es.dpr.marvelsampleapp.ui.screens.character.list.CharacterListViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterUseCase: GetCharacterUseCase
) : ViewModel() {

    val uiState = mutableStateOf(UiState())
    data class UiState(
        val state: State = State.INIT,
        val character: CharacterDomainModel? = null
    )

    fun getCharacterDetail(characterId: Int) = viewModelScope.launch {
        uiState.value = uiState.value.copy(state = State.LOADING)
        characterUseCase.getCharacterById(characterId = characterId).collect {
            uiState.value = uiState.value.copy(
                character = it.data.results.first(),
                state = State.COMPLETE
            )
        }
    }
    enum class State{
        INIT,
        LOADING,
        ERROR,
        COMPLETE,
    }
}