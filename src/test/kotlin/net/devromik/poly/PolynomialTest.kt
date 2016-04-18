package net.devromik.poly

import net.devromik.poly.utils.EPSILON
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Shulnyaev Roman
 */
class PolynomialTest {

    @Test fun hasCoefficients() {
        val p = Polynomial(arrayOf(1.0, 2.0, 3.0))

        assertArrayEquals(arrayOf(1.0, 2.0, 3.0), p.coeffs)
        assertEquals(p.coeffCount, 3)
        assertEquals(1.0, p.coeff(0), Double.EPSILON)
        assertEquals(2.0, p.coeff(1), Double.EPSILON)
        assertEquals(3.0, p.coeff(2), Double.EPSILON)
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

        assertEquals(1.0 + 2.0 * (-2.0) + 3.0 * (-2.0 * -2.0), p.at(-2.0), Double.EPSILON)
        assertEquals(1.0 + 2.0 * (-1.0) + 3.0 * (-1.0 * -1.0), p.at(-1.0), Double.EPSILON)
        assertEquals(1.0, p.at(0.0), Double.EPSILON)
        assertEquals(1.0 + 2.0 * (1.0) + 3.0 * (1.0 * 1.0), p.at(1.0), Double.EPSILON)
        assertEquals(1.0 + 2.0 * (2.0) + 3.0 * (2.0 * 2.0), p.at(2.0), Double.EPSILON)

        p = Polynomial(arrayOf(-1.0, 2.0, -3.0))

        assertEquals(-1.0 + 2.0 * (-2.0) - 3.0 * (-2.0 * -2.0), p.at(-2.0), Double.EPSILON)
        assertEquals(-1.0 + 2.0 * (-1.0) - 3.0 * (-1.0 * -1.0), p.at(-1.0), Double.EPSILON)
        assertEquals(-1.0, p.at(0.0), Double.EPSILON)
        assertEquals(-1.0 + 2.0 * (1.0) - 3.0 * (1.0 * 1.0), p.at(1.0), Double.EPSILON)
        assertEquals(-1.0 + 2.0 * (2.0) - 3.0 * (2.0 * 2.0), p.at(2.0), Double.EPSILON)

        p = Polynomial(arrayOf(7.0))
        assertEquals(7.0, p.at(-1.0), Double.EPSILON)
        assertEquals(7.0, p.at(1.0), Double.EPSILON)
    }

    @Test fun canBeBuiltDeclaratively() {
        var p = X pow 2
        assertArrayEquals(arrayOf(0.0, 0.0, 1.0), p.coeffs)

        p = 3.0 * (X pow 2)
        assertArrayEquals(arrayOf(0.0, 0.0, 3.0), p.coeffs)

        p = 2.0 * X
        assertArrayEquals(arrayOf(0.0, 2.0), p.coeffs)

        p = (X pow 2) * 3.0 + 2.0 * X
        assertArrayEquals(arrayOf(0.0, 2.0, 3.0), p.coeffs)

        p = 3.0 * (X pow 2) + X * 2.0 + 1.0
        assertArrayEquals(arrayOf(1.0, 2.0, 3.0), p.coeffs)
    }

    @Test fun twoPolynomialsCanBeMultipliedByDistributingAndCombiningTheirTerms() {
        var a = Polynomial(arrayOf(1.0));
        var b = Polynomial(arrayOf(2.0));
        var product = a * b
        var expectedProduct = Polynomial(arrayOf(2.0))

        assertArrayEquals(expectedProduct.coeffs, product.coeffs)

        a = -357.5 * (X pow 5) + 15.5 * (X pow 3)
        b = -2.0 * (X pow 2) + -5.0 * X + 7.0
        product = a * b
        expectedProduct = 715.0 * (X pow 7) + 1787.5 * (X pow 6) + -2533.5 * (X pow 5) + -77.5 * (X pow 4) + 108.5 * (X pow 3)

        assertArrayEquals(expectedProduct.coeffs, product.coeffs)
    }
}