package net.devromik.poly

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
        assertEquals(1.0, p.coeff(0), 1e-15)
        assertEquals(2.0, p.coeff(1), 1e-15)
        assertEquals(3.0, p.coeff(2), 1e-15)
    }

    @Test fun hasDegree() {
        val p = Polynomial(arrayOf(1.0, 2.0, 3.0))
        assertEquals(2, p.degree)
    }
}