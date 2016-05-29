package net.devromik.poly.utils

/**
 * @author Shulnyaev Roman
 */
data class Complex(val re: Double = 0.0, val im: Double = 0.0)

// ****************************** //

val COMPLEX_ZERO = Complex(0.0)
val COMPLEX_UNITY = Complex(1.0)

// ****************************** //

operator fun Complex.plus(addend: Complex): Complex {
    return Complex(re + addend.re, im + addend.im)
}

operator fun Complex.minus(subtrahend: Complex): Complex {
    return Complex(re - subtrahend.re, im - subtrahend.im)
}

operator fun Complex.times(factor: Complex): Complex {
    return Complex(
        re * factor.re - im * factor.im,
        im * factor.re + re * factor.im)
}

operator fun Complex.div(divisor: Int): Complex {
    return Complex(re / divisor, im / divisor)
}