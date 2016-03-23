package net.devromik.poly

/**
 * @author Shulnyaev Roman
 */
class Polynomial(val coeffs: Array<Double>) {

    init {
        require(coeffs.size > 0)
    }

    val coeffCount: Int = coeffs.size
    fun coeff(i: Int): Double = coeffs[i]
    val degree: Int = coeffCount - 1

    fun at(x: Double): Double {
        if (coeffCount == 1) {
            return coeff(0)
        }

        var r = coeff(coeffCount - 1)

        for (i in coeffCount - 2 downTo 0) {
            r = r * x + coeff(i)
        }

        return r
    }
}
