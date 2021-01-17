<p align="center">
  <img src="/images/logo.png" width="40%"/>
  </p>
  
  
# NoteApp

[![APK](https://img.shields.io/badge/download-APK-E53935.svg)](https://github.com/carolinemusyoka/NoteApp/blob/master/app/app-debug.apk?raw=true)
[![Min sdk](https://img.shields.io/badge/platform-Android-00E676.svg)](https://github.com/carolinemusyoka/NoteApp/blob/master/app/build.gradle)
[![Min sdk](https://img.shields.io/badge/minsdk-21-yellow.svg)](https://github.com/carolinemusyoka/2048TheGame/blob/master/app/build.gradle)
[![Language](https://img.shields.io/badge/language-kotlin-orange.svg)](https://github.com/carolinemusyoka/2048TheGame/blob/master/app/build.gradle)
[![License](https://img.shields.io/apm/l/vim-mode)](https://github.com/carolinemusyoka/2048TheGame/blob/master/LICENSE)



Note App is a simple note taking application  📝. Written in Kotlin and implements android libraries. All Changes are stored in the Room database. 

## Functionality
- Create notes.
- Delete notes onSwipe.
- Update notes
- Dark Mode.

## Libraries used

- Room
- Viewmodel - tores UI-related data that isn't destroyed on UI changes.
- Livedata -Data objects that notify views when the underlying database changes.
- Coroutines
- Material library
- Navigation Components
- DataStore(Preferences)



## Design Pattern
MVVM (Model-View-ViewModel) is one of the architectural patterns which enhances separation of concerns, it allows separating the user interface logic from the business (or the back-end) logic. Its target (with other MVC patterns goal) is to achieve the following principle “Keeping UI code simple and free of app logic in order to make it easier to manage

  - Lifecycles: It manages activity and fragment lifecycles of our app, survives configuration changes, avoids memory leaks and easily loads data into our UI.
  - LiveData: It notifies views of any database changes. Use LiveData to build data objects that notify views when the underlying database changes.
  - Room: It is a SQLite object mapping library. Use it to Avoid boilerplate code and easily convert SQLite table data to Java objects. Room provides compile time checks        of SQLite statements and can return RxJava, Flowable and LiveData observables.
  - ViewModel: It manages UI-related data in a lifecycle-conscious way. It stores UI-related data that isn't destroyed on app rotations.
  - Repository: The repository depends on a persistent data model and a remote backend data source.
  
  <p align="center">
  <img src="/images/mvvm-architecture-pattern.png" width="50%"/>
  </p>
  
  
  ## Screenshots
  
  <p align="center">
<img src="/images/1.png" width="20%"/>
<img src="/images/2.png" width="20%"/> 
<img src="/images/3.png" width="20%"/> 
<img src="/images/4.png" width="20%"/>
<img src="/images/5.png" width="20%"/> 
<img src="/images/6.png" width="20%"/> 
<img src="/images/7.png" width="20%"/>
<img src="/images/8.png" width="20%"/> 

</p>

## WIP
-  [ ] Tests (both UI and Unit)
-  [ ] Notifications/Reminders
-  [ ] Rich text notes with support for bold, italics, mono space and strike-through
-  [ ] Archived, Favourites, etc
-  [ ] Markdown Support: The text elements have markdown support
-  [ ] Choice of Grid / List layouts
