package BackEnd

import java.io.BufferedReader
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Paths

val surveyList = mutableListOf<survey>()
val questionList = mutableListOf<question>()

class survey(val sID: String,val title: String, val questions: List<question>)

class question(val qID: String, val text: String, val answers: List<String>)

private fun writeSurveyFile(){
    val header = "Survey ID, Title, Questions"
    var fileWriter: FileWriter? = null

    try {
        fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString()+"\\res\\SurveyList.csv")
        fileWriter.append(header)
        fileWriter.append('\n')

        for (a in surveyList){
            fileWriter.append(a.sID)
            fileWriter.append(',')
            fileWriter.append(a.title)
            //fileWriter.append(',')
            for (q in a.questions){
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

private fun writeQuestionFile(){
    val header = "Question ID, Question Text, Answers"
    var fileWriter: FileWriter? = null

    try {
        fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString()+"\\res\\QuestionList.csv")
        fileWriter.append(header)
        fileWriter.append('\n')

        for (a in questionList){
            fileWriter.append(a.qID)
            fileWriter.append(',')
            fileWriter.append(a.text)
            for (q in a.answers){
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

private fun getQuestionList(){
    var fileReader: BufferedReader? = null
    val questionID = 0
    val text = 1
    var q: question? = null

    try {
        var line: String?


        fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString()+"\\res\\QuestionList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null){
            val tokens = line.split(",")
            if (tokens.size > 0){
                val temp = mutableListOf<String>()
                for (t in 2 until tokens.size){
                    temp.add(tokens[t])
                }
                q = question(tokens[questionID], tokens[text], temp)
                questionList.add(q)
            }
            line = fileReader.readLine()
        }
    } catch(e: Exception){
        println("Error during Question Retrieval")
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
        } catch (e: IOException) {
            println("Error Closing File Reader")
            e.printStackTrace()
        }
    }
}

private fun getSurveyList(){
    var fileReader: BufferedReader? = null
    val survID = 0
    val title = 1
    var surv: survey? = null

    try {
        var line: String?


        fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString()+"\\res\\SurveyList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null){
            val tokens = line.split(",")
            if (tokens.size > 0){
                val temp = mutableListOf<question>()
                for (t in 2 until tokens.size){
                    temp.add(getQuestion(tokens[t]) as question)
                }
                surv = survey(tokens[survID], tokens[title], temp)
                surveyList.add(surv)
            }
            line = fileReader.readLine()
        }
    } catch(e: Exception){
        println("Error during Survey Retrieval")
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
        } catch (e: IOException){
            println("Error Closing File Reader")
            e.printStackTrace()
        }
    }
}

fun getSurvey(sID:String): survey?{
    var fileReader: BufferedReader? = null
    val survID = 0
    val title = 1
    var surv: survey? = null

    try {
        var line: String?


        fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString()+"\\res\\SurveyList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null){
            val tokens = line.split(",")
            if (tokens.size > 0 && tokens[survID] == sID){
                val temp = mutableListOf<question>()
                for (t in 2 until tokens.size){
                    temp.add(getQuestion(tokens[t]) as question)
                }
                surv = survey(tokens[survID], tokens[title], temp)
            }
            line = fileReader.readLine()
        }
    } catch(e: Exception){
        println("Error during Survey Retrieval")
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
        } catch (e: IOException){
            println("Error Closing File Reader")
            e.printStackTrace()
        }
    }
    return surv
}

fun getQuestion(qID:String): question?{
    var fileReader: BufferedReader? = null
    val questionID = 0
    val text = 1
    var q: question? = null

    try {
        var line: String?


        fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString()+"\\res\\QuestionList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null){
            val tokens = line.split(",")
            if (tokens.size > 0 && tokens[questionID] == qID){
                val temp = mutableListOf<String>()
                for (t in 2 until tokens.size){
                    temp.add(tokens[t])
                }
                q = question(tokens[questionID], tokens[text], temp)
            }
            line = fileReader.readLine()
        }
    } catch(e: Exception){
        println("Error during Question Retrieval")
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
        } catch (e: IOException){
            println("Error Closing File Reader")
            e.printStackTrace()
        }
    }
    return q
}

fun createSurvey(sID: String,title: String, questions: List<question>){
    if (questionList.isEmpty()) getQuestionList()
    if (surveyList.isEmpty()) getSurveyList()
    surveyList.add(survey(sID, title, questions))
    writeSurveyFile()
}

fun createQuestion(qID: String, text: String, answers: List<String>){
    if (questionList.isEmpty()) getQuestionList()
    questionList.add(question(qID, text, answers))
    writeQuestionFile()
}