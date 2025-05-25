package com.ok.touristvibes.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ok.touristvibes.R

@Composable
fun CountrySearchView(searchValue: String, onSearch: (searchValue: String) -> Unit) {

    val focusManager = LocalFocusManager.current

    Row {
        Card(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            border = BorderStroke(1.dp, colorResource(R.color.divider)),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            TextField(modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(colorResource(R.color.white), shape = RoundedCornerShape(10.dp)),
                value = searchValue, onValueChange = {
                onSearch(it)
            },
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.lexend_deca_regular)),
                    fontWeight = FontWeight(400),
                    color = colorResource(R.color.black)),
                placeholder = {
                Text(text = stringResource(R.string.search_text),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.lexend_deca_light)),
                        fontWeight = FontWeight(300),
                        color = colorResource(R.color.hint_txt_color),

                        )
                )},
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }, trailingIcon = {
                    if (searchValue.isNotEmpty()) {
                        IconButton(onClick = {
                            onSearch("")
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                tint = Color.Black.copy(0.3f),
                                contentDescription = "Clear icon"
                            )
                        }
                    }
                }, colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ), keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ), keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                })
            )
        }
    }
}

@Preview
@Composable
fun PreviewSearchView() {
    CountrySearchView("search") {

    }
}