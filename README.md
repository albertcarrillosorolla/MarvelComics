# MARVEL COMICS

Marvel Comics is an Android App that shows a list of Marvel comics and a detail view of the comic with a short description and the main characters of the story.
This data is taken from the public MARVEL API: https://developer.marvel.com

Github link: https://github.com/albertcarrillosorolla/MarvelComics.git

## TOOLS

Marvel Comics has been developed with Android Studio 4.1.2 using exclusively JAVA.

## ARCHITECTURE

This app has been developed as a vertical slice of how a much more complex app would be. This is the reason why it's been used my custom version of Clean Architecture with MVVM in the presentation 
layer.

### CLEAN

From a Clean perspective, there are three layers:
- INFRAESTRUCTURE (data and presentation{views and modelviews})
- USECASES
- DOMAIN (entities and repository contracts)

The main key in this structure is to keep the inner layers (domain) completely independent from the framework (Android), data source or library. This is how the business logic is protected from external dependencies, so it's implementations is purely logic plain java code. This makes it much easier to test and to mantain.

Every time the presentation (view/android) needs to do something, this layer calls to a usecase and waits until the usecase is resolved. The Usecases work as a link between the presentation and the domain kingdom. As this app doesn't have any logic more than showing data from an API, in this case, the Usecases only make a request to a Repository. As the implementation of this Repository is not part of the domain, a Repository contract is defined at Domain layer and implemented in the data layer. To keep the Data layer as flexible as possible, the repository has been segregated from the DataSource, so one repository, can have more than one DataSources, as a remote and local caching data storage system. 

In pure Clean Architecture, each data object (entity) should have it's own representation in every layer, but in order to keep it simpler, there's been used only two representations: one in the DataSource, this representation was passed through a DataMapper to convert it to the Domain Entity. In the presentation layer, it's been used the same Domain Entity. In order to be able to pass the Entities through the Fragments (presentation), they implement Parcelable, which is a dependency used in the domain layer. This would not be possible in a pure clean architecture.

### MVVM

In the presentation layer it's been used MVVM, actually View and ViewModel, as the Model is implemented as the UseCase of the Clean Architecture. Since Google presented the new Architecture Components, MVVM has become one of the most used View Patterns for Android. All the view state is kept in the VM, that does not depend on the complex View lifecycle. LiveData is a google library that allows the Views to observe this data in the ModelViews and update the ui.

## LIBRARIES

Navigation and safe-args between fragments (list and detail): androidx.navigation.

Google Architecture Components: MVVM and LiveData.

Picasso for loading the images.

Retrofit2 and GSON to fetch the data from the Marvel API.

## TO DOs

All the dependencies have been injected through constructors so, as an example, the implementation of the Repository (infra layer) has been injected from the presentation (infra layer) to the usecase, that knows it's interface (domain level) but does not know the implementation (infra layer). This keeps the Clean Architecture but would be much simpler using a DI library as Dagger2 or Koin.

LiveData has been used to asyncronously coordinate the VM with the View and a custom generic Handler has been used to coordinate the async data processes with the VM. Maybe RXJava would work better for this coordination.

Although this project would be easily testable because of the architecture, no tests have been implemented due the lack of time and no need to mantain this project.

The local data source and the cache policy in the repositories have not been developed yet.

Some ui feedback during the next page request and the characters fetching would be nice.

## DEVELOPMENT TIME

This project has been developed in about 10h, and reusing some classes I developed for other projects.

