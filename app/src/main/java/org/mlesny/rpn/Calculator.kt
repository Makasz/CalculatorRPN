package org.mlesny.rpn
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class Calculator() {
    val stack: Stack<BigDecimal> = Stack()
    private val INTERNAL_SCALE = 32

    constructor (cache: String) : this() {
        for (item in cache.split(";")) {
            if (item.isEmpty()) continue
            stack.push(BigDecimal(item))
        }
    }

    fun push(value: BigDecimal) {
        stack.push(value)
    }

    fun stack(): Stack<BigDecimal>? {
        if (stack.isEmpty()) return null
        return stack
    }

    fun push(value: String) = push(BigDecimal(value))
    fun isEmpty(): Boolean = stack.isEmpty()
    fun negate() {
        if (isEmpty()) return
        stack.push(stack.pop().negate())
    }

    fun peek(): BigDecimal = stack.peek()
    fun pop(): BigDecimal = stack.pop()
    fun drop() = stack()?.pop()
    fun size(): Int = stack.size
    fun small(): Boolean = size() <= 1

    fun swap() {
        if (small()) return
        val x = pop()
        val y = pop()
        push(x)
        push(y)
    }

    fun add() {
        if (small()) return
        push(pop().add(pop()))
    }

    fun subtract() {
        if (small()) return
        val x = pop()
        push(pop().subtract(x))
    }

    fun multiply() {
        if (small()) return
        val x = pop()
        push(pop().multiply(x))
    }

    fun divide() {
        if (small()) return
        if (BigDecimal.ZERO == peek()) throw ArithmeticException("Division by zero")
        val x = pop()
        push(pop().divide(x, INTERNAL_SCALE, RoundingMode.HALF_EVEN))
    }

    fun power() {
        if (small()) return
        val y = pop()
        val x = pop()
        val yi = y.intValueExact()
        push(x.pow(yi))

    }

    fun sqrt() {
        if (stack.isEmpty()) return
        if (peek().toDouble() < 0) throw ArithmeticException("Cant sqrt negative number!")
        push(BigDecimalUtils.sqrt(pop(), INTERNAL_SCALE))
    }

    fun reciprocal() {
        if (stack.isEmpty()) return
        if (BigDecimal.ZERO == peek()) throw ArithmeticException("Division by 0!")
        push(BigDecimal.ONE.divide(pop(), INTERNAL_SCALE, RoundingMode.HALF_EVEN))
    }

    override fun toString(): String {
        var msg = ""
        for (value in stack.elements()) {
            msg += value
            msg += ";"
        }
        return msg
    }


}