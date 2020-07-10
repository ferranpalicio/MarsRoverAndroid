package com.pal.marsroverandroid.di

import androidx.appcompat.app.AppCompatActivity

interface ComponentProvider {
    val component: AppComponent
}

val AppCompatActivity.injector get() = (application as ComponentProvider).component
