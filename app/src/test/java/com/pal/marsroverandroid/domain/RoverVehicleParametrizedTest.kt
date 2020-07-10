package com.pal.marsroverandroid.domain

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class RoverVehicleParametrizedTest(
    private val directionInput: Direction,
    private val positionInput: Coordinate,
    private val actionInput: VehicleAction,
    private val plateuDimInput: Coordinate,
    private val outputResult: Pair<Direction, Coordinate>
) {

    private val roverVehicle = RoverVehicle()

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Any>> {

            val initialPosition = Coordinate(0, 0)
            val plateuDim = Coordinate(5,  5)

            return listOf(
                //action: move N
                arrayOf(
                    Direction.N,
                    initialPosition,
                    VehicleAction.Move,
                    plateuDim,
                    Pair(Direction.N, Coordinate(0, 1))
                ),
                //action: move W
                arrayOf(
                    Direction.W,
                    Coordinate(1, 1),
                    VehicleAction.Move,
                    plateuDim,
                    Pair(Direction.W, Coordinate(0, 1))
                ),
                //action: move S
                arrayOf(
                    Direction.S,
                    Coordinate(1, 1),
                    VehicleAction.Move,
                    plateuDim,
                    Pair(Direction.S, Coordinate(1, 0))
                ),
                //action: move E
                arrayOf(
                    Direction.E,
                    Coordinate(1, 1),
                    VehicleAction.Move,
                    plateuDim,
                    Pair(Direction.E, Coordinate(2, 1))
                ),
                //action: rotate right
                arrayOf(
                    Direction.N,
                    initialPosition,
                    VehicleAction.RotateRight,
                    plateuDim,
                    Pair(Direction.E, initialPosition)
                ),
                //action: rotate left
                arrayOf(
                    Direction.N,
                    initialPosition,
                    VehicleAction.RotateLeft,
                    plateuDim,
                    Pair(Direction.W, initialPosition)
                ),
                //unknown action (stays same position & direction)
                arrayOf(
                    Direction.N,
                    initialPosition,
                    VehicleAction.Unknown,
                    plateuDim,
                    Pair(Direction.N, initialPosition)
                ),
                //unknown direction (stays same position & direction)
                arrayOf(
                    Direction.Unknown,
                    initialPosition,
                    VehicleAction.Move,
                    plateuDim,
                    Pair(Direction.Unknown, initialPosition)
                ),
                //move out of bounds (stays same position & direction)
                arrayOf(
                    Direction.N,
                    plateuDim,
                    VehicleAction.Move,
                    plateuDim,
                    Pair(Direction.N, plateuDim)
                )
            )
        }
    }

    @Test
    fun `should update position given input`() {
        assertEquals(
            roverVehicle.updatePosition(
                directionInput,
                positionInput,
                actionInput,
                plateuDimInput
            ), outputResult
        )
    }
}