## netdemo intellij project

This README.md file was created within IntelliJ, after the
the project had already been committed and pushed to github.uiowa.edu.

There are three Kotlin files under src/main/kotlin:
  User.kt is a simple data class (needed by both Client and Server)
  Server.kt - run this to have a server at localhost:8080
  Client.kt - run this to test the server
These three Kotlin programs all have package edu.uiowa.cs,
but you don't need to have the structure of src/edu/uiowa/cs
because Gradle takes care of that.

There need to be two run configurations, one for server and one
for the client. In case this is not already set up, to run the
server the main program is edu.uiowa.cs.NettyServer, and for
the client the main program is edu.uiowa.cs.TestClient.

