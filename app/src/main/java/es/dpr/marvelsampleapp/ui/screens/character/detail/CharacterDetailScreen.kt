package es.dpr.marvelsampleapp.ui.screens.character.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import es.dpr.marvelsampleapp.R
import es.dpr.marvelsampleapp.designsystem.character.CharacterItem
import es.dpr.marvelsampleapp.designsystem.character.ComicItem
import es.dpr.marvelsampleapp.designsystem.common.loader.AnimatedPreloader
import es.dpr.marvelsampleapp.domain.model.common.imageUrl
import es.dpr.marvelsampleapp.ui.screens.character.list.CharacterListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    characterId: Int = 1011334,
) {
    var loading by remember { mutableStateOf(true) }
    val uiState by remember { viewModel.uiState }
    val lazyState = rememberLazyListState()

    Surface(modifier = Modifier.fillMaxSize()) {
        if(loading){
            AlertDialog(onDismissRequest = {}) {
                AnimatedPreloader(
                    modifier = Modifier.size(200.dp)
                )
            }
        }
        uiState.character?.let { character ->
            LazyColumn(state = lazyState){
                item{
                    AsyncImage(
                        model = character.thumbnail.imageUrl(),
                        contentDescription = character.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .aspectRatio(1f)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp,
                                vertical = 4.dp
                            ),
                        text = character.name,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp,
                                vertical = 4.dp
                            ),
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec erat vehicula, consectetur lacus vel, posuere odio. Sed nulla odio, pulvinar sit amet tristique vestibulum, tristique vitae tellus. Vivamus porttitor velit non odio congue molestie. Donec porta velit vel tellus feugiat fermentum. Fusce viverra hendrerit est at mattis. Pellentesque ullamcorper ante sed magna pellentesque elementum. Nulla consectetur, diam id porttitor tempor, magna massa ultrices justo, sed vehicula sapien arcu non erat. Nunc nec lacus vel nibh lacinia auctor.",
                        fontStyle = FontStyle.Normal,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        text = stringResource(id = R.string.character_detail_comic_title),
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    )
                }
                if (uiState.comics.isNotEmpty()) {
                    itemsIndexed(items = uiState.comics) { index, comic ->
                        ComicItem(
                            name = comic.title,
                            imageUrl = comic.image.imageUrl(),
                            date = comic.date,
                        )
                    }
                }
            }
        }
    }
    LaunchedEffect(uiState.state){
        when(uiState.state){
            CharacterDetailViewModel.State.INIT -> {
                loading = true
                viewModel.getCharacterDetail(characterId)
                viewModel.getComicsByCharacter(characterId)
            }
            CharacterDetailViewModel.State.LOADING -> {
                loading = true
            }
            CharacterDetailViewModel.State.COMPLETE -> {
                loading = false
            }
            CharacterDetailViewModel.State.ERROR -> {
                loading = false
            }
        }
    }
}