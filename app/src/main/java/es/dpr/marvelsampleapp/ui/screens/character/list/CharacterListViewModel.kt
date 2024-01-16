package es.dpr.marvelsampleapp.ui.screens.character.list

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.dpr.marvelsampleapp.domain.character.GetCharacterUseCase
import es.dpr.marvelsampleapp.domain.model.character.CharacterDomainModel
import es.dpr.marvelsampleapp.ui.screens.character.enums.State
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val offset = mutableIntStateOf(0)
    private val limit = mutableIntStateOf(20)
    val uiState = mutableStateOf(UiState())
    data class UiState(
        val state: State = State.INIT,
        val characterList: List<CharacterDomainModel> = emptyList()
    )
    fun getCharacter() = viewModelScope.launch {
        uiState.value = uiState.value.copy(state = State.LOADING)
        characterUseCase.getAllCharacter(
            offset = offset.value,
            limit = limit.value
        ).collect{
            uiState.value = uiState.value.copy(
                characterList = uiState.value.characterList + it.data.results,
                state = State.COMPLETE
            )
        }
    }

    fun loadMoreCharacter() {
        offset.value += limit.value
        getCharacter()
    }
}

