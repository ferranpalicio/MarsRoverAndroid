package com.pal.marsroverandroid.data

import com.pal.marsroverandroid.domain.RoverData

interface DataReader {
    fun parseFile(file: String): RoverData?
}