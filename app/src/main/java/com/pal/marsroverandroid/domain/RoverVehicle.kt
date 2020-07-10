package com.pal.marsroverandroid.domain

class RoverVehicle {
    fun performAction(action: Char): VehicleAction {
        return when (action) {
            'L' -> VehicleAction.RotateLeft
            'R' -> VehicleAction.RotateRight
            'M' -> VehicleAction.Move
            else -> VehicleAction.Unknown
        }
    }

    fun updateDirection(direction: Char): Direction {
        return when (direction) {
            'N' -> Direction.N
            'W' -> Direction.W
            'S' -> Direction.S
            'E' -> Direction.E
            else -> Direction.Unknown
        }
    }

    fun updatePosition(
        roverDirection: Direction,
        roverPosition: Coordinate,
        action: VehicleAction,
        plateauDimensions: Coordinate
    ): Pair<Direction, Coordinate> {

        var directionUpdate = roverDirection
        var positionUpdate = Coordinate(roverPosition.x, roverPosition.y)

        when (action) {
            VehicleAction.RotateLeft -> {
                directionUpdate = when (roverDirection) {
                    Direction.N -> Direction.W
                    Direction.E -> Direction.N
                    Direction.S -> Direction.E
                    Direction.W -> Direction.S
                    Direction.Unknown -> {
                        println("Unknown rover direction")
                        directionUpdate
                    }
                }
            }

            VehicleAction.RotateRight -> {
                directionUpdate = when (roverDirection) {
                    Direction.N -> Direction.E
                    Direction.E -> Direction.S
                    Direction.S -> Direction.W
                    Direction.W -> Direction.N
                    Direction.Unknown -> {
                        println("Unknown rover direction")
                        directionUpdate
                    }
                }
            }

            VehicleAction.Move -> {
                when (roverDirection) {
                    Direction.N -> if (roverPosition.y < plateauDimensions.y) {
                        positionUpdate = Coordinate(roverPosition.x, roverPosition.y + 1)
                    }
                    Direction.E -> if (roverPosition.x < plateauDimensions.x) {
                        positionUpdate = Coordinate(roverPosition.x + 1, roverPosition.y)
                    }
                    Direction.S -> if (roverPosition.y > 0) {
                        positionUpdate = Coordinate(roverPosition.x, roverPosition.y - 1)
                    }
                    Direction.W -> if (roverPosition.x > 0) {
                        positionUpdate = Coordinate(roverPosition.x - 1, roverPosition.y)
                    }
                    Direction.Unknown -> println("Unknown rover direction")
                }
            }

            VehicleAction.Unknown -> println("Unknown action: ${action.value}")
        }

        return Pair(directionUpdate, positionUpdate)
    }
}