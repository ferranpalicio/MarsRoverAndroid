package com.pal.marsroverandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pal.marsroverandroid.data.DataReader
import com.pal.marsroverandroid.domain.Direction
import com.pal.marsroverandroid.domain.RoverVehicle
import com.pal.marsroverandroid.domain.RoverData

class AppViewModel constructor(private val dataReader: DataReader) : ViewModel() {

    private val resultLD: MutableLiveData<String> = MutableLiveData()
    val result: LiveData<String>
        get() = resultLD

    private var roverData: RoverData? = null

    fun readFile(file: String) {
        try {
            roverData = dataReader.parseFile(file)
        } catch (e: Exception) {
            resultLD.value = e.toString()
        }
    }

    fun moveVehicle() {
        roverData?.let {
            val roverVehicle = RoverVehicle()
            var currentRoverDirection: Direction =
                roverVehicle.updateDirection(it.roverDirection)
            var currentRoverPosition = it.roverPosition

            resultLD.value =
                "${currentRoverPosition.x} ${currentRoverPosition.y} ${currentRoverDirection.value}"

            it.movements.forEach { movement ->
                val (directionUpdated, positionUpdated) = roverVehicle.updatePosition(
                    currentRoverDirection,
                    currentRoverPosition,
                    roverVehicle.performAction(movement),
                    it.topRightCorner
                )
                currentRoverDirection = directionUpdated
                currentRoverPosition = positionUpdated
                resultLD.value =
                    "${currentRoverPosition.x} ${currentRoverPosition.y} ${currentRoverDirection.value}"
            }
        }
    }

}