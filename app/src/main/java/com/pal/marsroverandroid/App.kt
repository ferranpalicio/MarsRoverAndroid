package com.pal.marsroverandroid

import android.app.Application
import com.pal.marsroverandroid.di.AppComponent
import com.pal.marsroverandroid.di.ComponentProvider
import com.pal.marsroverandroid.di.DaggerAppComponent

class App : Application(), ComponentProvider {

    override val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}