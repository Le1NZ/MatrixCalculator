package ru.pokrovskii.matrixcalculator.data

class MultiplyRepository {

    fun multiplyMatrix(matrix: List<List<Float>>, secondMatrix: List<List<Float>>): List<List<Float>> {
        val row1 = matrix.size
        val col1 = matrix[0].size
        val col2 = secondMatrix[0].size
        val product = MutableList(row1) { MutableList(col2) { 0f } }

        for (i in 0 until row1) {
            for (j in 0 until col2) {
                for (k in 0 until col1) {
                    product[i][j] += matrix[i][k] * secondMatrix[k][j]
                }
            }
        }

        return product
    }
}