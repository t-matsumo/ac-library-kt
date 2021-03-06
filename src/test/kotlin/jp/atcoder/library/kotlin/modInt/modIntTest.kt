package jp.atcoder.library.kotlin.modInt

import org.junit.Assert.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class FenwickTreeTest {
    @Test
    fun modIntTest1e9_7() {
        val factory = ModIntFactory(1e9.toInt() + 7)
        val a = factory.create(107)
        val b = factory.create(109)

        // Simple multiplication.
        assertEquals(factory.create(109 * 107), a * b)

        // Inverse.
        val c = a * a.inv()
        assertEquals(factory.create(1), c)

        // Overflow test.
        assertTrue(a.pow(10000).value > 0)
    }
}

class ModIntFactoryTest {
    private val mod1000000007 = 1_000_000_007
    private lateinit var modFactory1000000007: ModIntFactory

    @Before
    fun setUp() {
        // 今の所immutableですが念の為ここでセット
        modFactory1000000007 = ModIntFactory(mod1000000007)
    }

    @Test
    fun createModIntWithPositiveValue() {
        assertThat(modFactory1000000007.create(2L * mod1000000007 + 5).value)
            .isEqualTo(5)
    }

    @Test
    fun createModIntWithNegativeValue() {
        assertThat(modFactory1000000007.create(-2L * mod1000000007 + 5L).value)
            .isEqualTo(5)
        assertThat(modFactory1000000007.create(-2L * mod1000000007).value)
            .isEqualTo(0)
    }
}

class ModIntTest {
    private val mod1000000007 = 1_000_000_007
    private lateinit var modFactory1000000007: ModIntFactory

    @Before
    fun setUp() {
        modFactory1000000007 = ModIntFactory(mod1000000007)
    }

    @Test
    fun haveMod() {
        assertThat(modFactory1000000007.create(2L * mod1000000007).mod)
            .isEqualTo(mod1000000007)
    }

    @Test
    fun haveValue() {
        assertThat(modFactory1000000007.create(2L * mod1000000007 + 5).value)
            .isEqualTo(5)
    }

    @Test
    fun plus() {
        val a = modFactory1000000007.create(2L * mod1000000007 + 5)
        val b = modFactory1000000007.create(2L * mod1000000007 + 10)
        assertThat((a + b).value)
            .isEqualTo(15)
    }

    @Test
    fun minus() {
        val a = modFactory1000000007.create(2L * mod1000000007 + 10)
        val b = modFactory1000000007.create(2L * mod1000000007 + 5)
        assertThat((a - b).value)
            .isEqualTo(5)
    }

    @Test
    fun times() {
        val a = modFactory1000000007.create(2L * mod1000000007 + 10)
        val b = modFactory1000000007.create(2L * mod1000000007 + 5)
        assertThat((a * b).value)
            .isEqualTo(50)
    }

    @Test
    fun div() {
        val a = modFactory1000000007.create(2L * mod1000000007 + 10)
        assertThat((a / a).value)
            .isEqualTo(1)
    }

    @Test
    fun inv() {
        val a = modFactory1000000007.create(2L * mod1000000007 + 10)
        assertThat((a * a.inv()).value)
            .isEqualTo(1)
    }

    @Test
    fun pow() {
        val a = modFactory1000000007.create(2L * mod1000000007 + 2)
        assertThat(a.pow(10).value)
            .isEqualTo(1024)
    }

    @Test
    fun addAsg() {
        val a = modFactory1000000007.create(2L * mod1000000007 + 10)
        val b = modFactory1000000007.create(2L * mod1000000007 + 5)
        a.addAsg(b)
        assertThat(a.value)
            .isEqualTo(15)
    }

    @Test
    fun subAsg() {
        val a = modFactory1000000007.create(2L * mod1000000007 + 10)
        val b = modFactory1000000007.create(2L * mod1000000007 + 5)
        a.subAsg(b)
        assertThat(a.value)
            .isEqualTo(5)
    }

    @Test
    fun mulAsg() {
        val a = modFactory1000000007.create(2L * mod1000000007 + 10)
        val b = modFactory1000000007.create(2L * mod1000000007 + 5)
        a.mulAsg(b)
        assertThat(a.value)
            .isEqualTo(50)
    }

    @Test
    fun divAsg() {
        val a = modFactory1000000007.create(2L * mod1000000007 + 10)
        a.divAsg(a)
        assertThat(a.value)
            .isEqualTo(1)
    }
}
