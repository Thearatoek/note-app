package com.sample.noteapp.presentation.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
@Composable
fun AddNewNoteScreen(
     navController: NavController,
    viewModel: AddNewItemViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            focusManager.clearFocus()
        },
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFFE6CCE6), // light pink like screenshot
                elevation = 0.dp,
                title = {
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.DarkGray)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onSaveClick()
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Check, contentDescription = "Save", tint = Color.DarkGray)
                    }
                }
            )
        },
        backgroundColor = Color(0xFFE6CCE6) // same as background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BasicTextField(
                value = state.title,
                onValueChange = {
                    viewModel.onTitleChange(it)
                },
                textStyle = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (state.title.isEmpty()) {
                        Text(
                            text = "Title",
                            style = MaterialTheme.typography.body1.copy(
                                color = Color.Gray,
                                fontSize = 30.sp
                            )
                        )
                    }
                    innerTextField()
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
            // Editable Description
            BasicTextField(
                value = state.description,
                onValueChange = {
                    viewModel.onDescriptionChange(it)
                },
                textStyle = MaterialTheme.typography.body1.copy(color = Color.DarkGray),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                decorationBox = { innerTextField ->
                    if (state.description.isEmpty()) {
                        Text(
                            text = "Description...",
                            style = MaterialTheme.typography.body1.copy(
                                color = Color.Gray,
                                fontSize = 22.sp
                            )
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}
