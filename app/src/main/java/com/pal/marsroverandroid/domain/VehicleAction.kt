package com.pal.marsroverandroid.domain

sealed class VehicleAction(var value: Char) {
    object RotateLeft : VehicleAction('L')
    object RotateRight : VehicleAction('R')
    object Move : VehicleAction('M')
    object Unknown : VehicleAction('?')
}