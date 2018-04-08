package org.scoutant.rpn

/**
 * Improving findViewById with Kotlin
 * https://medium.com/@quiro91/improving-findviewbyid-with-kotlin-4cf2f8f779bb
 * Usage :
 * private val display: TextView by bind(R.id.display)
 */

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
