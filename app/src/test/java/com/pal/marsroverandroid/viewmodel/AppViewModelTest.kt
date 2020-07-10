package com.pal.marsroverandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.pal.marsroverandroid.data.DataReader
import com.pal.marsroverandroid.domain.Coordinate
import com.pal.marsroverandroid.domain.RoverData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import testObserver


@RunWith(JUnit4::class)
class AppViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mockDataReader: DataReader = mock()
    private val modelView = AppViewModel(mockDataReader)

    @Test
    fun `start moving without reading file`() {
        val testObserver = modelView.result.testObserver()
        modelView.moveVehicle()
        assert(testObserver.observedValues.size == 0)
        verify(mockDataReader, never()).parseFile(any())
    }

    @Test
    fun `shows error when cannot read file`() {
        val runtimeException = RuntimeException("¯\\_(ツ)_/¯")
        whenever(mockDataReader.parseFile("file")).thenThrow(runtimeException)
        val testObserver = modelView.result.testObserver()
        modelView.readFile("file")
        assert(testObserver.observedValues.size == 1)
        assert(testObserver.observedValues.first() == runtimeException.toString())
    }

    @Test
    fun `move vehicle when got correct data`() {
        val roverData = RoverData("MML", 'N', Coordinate(0, 0), Coordinate(5, 5))
        whenever(mockDataReader.parseFile("file")).thenReturn(roverData)

        val testObserver = modelView.result.testObserver()
        modelView.readFile("file")
        modelView.moveVehicle()
        assert(testObserver.observedValues.size == 4)
        assert(testObserver.observedValues.last() == "0 2 W")
    }

}