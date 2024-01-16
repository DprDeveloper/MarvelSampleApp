package es.dpr.marvelsampleapp.ui.screens.character.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import es.dpr.marvelsampleapp.designsystem.character.CharacterItem
import es.dpr.marvelsampleapp.designsystem.common.loader.AnimatedPreloader
import es.dpr.marvelsampleapp.domain.model.common.imageUrl
import es.dpr.marvelsampleapp.ui.screens.character.enums.State

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
) {
    val lazyState = rememberLazyListState()
    var loading by remember { mutableStateOf(true) }
    val uiState by remember { viewModel.uiState }

    Surface(modifier = Modifier.fillMaxSize()) {
        if(loading){
            AlertDialog(onDismissRequest = {}) {
                AnimatedPreloader(
                    modifier = Modifier.size(200.dp)
                )
            }
        }
        if(uiState.characterList.isNotEmpty()){
            LazyColumn(state = lazyState){
                itemsIndexed(items = uiState.characterList){ index, character ->
                    CharacterItem(
                        title = character.name,
                        imageUrl = character.thumbnail.imageUrl(),
                        comicSize = character.comics.items?.size?:0,
                        onCharacterClick = {
                            //Implement navigation detail
                        }
                    )
                    if(index >= uiState.characterList.size - 1 &&
                        uiState.state != State.LOADING) {
                        viewModel.loadMoreCharacter()
                    }
                }
            }
        }
    }
    LaunchedEffect(uiState.state){
        when(uiState.state){
            State.INIT -> {
                loading = true
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
            }
        }
    }
}