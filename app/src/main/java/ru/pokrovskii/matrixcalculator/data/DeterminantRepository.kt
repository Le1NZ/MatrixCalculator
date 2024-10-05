package ru.pokrovskii.matrixcalculator.data

internal class DeterminantRepository {

    fun determinantOfMatrix(
        matrix: List<List<Float>>,
    ): Float {
        return determinantOfMatrix(
            matrix = matrix.map { it.toMutableList() }.toMutableList(),
            size = matrix.size,
        )
    }

    private fun determinantOfMatrix(
        matrix: MutableList<MutableList<Float>>,
        size: Int,
    ): Float {
        var d = 0f
        if (size == 1) return matrix[0][0]
        val temp = MutableList(size) { MutableList(size) { 0f } }
        var sign = 1


        for (f in 0 until size) {
            getCofactor(matrix, temp, 0, f, size)
            d += (sign * matrix[0][f] * determinantOfMatrix(temp, size - 1))
            sign = -sign
        }

        return d
    }

    private fun getCofactor(
        mat: MutableList<MutableList<Float>>,
        temp: MutableList<MutableList<Float>>,
        p: Int,
        q: Int,
        n: Int,
    ) {
        var i = 0
        var j = 0


        for (row in 0 until n) {
            for (col in 0 until n) {
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col]
                    if (j == n - 1) {
                        j = 0
                        i++
                    }
                }
            }
        }
    }
}