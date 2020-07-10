package com.pal.marsroverandroid.domain

sealed class Direction(var value: Char) {
    object N : Direction('N')
    object E : Direction('E')
    object S : Direction('S')
    object W : Direction('W')
    object Unknown : Direction('?')
}