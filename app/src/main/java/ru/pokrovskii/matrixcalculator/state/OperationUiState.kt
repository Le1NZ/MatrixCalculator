package ru.pokrovskii.matrixcalculator.state

import ru.pokrovskii.matrixcalculator.R

internal sealed interface OperationUiState {

    val name: Int

    data object MultiplyNumber : OperationUiState {
        override val name = R.string.type_multiple_number
    }

    data object Multiply : OperationUiState {
        override val name = R.string.type_multiple
    }

    data object Add : OperationUiState {
        override val name = R.string.type_add
    }

    data object Determinant : OperationUiState {
        override val name = R.string.type_determinant
    }
    
    companion object {
        
        internal val OPERATION_VARIANTS = listOf(
            MultiplyNumber,
            Multiply,
            Add,
            Determinant,
        )
    }
}