package Exer1_4

import kotlin.math.sqrt

/**
 * Classe que representa um vetor 2D com componentes x e y.
 * Suporta operações matemáticas como adição, subtração, multiplicação e comparação.
 * @param x componente horizontal do vetor
 * @param y componente vertical do vetor
 */
data class Vec2(val x: Double, val y: Double) : Comparable<Vec2> {

    /**
     * Soma dois vetores, componente a componente.
     * @param other vetor a somar
     * @return novo Vec2 com a soma dos componentes
     */
    operator fun plus(other: Vec2): Vec2 {
        return Vec2(x + other.x, y + other.y)
    }

    /**
     * Subtrai dois vetores componente a componente.
     * @param other o vetor a subtrair
     * @return novo Vec2 com a subtração dos componentes
     */
    operator fun minus(other: Vec2): Vec2 {
        return Vec2(x - other.x, y - other.y)
    }

    /**
     * Multiplica o vetor por um escalar.
     * @param scalar o valor pelo qual multiplicar
     * @return novo Vec2 com os componentes escalados
     */
    operator fun times(scalar: Double): Vec2 {
        return Vec2(x * scalar, y * scalar)
    }

    /**
     * Nega o vetor, invertendo o sinal de ambos os componentes.
     * @return novo Vec2 com os componentes negados
     */
    operator fun unaryMinus(): Vec2 {
        return Vec2(-x, -y)
    }

    /**
     * Compara dois vetores pela sua magnitude (comprimento).
     * @param other o vetor a comparar
     * @return negativo se this < other, 0 se iguais, positivo se this > other
     */
    override operator fun compareTo(other: Vec2): Int {
        return magnitude().compareTo(other.magnitude())
    }

    /**
     * Acede aos componentes do vetor por índice.
     * @param index 0 para x, 1 para y
     * @return o componente correspondente ao índice
     * @throws IndexOutOfBoundsException se o índice não for 0 ou 1
     */
    operator fun get(index: Int): Double {
        return when (index) {
            0 -> x
            1 -> y
            else -> throw IndexOutOfBoundsException()
        }
    }

    /**
     * Calcula a magnitude (comprimento) do vetor.
     * @return √(x² + y²)
     */
    fun magnitude(): Double {
        return sqrt(x * x + y * y)
    }

    /**
     * Calcula o produto escalar entre dois vetores.
     * @param other o outro vetor
     * @return x1*x2 + y1*y2
     */
    fun dot(other: Vec2): Double {
        return x * other.x + y * other.y
    }

    /**
     * Devolve o vetor normalizado (vetor unitário na mesma direção).
     * @return novo Vec2 com magnitude 1.0
     * @throws IllegalStateException se o vetor for o vetor nulo (magnitude == 0)
     */
    fun normalized(): Vec2 {
        if (magnitude() == 0.0) throw IllegalStateException("Cannot normalize a zero vector")
        return Vec2(x / magnitude(), y / magnitude())
    }
}

/**
 * Multiplica o escalar por um vetor
 * @param vec vetor pelo qual multiplicar
 * @return novo Vec2 com os componentes escalados
 */
operator fun Double.times(vec: Vec2): Vec2 {
    return Vec2(this * vec.x, this * vec.y )
}