package com.mysuperappcompose.core.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.mysuperappcompose.core.R
import com.mysuperappcompose.core.utils.Dimens

@Composable
fun SearchBar(
    //query: String,
    context: Context,
    modifier: Modifier = Modifier,
    isEnabled: (Boolean) = true,
    onSearchClicked: () -> Unit = {},
    //onQueryChange: (String) -> Unit = {}
    onQueryChange: (String) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }
    var isTextFieldFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = Dimens.dp16,
                end = Dimens.dp16,
                top = Dimens.dp16,
                bottom = Dimens.dp16
            )
            .clickable { onSearchClicked() }
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            //onValueChange = onQueryChange,
            enabled = isEnabled,
            modifier = modifier
                .focusRequester(FocusRequester())
                .onFocusChanged { isTextFieldFocused = it.isFocused }
                .fillMaxWidth()
                .heightIn(min = Dimens.dp48, max = Dimens.dp48)
                .clip(shape = RoundedCornerShape(Dimens.dp8))
                .border(
                    border = BorderStroke(
                        Dimens.dp1,
                        MaterialTheme.colors.primary.copy(alpha = 0.4f)
                    ),
                    shape = RoundedCornerShape(Dimens.dp8)
                ),
            textStyle = TextStyle(fontSize = Dimens.sp14),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.dp20),
                )
            },
            trailingIcon = {
                if (isTextFieldFocused && query.isNotEmpty()) {
                    IconButton(onClick = { query = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = stringResource(R.string.clear),
                            modifier = Modifier.size(Dimens.dp20),
                        )
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = MaterialTheme.colors.onSurface,
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.search_product),
                    fontSize = Dimens.sp14,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                if (query.length <= 2) {
                    Toast.makeText(context, "Minimum 3 Letters to Search", Toast.LENGTH_SHORT).show()
                } else {
                    onQueryChange(query)
                }
            }),
        )
    }
}