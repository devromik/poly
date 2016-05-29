package net.devromik.poly.utils

/**
 * @author Shulnyaev Roman
 */
operator fun DoubleArray.times(scaleFactor: Double): DoubleArray {
    return DoubleArray(size, { this[it] * scaleFactor })
}