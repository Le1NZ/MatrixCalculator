package ru.pokrovskii.matrixcalculator

import android.content.Context
import android.widget.Toast

fun createMatrixBySize(size: Int): MutableList<MutableList<String>> {
    return MutableList(size) { MutableList(size) { "0" } }
}

fun modifyMatrix(
    originalMatrix: MutableList<MutableList<String>>,
    positionX: Int,
    positionY: Int,
    newValue: String,
): MutableList<MutableList<String>> {
    val newMatrix = originalMatrix.map { it.toMutableList() }.toMutableList()
    newMatrix[positionX][positionY] = newValue
    return newMatrix
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}