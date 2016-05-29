package net.devromik.poly.utils

import org.junit.Assert.*
import org.junit.Test

/**
 * @author Shulnyaev Roman
 */
class ComplexTest {

    @Test fun twoComplexCanBeAdded() {
        assertEquals(Complex(4.0, 6.0), Complex(1.0, 2.0) + Complex(3.0, 4.0))
        assertEquals(Complex(-3.0, 8.0), Complex(-1.0, 3.0) + Complex(-2.0, 5.0))
        assertEquals(Complex(8.0, -8.0), Complex(1.0, -3.0) + Complex(7.0, -5.0))
        assertEquals(Complex(-4.0, -2.0), Complex(1.0, 3.0) + Complex(-5.0, -5.0))
    }

    @Test fun oneComplexCanBeSubtractedFromAnother() {
        assertEquals(Complex(-2.0, -2.0), Complex(1.0, 2.0) - Complex(3.0, 4.0))
        assertEquals(Complex(1.0, -2.0), Complex(-1.0, 3.0) - Complex(-2.0, 5.0))
        assertEquals(Complex(-6.0, 2.0), Complex(1.0, -3.0) - Complex(7.0, -5.0))
        assertEquals(Complex(6.0, 8.0), Complex(1.0, 3.0) - Complex(-5.0, -5.0))
    }

    @Test fun twoComplexCanBeMultiplied() {
        assertEquals(Complex(-183.0, 135.0), Complex(3.0, 12.0) * Complex(7.0, 17.0))
        assertEquals(Complex(-22104.0, -1406.0), Complex(-3.0, 125.0) * Complex(-7.0, 177.0))
    }

    @Test fun bothComponentsCanBeDividedIntoInt() {
        assertEquals(Complex(2.0, 4.0), Complex(10.0, 20.0) / 5)

        var quotient = Complex(5.5, 7.5)
        quotient /= -2

        assertEquals(Complex(-2.75, -3.75), quotient)
    }
}