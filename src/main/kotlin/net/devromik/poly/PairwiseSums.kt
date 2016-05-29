package net.devromik.poly

import net.devromik.poly.utils.MathConstants.EPSILON
import java.lang.Math.floor
import java.util.*

/**
 * Calculates pairwise sums of two non-negative integer arrays.
 *
 * @author Shulnyaev Roman
 */
fun pairwiseSumsOfNonNegativeArrays(a: IntArray, b: IntArray): IntArray {
    if (a.isEmpty() || b.isEmpty()) {
        return intArrayOf()
    }

    val maxA = a.max()!!
    val polyA = X pow maxA
    a.forEach { polyA.setCoeff(it, 1.0) }

    val maxB = b.max()!!
    val polyB = X pow maxB
    b.forEach { polyB.setCoeff(it, 1.0) }

    val productAB = polyA.multFFT(polyB)

    val s = ArrayList<Int>()
    productAB.coeffs.forEachIndexed() { i, c -> if (floor(c + 0.5) > EPSILON) s.add(i) }

    return s.toIntArray()
}