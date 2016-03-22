package net.devromik.poly

/**
 * @author Shulnyaev Roman
 */
class Polynomial(val coeffs: Array<Double>) {
    val coeffCount: Int = coeffs.size
    fun coeff(i: Int): Double = coeffs[i]
    val degree: Int = coeffCount - 1
}
