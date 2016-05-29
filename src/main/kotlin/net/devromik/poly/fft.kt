package net.devromik.poly

import net.devromik.poly.utils.*
import java.lang.Integer.numberOfLeadingZeros
import java.lang.Integer.reverse

/**
 * @author Shulnyaev Roman
 */
private val PRINCIPAL_ROOTS_OF_UNITY_FOR_DIRECT_FFT = arrayOf(
    Complex(re=-1.0, im=1.2246467991473532E-16),
    Complex(re=6.123233995736766E-17, im=1.0),
    Complex(re=0.7071067811865476, im=0.7071067811865475),
    Complex(re=0.9238795325112867, im=0.3826834323650898),
    Complex(re=0.9807852804032304, im=0.19509032201612825),
    Complex(re=0.9951847266721969, im=0.0980171403295606),
    Complex(re=0.9987954562051724, im=0.049067674327418015),
    Complex(re=0.9996988186962042, im=0.024541228522912288),
    Complex(re=0.9999247018391445, im=0.012271538285719925),
    Complex(re=0.9999811752826011, im=0.006135884649154475),
    Complex(re=0.9999952938095762, im=0.003067956762965976),
    Complex(re=0.9999988234517019, im=0.0015339801862847655),
    Complex(re=0.9999997058628822, im=7.669903187427045E-4),
    Complex(re=0.9999999264657179, im=3.8349518757139556E-4),
    Complex(re=0.9999999816164293, im=1.917475973107033E-4),
    Complex(re=0.9999999954041073, im=9.587379909597734E-5),
    Complex(re=0.9999999988510269, im=4.793689960306688E-5),
    Complex(re=0.9999999997127567, im=2.396844980841822E-5),
    Complex(re=0.9999999999281892, im=1.1984224905069705E-5),
    Complex(re=0.9999999999820472, im=5.9921124526424275E-6),
    Complex(re=0.9999999999955118, im=2.996056226334661E-6),
    Complex(re=0.999999999998878, im=1.4980281131690111E-6),
    Complex(re=0.9999999999997194, im=7.490140565847157E-7),
    Complex(re=0.9999999999999298, im=3.7450702829238413E-7),
    Complex(re=0.9999999999999825, im=1.8725351414619535E-7),
    Complex(re=0.9999999999999957, im=9.362675707309808E-8),
    Complex(re=0.9999999999999989, im=4.681337853654909E-8),
    Complex(re=0.9999999999999998, im=2.340668926827455E-8),
    Complex(re=0.9999999999999999, im=1.1703344634137277E-8)
)

private val PRINCIPAL_ROOTS_OF_UNITY_FOR_INVERSE_FFT = arrayOf(
    Complex(re=-1.0, im=-1.2246467991473532E-16),
    Complex(re=6.123233995736766E-17, im=-1.0),
    Complex(re=0.7071067811865476, im=-0.7071067811865475),
    Complex(re=0.9238795325112867, im=-0.3826834323650898),
    Complex(re=0.9807852804032304, im=-0.19509032201612825),
    Complex(re=0.9951847266721969, im=-0.0980171403295606),
    Complex(re=0.9987954562051724, im=-0.049067674327418015),
    Complex(re=0.9996988186962042, im=-0.024541228522912288),
    Complex(re=0.9999247018391445, im=-0.012271538285719925),
    Complex(re=0.9999811752826011, im=-0.006135884649154475),
    Complex(re=0.9999952938095762, im=-0.003067956762965976),
    Complex(re=0.9999988234517019, im=-0.0015339801862847655),
    Complex(re=0.9999997058628822, im=-7.669903187427045E-4),
    Complex(re=0.9999999264657179, im=-3.8349518757139556E-4),
    Complex(re=0.9999999816164293, im=-1.917475973107033E-4),
    Complex(re=0.9999999954041073, im=-9.587379909597734E-5),
    Complex(re=0.9999999988510269, im=-4.793689960306688E-5),
    Complex(re=0.9999999997127567, im=-2.396844980841822E-5),
    Complex(re=0.9999999999281892, im=-1.1984224905069705E-5),
    Complex(re=0.9999999999820472, im=-5.9921124526424275E-6),
    Complex(re=0.9999999999955118, im=-2.996056226334661E-6),
    Complex(re=0.999999999998878, im=-1.4980281131690111E-6),
    Complex(re=0.9999999999997194, im=-7.490140565847157E-7),
    Complex(re=0.9999999999999298, im=-3.7450702829238413E-7),
    Complex(re=0.9999999999999825, im=-1.8725351414619535E-7),
    Complex(re=0.9999999999999957, im=-9.362675707309808E-8),
    Complex(re=0.9999999999999989, im=-4.681337853654909E-8),
    Complex(re=0.9999999999999998, im=-2.340668926827455E-8),
    Complex(re=0.9999999999999999, im=-1.1703344634137277E-8)
)

fun fft(a: Array<Complex>, inverse: Boolean) {
    doBitReversalPermutationOf(a)

    val principalRootsOfUnity = if (inverse) PRINCIPAL_ROOTS_OF_UNITY_FOR_INVERSE_FFT else PRINCIPAL_ROOTS_OF_UNITY_FOR_DIRECT_FFT

    val n = a.size
    var r = 2
    var principalRootOfUnityIndex = 0

    while (r <= n) {
        val principalRootOfUnity = principalRootsOfUnity[principalRootOfUnityIndex]

        for (i in 0..n - 1 step r) {
            var w = COMPLEX_UNITY

            for (j in 0..r / 2 - 1) {
                val u = a[i + j]
                val v = a[i + j + r / 2] * w

                a[i + j] = u + v
                a[i + j + r / 2] = u - v

                w *= principalRootOfUnity
            }
        }

        r = r.shl(1)
        ++principalRootOfUnityIndex
    }

    if (inverse) {
        for (i in 0..n - 1) {
            a[i] = a[i] / n
        }
    }
}

fun doBitReversalPermutationOf(a: Array<Complex>) {
    val n = a.size
    val shift = numberOfLeadingZeros(n) + 1

    for (i in 0..n - 1) {
        val p = reverse(i).ushr(shift)

        if (i < p) {
            val temp = a[i]
            a[i] = a[p]
            a[p] = temp
        }
    }
}