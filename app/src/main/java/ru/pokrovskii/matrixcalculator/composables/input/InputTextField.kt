package ru.pokrovskii.matrixcalculator.composables.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun InputTextField(
    currentText: String?,
    onTextChanged: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
) {
    ShortInputTextField(
        currentText = currentText,
        onTextChanged = onTextChanged,
        modifier = modifier
            .fillMaxWidth(),
        keyboardType = keyboardType,
    )
}

@Composable
internal fun ShortInputTextField(
    currentText: String?,
    onTextChanged: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier,
        value = currentText ?: "",
        onValueChange = { newText ->
            onTextChanged(resolveNewText(currentText, newText))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        )
    )
}

private fun resolveNewText(
    currentText: String?,
    newText: String,
): String {
    return if (currentText == "0" && newText == "00") {
        "0"
    } else if (currentText == "0") {
        newText.filter { it != '0' }
    } else {
        newText
    }
}

@Composable
@Preview
private fun InputTextFieldPreview() {
    InputTextField(
        currentText = "Simple text",
        onTextChanged = { },
        keyboardType = KeyboardType.Number,
    )
}