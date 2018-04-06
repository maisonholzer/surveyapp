package edu.uiowa.cs

import java.io.FileWriter
import java.io.IOException
import java.io.BufferedReader
import java.io.FileReader
import java.nio.file.Paths

val AdministratorList = mutableListOf<User>()
val clientList = mutableListOf<Client>()

private fun retrieveAdminList(){
    var fileReader: BufferedReader? = null

    val Admin_username = 0
    val Admin_password = 1

    // Reads the AdminList and create a list for editing if it has not already been done.
    try {
        //val admins = ArrayList<User>()
        var line: String?

        fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString()+"\\res\\AdminList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null) {
            val tokens = line.split(",")
            if (tokens.size > 0) {
                val adUser = Admin(tokens[Admin_username], tokens[Admin_password], false)
                //admins.add(adUser)
                //AdministratorList.add(adUser)
            }

            line = fileReader.readLine()
        }


    } catch (e: Exception) {
        "Error with Reading Admin List"
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
        } catch (e: IOException) {
            println("Error closing fileReader.")
        }
    }

}

private fun retrieveClientList(){
    var fileReader: BufferedReader? = null

    val username = 0
    val password = 1
    val surveyList = 2

    // Reads the AdminList and create a list for editing if it has not already been done.
    try {
        var line: String?

        fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString()+"\\res\\ClientList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null) {
            val tokens = line.split(",")
            if (tokens.size > 0) {
                val adUser = Client(tokens[username], tokens[password], false)
            }

            line = fileReader.readLine()
        }


    } catch (e: Exception) {
        "Error with Reading Client List"
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
        } catch (e: IOException) {
            println("Error closing fileReader.")
        }
    }

}

private fun updateAdminFile(){
    val header = "username, password"
    var fileWriter: FileWriter? = null

    try {
        fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString()+"\\res\\AdminList.csv")
        fileWriter.append(header)
        fileWriter.append('\n')

        for (a in AdministratorList){
            fileWriter.append(a.username)
            fileWriter.append(',')
            fileWriter.append(a.pass)
            fileWriter.append('\n')
        }

    } catch (e:Exception){
        println("Error")
        e.printStackTrace()
    } finally {
        try {
            fileWriter!!.flush()
            fileWriter.close()
        } catch (e: IOException){
            println("Closing Error")
            e.printStackTrace()
        }
    }
}

private fun updateClientFile(){
    val header = "username, password, surveys"
    var fileWriter: FileWriter? = null

    try {
        fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString()+"\\res\\ClientList.csv")
        fileWriter.append(header)
        fileWriter.append('\n')

        for (a in clientList){
            fileWriter.append(a.username)
            fileWriter.append(',')
            fileWriter.append(a.pass)
            fileWriter.append(',')
            var surveyString = ""
            for (s in a.surveys){
                surveyString += s.sID
                surveyString += ","
            }
            fileWriter.append(surveyString)
            fileWriter.append('\n')
        }

    } catch (e:Exception){
        println("Error")
        e.printStackTrace()
    } finally {
        try {
            fileWriter!!.flush()
            fileWriter.close()
        } catch (e: IOException){
            println("Closing Error")
            e.printStackTrace()
        }
    }
}

fun RetrieveAdmin(adminID: String): User?{
    var fileReader: BufferedReader? = null
    val username = 0
    val password = 1
    var Ad: User? = null

    try {
        var line: String?


        fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString()+"\\res\\AdminList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null){
            val tokens = line.split(",")
            if (tokens.size > 0 && tokens[username] == adminID){
                Ad = Admin(tokens[username],tokens[password],false)
            }
            line = fileReader.readLine()
        }
    } catch(e: Exception){
        println("Error during Admin Retrieval")
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
        } catch (e: IOException){
            println("Error Closing File Reader")
            e.printStackTrace()
        }
    }
    return Ad
}

fun createAdmin(username: String, password: String){
    if (AdministratorList.isEmpty()) retrieveAdminList()

    if (userName_Available(username, AdministratorList)){
        Admin(username,password,true)
    }else println("Username is use.")

}

fun createClient(username: String, password: String){
    if (clientList.isEmpty()) retrieveClientList()

    if (userName_Available(username, clientList)){
        Client(username,password,true)
    }else println("Username is use.")

}

private fun userName_Available(newUsername: String, userList: List<User>): Boolean{
    for (users in userList){
        if (users.username == newUsername) return false
    }
    return true
}
private fun RetrieveClient(clientID: String){

}

open class User(val username: String,val pass: String)

class Admin: User{

    constructor(username: String, pass: String, new: Boolean): super(username,pass){
        AdministratorList.add(this)
        if (new == true){
            updateAdminFile()

        }
    }
}

class Client: User{
    var surveys = mutableListOf<survey>()

    constructor(username: String, pass: String, new: Boolean): super(username,pass){
        clientList.add(this)
        if (new == true) updateClientFile()
    }
}

fun main(args: Array<String>) {

}