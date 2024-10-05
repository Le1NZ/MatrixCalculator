package ru.pokrovskii.matrixcalculator.data

class AddRepository {

    fun addMatrix(matrix: List<List<Float>>, secondMatrix: List<List<Float>>): List<List<Float>> {
        return matrix.mapIndexed { indexX, row ->
            row.mapIndexed { indexY, num ->
                num + secondMatrix[indexX][indexY]
            }
        }
    }
}