package net.devromik.poly

import com.carrotsearch.hppc.IntArrayList
import net.devromik.poly.utils.MathConstant.EPSILON
import java.lang.Math.floor

/**
 * @author Shulnyaev Roman
 */
fun pairwiseSumOfNonNegativeArrays(a1: IntArrayList, a2: IntArrayList): IntArrayList {
    if (a1.isEmpty || a2.isEmpty) {
        return IntArrayList()
    }

    fun polyOf(a: IntArrayList): Polynomial {
        var max = -1

        a.forEach {
            val v = it.value

            if (v < 0) throw IllegalArgumentException("Negative: " + v)
            if (v > max) max = v
        }

        val poly = X pow max
        a.forEach { poly.setCoeff(it.value, 1.0) }

        return poly
    }

    val product = polyOf(a1).multFFT(polyOf(a2))

    val s = IntArrayList()
    product.coeffs.forEachIndexed() { i, c -> if (floor(c + 0.5) > EPSILON) s.add(i) }

    return s
}