package net.devromik.poly

import net.devromik.poly.utils.COMPLEX_ZERO
import net.devromik.poly.utils.Complex
import net.devromik.poly.utils.times
import java.lang.Math.max

/**
 * @author Shulnyaev Roman
 */
class Polynomial(val coeffs: DoubleArray) {

    init {
        require(coeffs.size > 0)
    }

    // ****************************** //

    fun setCoeff(i: Int, c: Double) {
        coeffs[i] = c
    }

    fun coeff(i: Int): Double = coeffs[i]

    val coeffCount: Int get() = coeffs.size
    val degree: Int get() = coeffCount - 1

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

    override fun toString(): String {
        return coeffs.reversed().mapIndexed {
            i, c ->
                when {
                    c.compareTo(0.0) == 0 -> "-"
                    i < degree - 1 -> if (c.compareTo(1.0) == 0) "X^${degree - i}" else "$c * X^${degree - i}"
                    i == degree - 1 -> if (c.compareTo(1.0) == 0) "X" else "$c * X"
                    else -> "$c"
                }
        }.filter { it != "-" }.joinToString(separator = " + ")
    }
}

/* ****** Sum ****** */

operator fun Polynomial.plus(addend: Polynomial): Polynomial {
    val (min, max) = if (coeffCount > addend.coeffCount) addend.to(this) else this.to(addend)

    return Polynomial(DoubleArray(
        max.coeffCount,
        { if (it < min.coeffCount) min.coeff(it) + max.coeff(it) else max.coeff(it) }))
}

operator fun Polynomial.plus(addend: Double): Polynomial {
    val sumCoeffs = coeffs.copyOf()
    sumCoeffs[0] += addend

    return Polynomial(sumCoeffs)
}

operator fun Double.plus(addend: Polynomial): Polynomial {
    return addend + this
}

/* ****** Product ****** */

operator fun Polynomial.times(factor: Double): Polynomial = Polynomial(coeffs * factor)
operator fun Double.times(factor: Polynomial): Polynomial = factor * this

operator fun Polynomial.times(factor: Polynomial): Polynomial {
    val productCoeffs = DoubleArray(degree + factor.degree + 1, { 0.0 })

    for (i in 0..degree) {
        for (j in 0..factor.degree) {
            productCoeffs[i + j] += coeff(i) * factor.coeff(j)
        }
    }

    return Polynomial(productCoeffs)
}

fun Polynomial.multFFT(factor: Polynomial): Polynomial {
    val maxCoeffCount = max(coeffCount, factor.coeffCount);
    var auxiliaryCoeffCount = 1

    while (auxiliaryCoeffCount < maxCoeffCount) {
        if (auxiliaryCoeffCount > Int.MAX_VALUE / 4) {
            throw IllegalArgumentException("Overflow")
        }

        auxiliaryCoeffCount = auxiliaryCoeffCount.shl(1)
    }

    auxiliaryCoeffCount = auxiliaryCoeffCount.shl(1);

    val a = Array(
        auxiliaryCoeffCount,
        { if (it < coeffCount) Complex(coeff(it)) else COMPLEX_ZERO })

    fft(a, false)

    val b = Array(
        auxiliaryCoeffCount,
        { if (it < factor.coeffCount) Complex(factor.coeff(it)) else COMPLEX_ZERO })

    fft(b, false)

    for (i in 0..auxiliaryCoeffCount - 1) {
        a[i] *= b[i]
    }

    fft(a, true)

    return Polynomial(DoubleArray(degree + factor.degree + 1, { a[it].re }))
}