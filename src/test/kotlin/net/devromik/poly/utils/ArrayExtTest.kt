package net.devromik.poly.utils

import net.devromik.poly.utils.MathConstant.EPSILON
import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * @author Shulnyaev Roman
 */
class ArrayExtTest {

    @Test fun doubleArrayCanBeScaled() {
        assertArrayEquals(
            doubleArrayOf(1.0, 2.0, 3.0) * 2.0,
            doubleArrayOf(2.0, 4.0, 6.0),
            EPSILON)
    }
}