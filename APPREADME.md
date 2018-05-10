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

1. Need to run the edu.uiowa.cs.NettyServer in the server first. 
2. Then open the Android Studio and open an existing an Android Studio project and open the file named android. It should be set up by itself. 
3. Under the app package, there are three packages: mainfests, java, and res. 
        Java package includes all the class files and kotlin files. 
        res package includes all the app layouts files
4. Under java package: there is a controller package and a cs.uiowa.edu.android_front_end package. 
        Under the controller package: 
            There is a Survey.kt and a User.kt which are controller's job. 
        Under the cs.uiowa.edu.android_front_end package, there are 11 files: 1 client file and 10 front end activities.
            The NetAccess.kt is the client code which communicates with the server and pushes and pulls objects to and from the server to interact with it.          
5. Under res package: 
       drawable package includes all the picture files. 
       layout package includes ten xml files of all the activities in cs.uiowa.edu.android_front_end package. We can use xml file directly change the front layouts of the app. Need to be careful about it because they are connected to the cs.uiowa.edu.android_front_end package. 

6. To run the app:
    First step: Make sure the server is running
    Second step: edit the configurations. Make sure to run the app. 
    Third step: the Android app needs the real IP address of the server computer. Find NetAccess.kt, Change all the IP addresses. 
                (Find your IP: Windows: https://www.windowscentral.com/4-easy-ways-find-your-pc-ip-address-windows-10-s
                               Mac : http://osxdaily.com/2010/11/21/find-ip-address-mac/)
