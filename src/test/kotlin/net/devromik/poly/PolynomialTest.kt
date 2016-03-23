package net.devromik.poly

import org.junit.Assert.*
import org.junit.Test

/**
 * @author Shulnyaev Roman
 */
class PolynomialTest {

    companion object {
        val EPSILON = 1e-10
    }

    @Test fun hasCoefficients() {
        val p = Polynomial(arrayOf(1.0, 2.0, 3.0))

        assertArrayEquals(arrayOf(1.0, 2.0, 3.0), p.coeffs)
        assertEquals(p.coeffCount, 3)
        assertEquals(1.0, p.coeff(0), EPSILON)
        assertEquals(2.0, p.coeff(1), EPSILON)
        assertEquals(3.0, p.coeff(2), EPSILON)
    }

    @Test(expected = IllegalArgumentException::class)
    fun hasAtLeastOneCoefficient() {
        Polynomial(arrayOf())
    }

    @Test fun hasDegree() {
        val p = Polynomial(arrayOf(1.0, 2.0, 3.0))
        assertEquals(2, p.degree)
    }

    @Test fun canBeEvaluated() {
        var p = Polynomial(arrayOf(1.0, 2.0, 3.0))

        assertEquals(1.0 + 2.0 * (-2.0) + 3.0 * (-2.0 * -2.0), p.at(-2.0), EPSILON)
        assertEquals(1.0 + 2.0 * (-1.0) + 3.0 * (-1.0 * -1.0), p.at(-1.0), EPSILON)
        assertEquals(1.0, p.at(0.0), EPSILON)
        assertEquals(1.0 + 2.0 * (1.0) + 3.0 * (1.0 * 1.0), p.at(1.0), EPSILON)
        assertEquals(1.0 + 2.0 * (2.0) + 3.0 * (2.0 * 2.0), p.at(2.0), EPSILON)

        p = Polynomial(arrayOf(-1.0, 2.0, -3.0))

        assertEquals(-1.0 + 2.0 * (-2.0) - 3.0 * (-2.0 * -2.0), p.at(-2.0), EPSILON)
        assertEquals(-1.0 + 2.0 * (-1.0) - 3.0 * (-1.0 * -1.0), p.at(-1.0), EPSILON)
        assertEquals(-1.0, p.at(0.0), EPSILON)
        assertEquals(-1.0 + 2.0 * (1.0) - 3.0 * (1.0 * 1.0), p.at(1.0), EPSILON)
        assertEquals(-1.0 + 2.0 * (2.0) - 3.0 * (2.0 * 2.0), p.at(2.0), EPSILON)

        p = Polynomial(arrayOf(7.0))
        assertEquals(7.0, p.at(-1.0), EPSILON)
        assertEquals(7.0, p.at(1.0), EPSILON)
    }
}