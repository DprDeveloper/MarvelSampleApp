package es.dpr.marvelsampleapp.ui.screens.character.list


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import es.dpr.marvelsampleapp.R
import es.dpr.marvelsampleapp.designsystem.character.CharacterItem
import es.dpr.marvelsampleapp.designsystem.character.CharacterSearcher
import es.dpr.marvelsampleapp.designsystem.common.error.ErrorScreen
import es.dpr.marvelsampleapp.designsystem.common.loader.AnimatedPreloader
import es.dpr.marvelsampleapp.domain.model.character.CharacterDomainModel
import es.dpr.marvelsampleapp.domain.model.common.imageUrl
import es.dpr.marvelsampleapp.ui.screens.character.enums.State

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    onCharacterItemClick:(Int) -> Unit,
) {
    var context = LocalContext.current
    val lazyState = rememberLazyListState()
    var loading by remember { mutableStateOf(true) }
    var showError by remember { mutableStateOf(false) }
    val uiState by remember { viewModel.uiState }
    val searcherErrorMessage = stringResource(id = R.string.searcher_empty)

    Surface(modifier = Modifier.fillMaxSize()) {
        if(loading){
            AlertDialog(onDismissRequest = {}) {
                AnimatedPreloader(
                    modifier = Modifier.size(200.dp)
                )
            }
        }
        if (showError){
            ErrorScreen(){
                viewModel.reload()
            }
        } else {
            CharacterListContent(
                searcherText = uiState.searcherText,
                characterList = uiState.characterList,
                lazyState = lazyState,
                state = uiState.state,
                characterTotal = uiState.characterTotal,
                onSearcherTextChange = { viewModel.onSearcherTextChange(it) },
                onSearcherErrorMessage = {
                    Toast.makeText(context, searcherErrorMessage, Toast.LENGTH_SHORT).show()
                },
                onFindCharacter = { viewModel.findCharacter() },
                onClearCharacter = { viewModel.clearCharacter() },
                onCharacterItemClick = onCharacterItemClick,
                onLoadMoreCharacter = { viewModel.loadMoreCharacter() },
            )
        }
    }
    LaunchedEffect(uiState.state){
        when(uiState.state){
            State.INIT -> {
                loading = true
                showError = false
                viewModel.getCharacter()
            }
            State.LOADING -> {
                loading = true
            }
            State.COMPLETE -> {
                loading = false
            }
            State.ERROR -> {
                loading = false
                showError = true
            }
        }
    }
}

@Composable
fun CharacterListContent(
    searcherText: String,
    characterList: List<CharacterDomainModel>,
    lazyState: LazyListState,
    state: State,
    characterTotal: Int,
    onSearcherTextChange: (String)->Unit,
    onSearcherErrorMessage: ()->Unit,
    onFindCharacter: ()->Unit,
    onClearCharacter: ()->Unit,
    onCharacterItemClick: (Int)->Unit,
    onLoadMoreCharacter: ()->Unit,
) {
    CharacterSearcher(
        text = searcherText,
        onTextChange = {
            onSearcherTextChange(it)
        },
        onSearchClick = {
            if(searcherText.isBlank()){
                onSearcherErrorMessage()

            } else {
                onFindCharacter()
            }
        },
        onSearchClearClick = {
            onClearCharacter()
        }
    )
    if(characterList.isNotEmpty()){
        LazyColumn(state = lazyState, modifier = Modifier.padding(vertical = 80.dp)){
            itemsIndexed(items = characterList){ index, character ->
                CharacterItem(
                    title = character.name,
                    imageUrl = character.thumbnail.imageUrl(),
                    comicSize = character.comics.items?.size?:0,
                    onCharacterClick = {
                        onCharacterItemClick(character.id)
                    }
                )
                if(index >= characterList.size - 1 &&
                    state != State.LOADING &&
                    characterList.size < characterTotal) {
                    onLoadMoreCharacter()
                }
            }
        }
    }
}