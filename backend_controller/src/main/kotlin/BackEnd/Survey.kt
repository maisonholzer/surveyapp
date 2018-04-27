
package BackEnd

import java.io.BufferedReader
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Paths

val questionList: MutableMap<String,question> = readQuestionFile()
val surveyList: MutableMap<String, survey> = readSurveyFile()

class survey {
    val sID: String
    val title: String
    val type:String
    val questions: List<question>
    constructor(sID: String, title: String, type: String, questions: List<question>) {
        this.sID = sID; this.title = title; this.type = type; this.questions = questions
    }
    constructor() {
        this.sID = ""; this.title = ""; this.type = ""; this.questions = emptyList()
    }
}

class question {
    val qID: String
    val text: String
    val answers: List<String>
    constructor(qID: String, text: String, answers: List<String>) {
        this.qID = qID; this.text = text; this.answers = answers
    }
    constructor() {
        this.qID = ""; this.text = ""; this.answers = emptyList()
    }
}

fun readQuestionFile(): MutableMap<String,question>{
    val tempList = mutableMapOf<String,question>()
    var fileReader: BufferedReader? = null
    val questionID = 0
    val text = 1
    var q: question? = null

    try {
        var line: String?

        if (System.getProperty("os.name").split(" ")[0] == "Windows") {
            fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString() + "\\res\\QuestionList.csv"))
        }else fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString() + "/res/QuestionList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null && line != ""){
            val tokens = line.split(",")
            if (tokens.size > 0){
                val temp = mutableListOf<String>()
                for (t in 2 until tokens.size){
                    temp.add(tokens[t])
                }
                q = question(tokens[questionID], tokens[text], temp)
                tempList.put(q.qID,q)
            }
            line = fileReader.readLine()
        }
    } catch(e: Exception){
        println("Error during Question File Read")
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
        } catch (e: IOException) {
            println("Error Closing File Reader, Question File Read")
            e.printStackTrace()
        }
    }
    return tempList
}

fun readSurveyFile(): MutableMap<String,survey>{
    val tempList = mutableMapOf<String,survey>()
    var fileReader: BufferedReader? = null
    val survID = 0
    val title = 1
    val type = 2
    var surv: survey? = null

    try {
        var line: String?

        // Determines which OS is being used since path structure is different between Windows and Mac.
        if (System.getProperty("os.name").split(" ")[0] == "Windows") {
            fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString() + "\\res\\SurveyList.csv"))
        }else fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString() + "/res/SurveyList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null && line != ""){
            val tokens = line.split(",")
            if (tokens.size > 0){
                val temp = mutableListOf<question>()
                for (t in 3 until tokens.size){
                    temp.add(questionList.get(tokens[t]) as question)
                }
                surv = survey(tokens[survID], tokens[title], tokens[type] ,temp)
                tempList.put(surv.sID,surv)
            }
            line = fileReader.readLine()
        }
    } catch(e: Exception){
        println("Error during Survey File Read")
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
        } catch (e: IOException){
            println("Error Closing Survey File Reader")
            e.printStackTrace()
        }
    }
    return tempList
}

internal fun writeSurveyFile(){
    val header = "Survey ID, Title, Questions"
    var fileWriter: FileWriter? = null

    try {
        // Determines which OS is being used since path structure is different between Windows and Mac.
        if (System.getProperty("os.name").split(" ")[0] == "Windows") {
            fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString() + "\\res\\SurveyList.csv")
        }else fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString() + "/res/SurveyList.csv")

        fileWriter.append(header)
        fileWriter.append('\n')

        for (a in surveyList){
            fileWriter.append(a.value.sID)
            fileWriter.append(',')
            fileWriter.append(a.value.title)
            fileWriter.append(',')
            fileWriter.append(a.value.type)
            for (q in a.value.questions){
                fileWriter.append(',')
                fileWriter.append(q.qID.toString())
            }
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

internal fun writeQuestionFile(){
    val header = "Question ID, Question Text, Answers"
    var fileWriter: FileWriter? = null

    try {
        // Determines which OS is being used since path structure is different between Windows and Mac.
        if (System.getProperty("os.name").split(" ")[0] == "Windows") {
            fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString() + "\\res\\QuestionList.csv")
        }else fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString() + "/res/QuestionList.csv")

        fileWriter.append(header)
        fileWriter.append('\n')

        for (a in questionList){
            fileWriter.append(a.value.qID)
            fileWriter.append(',')
            fileWriter.append(a.value.text)
            for (q in a.value.answers){
                fileWriter.append(',')
                fileWriter.append(q.toString())
            }
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

fun main(args: Array<String>) {
    println(surveyList.get("s2")?.type)
    println(System.getProperty("os.name").split(" "))
    if (System.getProperty("os.name").split(" ")[0] == "Windows") println("True")

}