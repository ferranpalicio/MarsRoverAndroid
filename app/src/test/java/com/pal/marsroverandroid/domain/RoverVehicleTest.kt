package com.pal.marsroverandroid.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RoverVehicleTest {

    private val roverVehicle = RoverVehicle()

    @Test
    fun `perform action`() {
        assert(roverVehicle.performAction('L') == VehicleAction.RotateLeft)
        assert(roverVehicle.performAction('R') == VehicleAction.RotateRight)
        assert(roverVehicle.performAction('M') == VehicleAction.Move)
        assert(roverVehicle.performAction('x') == VehicleAction.Unknown)
    }

    @Test
    fun `update direction`() {
        assert(roverVehicle.updateDirection('N') == Direction.N)
        assert(roverVehicle.updateDirection('W') == Direction.W)
        assert(roverVehicle.updateDirection('S') == Direction.S)
        assert(roverVehicle.updateDirection('E') == Direction.E)
        assert(roverVehicle.updateDirection('x') == Direction.Unknown)
    }

    //todo test updatePosition (parametrized)
}