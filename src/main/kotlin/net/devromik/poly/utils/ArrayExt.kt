package net.devromik.poly.utils

/**
 * @author Shulnyaev Roman
 */
operator fun Array<Double>.times(a: Double): Array<Double> {
    return Array(size, { this[it] * a })
}