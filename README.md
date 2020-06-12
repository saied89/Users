### Libraries and Technologies
The project is written in Kotlin and kotlin coroutines are used for async operations.
The app follows the single activity structure and Android Architectural Components Navigation is used to manage navigation between screens.  AAC Lifecycle(ViewModel and LiveData) is used to implement MVVM.  AAC Room is used for persistence.
Furthermore Koin is used for dependency injection.
The project uses android gradle plugin 4.0.0 and requires Android Studio 4.0

### Overview of the project
The project has three top level packages. `di`, `ui` and `data`.  
`di` holds koin modules that contain dependency  definitions.  `appModule` holds definitions of viewModels and `dbModule` holds definition of `Dao` object and the repository.
`ui` holds various screens. The navigation graph *under res/navigation/nav_graph.xml* shows an overview of app screens and their relations.
`data` contains various classes related to persistence and Room.

###### Hardcoded admin Credentials
user: admin@user.com
password: admin