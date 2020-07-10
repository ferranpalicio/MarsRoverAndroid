package com.pal.marsroverandroid.data

import android.content.Context
import com.google.gson.Gson
import com.pal.marsroverandroid.domain.RoverData
import java.io.InputStream
import javax.inject.Inject

class LocalFileDataReader @Inject constructor(private val context: Context): DataReader {
    //todo test with robolectric
    override fun parseFile(file: String): RoverData? {
        val inputStream: InputStream = context.assets.open(file)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer)
        return Gson().fromJson(json, RoverData::class.java)
    }

}