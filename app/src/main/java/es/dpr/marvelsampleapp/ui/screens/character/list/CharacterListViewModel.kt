package es.dpr.marvelsampleapp.ui.screens.character.list

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.dpr.marvelsampleapp.domain.character.GetCharacterUseCase
import es.dpr.marvelsampleapp.domain.model.character.CharacterDomainModel
import es.dpr.marvelsampleapp.ui.screens.character.enums.CharacterType
import es.dpr.marvelsampleapp.ui.screens.character.enums.State
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val offset = mutableIntStateOf(0)
    private val limit = mutableIntStateOf(20)
    private val characterType = mutableStateOf(CharacterType.NORMAL)

    val uiState = mutableStateOf(UiState())
    data class UiState(
        val state: State = State.INIT,
        val searcherText: String = "",
        val characterTotal: Int = 0,
        val characterList: List<CharacterDomainModel> = emptyList()
    )
    fun getCharacter() = viewModelScope.launch {
        uiState.value = uiState.value.copy(state = State.LOADING)
        characterType.value = CharacterType.NORMAL
        characterUseCase.getAllCharacter(
            offset = offset.value,
            limit = limit.value
        ).collect{
            if(it.error){
                uiState.value = uiState.value.copy(state = State.ERROR)
            } else {
                requireNotNull(it.data)
                uiState.value = uiState.value.copy(
                    characterList = uiState.value.characterList + it.data.results,
                    state = State.COMPLETE,
                    characterTotal = it.data.total
                )
            }
        }
    }

    private fun getCharacterByStartName() = viewModelScope.launch {
        uiState.value = uiState.value.copy(state = State.LOADING)
        characterType.value = CharacterType.FIND
        characterUseCase.getCharacterByStartName(
            offset = offset.value,
            limit = limit.value,
            nameStartsWith = uiState.value.searcherText
        ).collect{
            if(it.error){
                uiState.value = uiState.value.copy(state = State.ERROR)
            } else {
                requireNotNull(it.data)
                uiState.value = uiState.value.copy(
                    characterList = uiState.value.characterList + it.data.results,
                    state = State.COMPLETE,
                    characterTotal = it.data.total
                )
            }
        }
    }

    fun loadMoreCharacter() {
        offset.value += limit.value
        when(characterType.value){
            CharacterType.NORMAL -> getCharacter()
            CharacterType.FIND -> getCharacterByStartName()
        }

    }

    fun onSearcherTextChange(text: String) {
        uiState.value = uiState.value.copy(searcherText = text)
    }
    fun findCharacter() {
        cleanPagination()
        uiState.value = uiState.value.copy(
            characterList = emptyList(),
        )
        getCharacterByStartName()
    }

    fun clearCharacter() {
        uiState.value = uiState.value.copy(
            characterList = emptyList(),
            searcherText = "",
            characterTotal = 0
        )
        cleanPagination()
        getCharacter()
    }

    private fun cleanPagination(){
        offset.value = 0
        limit.value = 20
    }

    fun reload() {
        uiState.value = uiState.value.copy(state = State.INIT)
    }
}

