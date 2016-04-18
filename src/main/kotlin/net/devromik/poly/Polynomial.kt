package net.devromik.poly

import net.devromik.poly.utils.times

/**
 * @author Shulnyaev Roman
 */
class Polynomial(val coeffs: Array<Double>) {

    init {
        require(coeffs.size > 0)
    }

    // ****************************** //

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

/* ****** Sum ****** */

operator fun Polynomial.plus(that: Polynomial): Polynomial {
    val (min, max) = if (coeffCount > that.coeffCount) that.to(this) else this.to(that)

    return Polynomial(Array(
        max.coeffCount,
        { if (it < min.coeffCount) min.coeff(it) + max.coeff(it) else max.coeff(it) }))
}

operator fun Polynomial.plus(a: Double): Polynomial {
    val sumCoeffs = coeffs.copyOf()
    sumCoeffs[0] += a

    return Polynomial(sumCoeffs)
}

operator fun Double.plus(p: Polynomial): Polynomial {
    return p.plus(this)
}

/* ****** Product ****** */

operator fun Polynomial.times(a: Double): Polynomial = Polynomial(coeffs * a)
operator fun Double.times(p: Polynomial): Polynomial = p * this

operator fun Polynomial.times(that: Polynomial): Polynomial {
    var productCoeffs = Array(this.degree + that.degree + 1, { 0.0 })

    for (i in 0..this.degree) {
        for (j in 0..that.degree) {
            productCoeffs[i + j] += this.coeff(i) * that.coeff(j)
        }
    }

    return Polynomial(productCoeffs)
}
