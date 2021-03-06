
package BackEnd

import java.io.FileWriter
import java.io.IOException
import java.io.BufferedReader
import java.io.FileReader
import java.nio.file.Paths

val ClientList: MutableMap<String,Client> = readClientFile()
val AdminList: MutableMap<String,Admin> = readAdminFile()

open class User {
    val username: String
    val pass: String
    constructor(username: String, pass: String) {
        this.username = username; this.pass = pass
    }
    constructor() {
        this.username = ""; this.pass = ""
    }
}

class Admin: User {

    constructor(username: String, pass: String): super(username,pass)

}

class Client: User {
    var surveys = mutableMapOf<String,survey>()

    constructor(username: String, pass: String): super(username,pass)

}

fun readAdminFile(): MutableMap<String,Admin>{
    println("BackEnd-User - readAdminFile function")
    val tempList = mutableMapOf<String,Admin>()
    var fileReader: BufferedReader? = null

    val Admin_username = 0
    val Admin_password = 1


    try {
        var line: String?

        // Determines which OS is being used since path structure is different between Windows and Mac.
        if (System.getProperty("os.name").split(" ")[0] == "Windows") {
            fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString() + "\\res\\AdminList.csv"))
        }else fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString() + "/res/AdminList.csv"))

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
    println("BackEnd-User - readClientFile function")
    val tempList = mutableMapOf<String,Client>()
    var fileReader: BufferedReader? = null

    val username = 0
    val password = 1
    val surveyList = 2

    // Reads the AdminList and create a list for editing if it has not already been done.
    try {
        var line: String?

        // Determines which OS is being used since path structure is different between Windows and Mac.
        if (System.getProperty("os.name").split(" ")[0] == "Windows") {
            fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString() + "\\res\\ClientList.csv"))
        }else fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString() + "/res/ClientList.csv"))

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

fun updateAdminFile(){
    println("BackEnd-User - updateAdminFile function")
    val header = "username, password"
    var fileWriter: FileWriter? = null

    try {
        // Determines which OS is being used since path structure is different between Windows and Mac.
        if (System.getProperty("os.name").split(" ")[0] == "Windows") {
            fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString() + "\\res\\AdminList.csv")
        }else fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString() + "/res/AdminList.csv")

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

fun updateClientFile(){
    println("BackEnd-User - updateClientFile function")
    val header = "username, password, surveys"
    var fileWriter: FileWriter? = null

    try {
        // Determines which OS is being used since path structure is different between Windows and Mac.
        if (System.getProperty("os.name").split(" ")[0] == "Windows") {
            fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString() + "\\res\\ClientList.csv")
        }else fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString() + "/res/ClientList.csv")

        fileWriter.append(header)
        fileWriter.append('\n')

        for (a in ClientList){
            fileWriter.append(a.value.username)
            fileWriter.append(',')
            fileWriter.append(a.value.pass)
            fileWriter.append(',')
            var surveyString = ""
            for (s in a.value.surveys){
                surveyString += s.value.sID
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
