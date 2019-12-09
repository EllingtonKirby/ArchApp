# Sample App

This app is an example of several modern Android architecture principles, using the latest available
tools from Android Jetpack.

## Core

### Modules

The app is composed of a modular project structure, using dynamic features to build an app bundle. In the future,
an app built on this foundation would receive the APK size reduction and dynamic delivery benefits of the app bundle.

There is a very thin :app module. Then, there are two :common modules, one creating the base framework, the other setting
up the core dagger2 implementation. Each feature should be contained within a single activity, which is then contained in a single module.

Support modules are included as well, including the :buildSrc module, which uses the kotlin-dsl to assist in the building of
dependencies within build.gradle files, as well as the :navigation module which assists in inter-module navigation.

Much of the module level decisions were taken from [This great project by Mario Sanoguera de Lorenzo](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture)

### Architecture

The app uses Google's recommended MVVM architecture, using the repository pattern to abstract network and local data retrieval.
The ViewModel interacts with this repository, serving it to the Views via LiveData observations.

In the creation of this project, I really grew to appreciate this architecture style. It allowed me to abstract out really
any level of the application, and one can imagine easily how simple it would be to replace an implementation of one of the
data sources with a mocking source for testing, or something else.

Much of the architecture level decisions were taken from [The Architecture Blueprints Project](https://github.com/android/architecture-samples)

### Dependency Injection

I was looking forward to using Dagger2 with dagger.android in this project, as I have some experience with it in other sample projects, but I
ran into several issues. The issues stemmed from the decision to modularize the architecture as well as use Jetpack. Mainly the `AndroidInjectionModule`
including dependencies on `AppCompatFragment` instead of `androidx.Fragment`. There were also some issues in creating in creating dependency cycles.

Instead I used Dagger2 alone, and ran into no issues.

I used the [Plaid](https://github.com/android/plaid) app as an example to make many of my decisions, and it seemed
they had run into a similar issue using dagger.Android in the modularization of the project.

## Takeaways
* Retrofit 2.6 adding coroutine support is amazing. I had no need for RxJava at any level in the project
* Deciding on the ViewModelStore is important. I use fragments as the ViewModelStore owner, but this necessitates a ViewModel per Fragment approach, which
can introduce some boilerplate
* If I had made the activity or perhaps the application the ViewModelStore, I could have allowed the fragments to share a ViewModel.
* The Navigation component is really easy to work with if the project is set up with it from the beginning. It allows the container activity
to essentially be empty, and it really improves the usage of navigation code.
* I was unable to get the shared element transition to work well using two separate image urls, moreover, Navigation component
doesn't really play nice when trying to use a NavigationDirection with a shared element transition
* Room was really simple to setup and take advantage of. However, I don't think this project is really robust enough to really test it's usefulness.

## For the Future
* The :home module could easily be abstracted to handle any List -> Detail style interaction, would mainly be about introducing a layer to
change repository models into models the view works with.
* Add a memory-caching data source that would go between the local database and the remote source
* Allow switching between grid and list views, or intelligently change based on device orientation