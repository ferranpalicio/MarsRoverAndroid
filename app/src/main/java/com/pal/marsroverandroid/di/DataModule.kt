package com.pal.marsroverandroid.di

import com.pal.marsroverandroid.data.DataReader
import com.pal.marsroverandroid.data.LocalFileDataReader
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun getDataReader(localFileDataReader: LocalFileDataReader) : DataReader
}