# Android GitHub Users Listing App
Simple one-screen app containing list of 30 users from github and their 3 repo names based on MVVM architecture.
Downloaded data is cached into Realm db, from where its used to feed the view.
In case of lack internet connection data will be loaded from Realm.
Multi-threaded tasks are handled by use of RxJava.
Network connection with help of Retrofit2.
Use of runtime di/dsl Koin.
Avatar loading by Glide.

### To-Do
Rethink approach with Realm db, Consider using SQL-based database like Room for optimizing query and better cooperation with RxJava.
Cover app in at least unit tests for buisness logic.
Adjust fonts/color of repositories list.
Add search by repository name.
Consider use of Dagger2 because of compile-time safety.
Use styles more broadly.

