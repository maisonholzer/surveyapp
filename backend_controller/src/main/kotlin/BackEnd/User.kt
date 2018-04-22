package BackEnd

import java.io.FileWriter
import java.io.IOException
import java.io.BufferedReader
import java.io.FileReader
import java.nio.file.Paths

val ClientList = mutableMapOf<String,Client>()
val AdminList: MutableMap<String,Admin> = readAdminFile()

open class User(val username: String,val pass: String)

class Admin: User {

    constructor(username: String, pass: String): super(username,pass)

}

class Client: User {
    var surveys = mutableListOf<survey>()

    constructor(username: String, pass: String): super(username,pass)

}

fun readAdminFile(): MutableMap<String,Admin>{
    val tempList = mutableMapOf<String,Admin>()
    var fileReader: BufferedReader? = null

    val Admin_username = 0
    val Admin_password = 1


    try {
        var line: String?

        fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString()+"\\res\\AdminList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null) {
            val tokens = line.split(",")
            if (tokens.size > 0) {
                val adUser = Admin(tokens[Admin_username], tokens[Admin_password])
                tempList.put(adUser.username,adUser)
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
    return tempList
}

fun readClientFile(): MutableMap<String,Client>{
    val tempList = mutableMapOf<String,Client>()
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
                val adUser = Client(tokens[username], tokens[password])
                tempList.put(adUser.username,adUser)
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
    return tempList
}

internal fun updateAdminFile(){
    val header = "username, password"
    var fileWriter: FileWriter? = null

    try {
        fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString()+"\\res\\AdminList.csv")
        fileWriter.append(header)
        fileWriter.append('\n')

        for (a in AdminList){
            fileWriter.append(a.value.username)
            fileWriter.append(',')
            fileWriter.append(a.value.pass)
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

internal fun updateClientFile(){
    val header = "username, password, surveys"
    var fileWriter: FileWriter? = null

    try {
        fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString()+"\\res\\ClientList.csv")
        fileWriter.append(header)
        fileWriter.append('\n')

        for (a in ClientList){
            fileWriter.append(a.value.username)
            fileWriter.append(',')
            fileWriter.append(a.value.pass)
            fileWriter.append(',')
            var surveyString = ""
            for (s in a.value.surveys){
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
                Ad = Admin(tokens[username], tokens[password])
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

private fun userName_Available(newUsername: String, userList: List<User>): Boolean{
    for (users in userList){
        if (users.username == newUsername) return false
    }
    return true
}

fun main(args: Array<String>) {

}