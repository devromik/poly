package net.devromik.poly

import net.devromik.poly.utils.MathConstants.EPSILON
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Shulnyaev Roman
 */
class PolynomialTest {

    @Test fun hasCoefficients() {
        val p = Polynomial(doubleArrayOf(1.0, 2.0, 3.0))

        assertArrayEquals(doubleArrayOf(1.0, 2.0, 3.0), p.coeffs, EPSILON)
        assertEquals(p.coeffCount, 3)
        assertEquals(1.0, p.coeff(0), EPSILON)
        assertEquals(2.0, p.coeff(1), EPSILON)
        assertEquals(3.0, p.coeff(2), EPSILON)
    }

    @Test(expected = IllegalArgumentException::class)
    fun hasAtLeastOneCoefficient() {
        Polynomial(doubleArrayOf())
    }

    @Test fun hasDegree() {
        val p = Polynomial(doubleArrayOf(1.0, 2.0, 3.0))
        assertEquals(2, p.degree)
    }

    @Test fun canBeEvaluated() {
        var p = Polynomial(doubleArrayOf(1.0, 2.0, 3.0))

        assertEquals(1.0 + 2.0 * (-2.0) + 3.0 * (-2.0 * -2.0), p.at(-2.0), EPSILON)
        assertEquals(1.0 + 2.0 * (-1.0) + 3.0 * (-1.0 * -1.0), p.at(-1.0), EPSILON)
        assertEquals(1.0, p.at(0.0), EPSILON)
        assertEquals(1.0 + 2.0 * (1.0) + 3.0 * (1.0 * 1.0), p.at(1.0), EPSILON)
        assertEquals(1.0 + 2.0 * (2.0) + 3.0 * (2.0 * 2.0), p.at(2.0), EPSILON)

        p = Polynomial(doubleArrayOf(-1.0, 2.0, -3.0))

        assertEquals(-1.0 + 2.0 * (-2.0) - 3.0 * (-2.0 * -2.0), p.at(-2.0), EPSILON)
        assertEquals(-1.0 + 2.0 * (-1.0) - 3.0 * (-1.0 * -1.0), p.at(-1.0), EPSILON)
        assertEquals(-1.0, p.at(0.0), EPSILON)
        assertEquals(-1.0 + 2.0 * (1.0) - 3.0 * (1.0 * 1.0), p.at(1.0), EPSILON)
        assertEquals(-1.0 + 2.0 * (2.0) - 3.0 * (2.0 * 2.0), p.at(2.0), EPSILON)

        p = Polynomial(doubleArrayOf(7.0))
        assertEquals(7.0, p.at(-1.0), EPSILON)
        assertEquals(7.0, p.at(1.0), EPSILON)
    }

    @Test fun canBeBuiltDeclaratively() {
        var p = X pow 0
        assertArrayEquals(doubleArrayOf(1.0), p.coeffs, EPSILON)

        p = X pow 2
        assertArrayEquals(doubleArrayOf(0.0, 0.0, 1.0), p.coeffs, EPSILON)

        p = 3.0 * (X pow 2)
        assertArrayEquals(doubleArrayOf(0.0, 0.0, 3.0), p.coeffs, EPSILON)

        p = 2.0 * X
        assertArrayEquals(doubleArrayOf(0.0, 2.0), p.coeffs, EPSILON)

        p = (X pow 2) * 3.0 + 2.0 * X
        assertArrayEquals(doubleArrayOf(0.0, 2.0, 3.0), p.coeffs, EPSILON)

        p = 3.0 * (X pow 2) + X * 2.0 + 1.0
        assertArrayEquals(doubleArrayOf(1.0, 2.0, 3.0), p.coeffs, EPSILON)
    }

    @Test fun canBeConvertedToString() {
        var p = X pow 1
        assertEquals("X", p.toString());

        p = (X pow 2) * 3.0 + 2.0 * X
        assertEquals("3.0 * X^2 + 2.0 * X", p.toString());

        p = (X pow 2) + 2.0 * X + 1.0
        assertEquals("X^2 + 2.0 * X + 1.0", p.toString());
    }

    @Test fun twoPolynomialsCanBeMultiplied() {
        var a = Polynomial(doubleArrayOf(1.0));
        var b = Polynomial(doubleArrayOf(2.0));
        var product = a * b
        var expectedProduct = Polynomial(doubleArrayOf(2.0))

        assertEquals(product, expectedProduct)

        a = -357.5 * (X pow 5) + 15.5 * (X pow 3)
        b = -2.0 * (X pow 2) + -5.0 * X + 7.0
        product = a * b
        expectedProduct = 715.0 * (X pow 7) + 1787.5 * (X pow 6) + -2533.5 * (X pow 5) + -77.5 * (X pow 4) + 108.5 * (X pow 3)

        assertEquals(product, expectedProduct)
    }

    @Test fun twoPolynomialsCanBeMultipliedWithFFT() {
        var a = Polynomial(doubleArrayOf(1.0));
        var b = Polynomial(doubleArrayOf(2.0));
        var product = a.multFFT(b)

        assertEquals(product, a * b)

        // ****************************** //

        a = Polynomial(doubleArrayOf(2.5))
        b = 3.0 * X
        product = a.multFFT(b)

        assertEquals(product, a * b)

        // ****************************** //

        a = 3.0 * X
        b = Polynomial(doubleArrayOf(2.5))
        product = a.multFFT(b)

        assertEquals(product, a * b)

        // ****************************** //

        a = -357.5 * (X pow 5) + 15.5 * (X pow 3)
        b = -2.0 * (X pow 2) + -5.0 * X + 7.0
        product = a.multFFT(b)

        assertEquals(product, a * b)

        // ****************************** //

        a = Polynomial(DoubleArray(50, { if (it % 2 == 0) it.toDouble() + 0.2 else 0.0 }))
        b = Polynomial(DoubleArray(50, { if (it % 2 == 1) it.toDouble() - 0.4 else 0.0 }))
        product = a.multFFT(b)

        assertEquals(product, a * b)

        // ****************************** //

        a = Polynomial(DoubleArray(50, { if (it % 3 == 0) it.toDouble() + 0.7 else 0.0 }))
        b = Polynomial(DoubleArray(50, { if (it % 2 == 1) -it.toDouble() - 0.3 else 0.0 }))
        product = a.multFFT(b)

        assertEquals(product, a * b)

        // ****************************** //

        a = Polynomial(DoubleArray(50, { if (it % 5 == 0) -it.toDouble() + 0.8 else 0.0 }))
        b = Polynomial(DoubleArray(50, { if (it % 2 == 1) it.toDouble() - 0.1 else 0.0 }))
        product = a.multFFT(b)

        assertEquals(product, a * b)
    }

    // ****************************** //

    fun assertEquals(a: Polynomial, b: Polynomial) {
        assertArrayEquals(a.coeffs, b.coeffs, EPSILON)
    }
}