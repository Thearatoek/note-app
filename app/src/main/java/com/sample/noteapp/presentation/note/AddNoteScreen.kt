package com.sample.noteapp.presentation.note


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sample.noteapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewNoteScreen(
     navController: NavController,
    viewModel: AddNewItemViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    Scaffold(
        contentColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            focusManager.clearFocus()
        },
        topBar = {
           TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    actionIconContentColor =MaterialTheme.colorScheme.primary
                ),
               navigationIcon = {
                   Image(
                       colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
                       painter = painterResource(R.drawable.icon_back),
                       contentDescription = null,
                       modifier = Modifier
                           .size(30.dp)
                           .padding(start = 12.dp)
                           .clickable(
                               indication = null,
                               interactionSource = remember { MutableInteractionSource() }
                           ) {
                               navController.popBackStack()
                           }
                   )
               },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                      Text(
                            text = "Add Notes",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 40.dp),
                            textAlign = TextAlign.Center,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    Text(
                        text = "Save",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (state.title.isNotBlank()) MaterialTheme.colorScheme.primary else Color.Gray
                        ),
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(
                                enabled = state.title.isNotBlank(),
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                viewModel.onSaveClick()
                                navController.popBackStack()
                            }
                    )
                },
            )
        },
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
                textStyle = MaterialTheme.typography.labelLarge.copy(
                    color = Color.DarkGray,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (state.title.isEmpty()) {
                        Text(
                            text = "Title",
                            style = MaterialTheme.typography.labelLarge.copy(
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
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.labelLarge.copy(
                    color = Color.DarkGray,   fontSize = 22.sp
                    ),
                decorationBox = { innerTextField ->
                    if (state.description.isEmpty()) {
                        Text(
                            text = "Description...",
                            style = MaterialTheme.typography.labelLarge.copy(
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
