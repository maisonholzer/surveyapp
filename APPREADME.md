## Survey App intellij/Android Studio project

# Running the Server

There is a Server Kotlin file and a BackEnd package under src/main/kotlin:
  Server.kt - run this to have a server at localhost:8080
  Under the BackEnd package:
    Backend_API is an interface to communicate with the res files, which can be found in the res folder.  These files are 
    written to in order to store our data.
    Survey.kt is a data class that allows for question and survey objects to be used.
    User.kt is a simple data class to differentiate between Admins and Clients (needed by the Server)
    
The Gradle should take care of all of the files accordingly, so you shouldn't need to worry about connecting them.

There needs to be one run configuration for the server.  In case this is not already set up, to run the server the main 
program is edu.uiowa.cs.NettyServer .

# Running TornadoFX

There are many different packages under src/main/kotlin:
  Under the app package:
    Both the Styles and SurveyApp Stylesheets are what drive the style of the app and the way objects are displayed.
  Under the controller package:
    appClient.kt communicates with the server and pushes and pulls objects to and from the server to interact with it.
  Under the models package:
    Both Survey.kt and User.kt model after the backend functions but don't interact with persistent storage at all, since that
    is the Controller's job.
  Under the vew package:
    adminView.kt, clientView.kt, and appTest.kt are pages of the app to be seen.  When starting the app, appTest.kt will
    appear first, which allows a user to login.  If the user is an Admin, adminView.kt will be seen.  If the user is a Client,
    clientView.kt will be seen.
    
Again, the Gradle should handle the build of the app, so no need to mess with any other settings.

There should be one run configuration to show the app.  If this is not already set up, to run the app the main program is 
app.SurveyApp (named as Test App for now, but you could name it how you like).

# Running Android Studio

