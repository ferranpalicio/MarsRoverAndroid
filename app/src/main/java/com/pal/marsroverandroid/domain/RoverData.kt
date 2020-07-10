package com.pal.marsroverandroid.domain

data class RoverData(
    val movements: String,
    val roverDirection: Char,
    val roverPosition: Coordinate,
    val topRightCorner: Coordinate
)