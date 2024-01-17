package es.dpr.marvelsampleapp.designsystem.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CharacterSearcher(
    text: String,
    onTextChange: (String)->Unit,
    onSearchClick: ()->Unit,
    onSearchClearClick: ()->Unit,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(30.dp)){
        TextField(
            value = text,
            onValueChange = onTextChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onSearchClearClick()
                    }
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onSearchClick()
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}