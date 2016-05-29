package net.devromik.poly

/**
 * @author Shulnyaev Roman
 */
object X {
    infix fun pow(degree: Int): Polynomial = Polynomial(DoubleArray(degree + 1, { if (it < degree) 0.0 else 1.0 }))
}

operator fun X.times(factor: Double): Polynomial = pow(1) * factor
operator fun Double.times(factor: X): Polynomial = factor * this