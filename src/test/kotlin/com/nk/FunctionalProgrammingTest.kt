package com.nk

import org.assertj.core.api.Assertions
import org.junit.Assert.*
import org.junit.Test

class FunctionalProgrammingTest {

    @Test
    fun should_test(){
        // Given
        val films = listOf(Film("Matrix"), Film("Casa De Papel"))

        // When
        val findByTitleresult = FunctionalProgramming().findByTitle("Matrix", films)

        // Then
        Assertions.assertThat(findByTitleresult).hasSize(1)
    }

}