# AlphaVantage App

**FilamentDroid** is an Android application with Clean Architecture approach (or close to it).
This application is should be in line with current technology standards.

## Features (Pages)

As the time of writing, the application has 2 pages, first page, which should contain 3d model
selection.
The first page should have OK button, which directs the app to second page, consisted of tab bar of
single 3d model section and profile section

## Architecture

# TODO: Image will be put in later

Architecture should follow this graph
[Module structure]()

This architecture mostly follows the mix of [this](https://github.com/andrejoshua/alphavantageapp)
and [this](https://github.com/andrejoshua/covid-apps). See those links for detailed explanation
about Clean Architecture.

There are few additional points i will explain given the picture:

* The architecture used is based on task, which requires additional navigation and API call
* The API call is auto-generated using Retrofit, and GSON as an adapter to convert API response into
  Kotlin Class
* The Response model class should reflect what API provides
* The Domain model class should reflect what is shown in UI
* The data is carried through Coroutines, which will be converted to LiveData in ViewModel
* LiveData will later be converted to State, which follows Jetpack Compose UI mechanism

## Project Libraries

* [Kotlin 1.8.10](https://kotlinlang.org/docs/reference/android-overview.html)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
* [Dagger Hilt DI](https://dagger.dev/hilt/)
* [AndroidX Jetpack](https://developer.android.com/jetpack)
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=Cj0KCQjwxuCnBhDLARIsAB-cq1rSXW4qMIzEuH3E_kQTce6lxCOUCrRwU4sRZITZGqzlqXh0e1J2fhoaAg59EALw_wcB&gclsrc=aw.ds)
    * [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
* [Retrofit](https://square.github.io/retrofit/)
* [JUnit + Mockito](https://developer.android.com/training/testing/unit-testing/local-unit-tests)

## Issues produced

* No issue as of 19-09-2023