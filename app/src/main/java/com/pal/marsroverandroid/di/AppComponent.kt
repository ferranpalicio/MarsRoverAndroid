package com.pal.marsroverandroid.di

import android.content.Context
import com.pal.marsroverandroid.data.DataReader
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context
        ) : AppComponent
    }

    /*fun inject(activity: MainActivity)*/
    fun dataReader(): DataReader
}