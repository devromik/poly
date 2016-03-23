package net.devromik.poly

/**
 * @author Shulnyaev Roman
 */
object X {
    infix fun pow(n: Int): Polynomial = Polynomial(Array(n + 1, { if (it < n) 0.0 else 1.0 }))
}

operator fun X.times(a: Double): Polynomial = pow(1) * a
operator fun Double.times(x: X): Polynomial = x * this