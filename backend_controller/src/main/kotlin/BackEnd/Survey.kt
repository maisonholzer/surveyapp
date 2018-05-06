
package BackEnd

import java.io.BufferedReader
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Paths

val questionList: MutableMap<String,question> = readQuestionFile()
val surveyList: MutableMap<String, survey> = readSurveyFile()
val summaryList: MutableMap<String,ResultSummary> = readSummaryFile()

class survey {
    val sID: String
    val title: String
    val questions: MutableList<question>
    constructor(sID: String, title: String, questions: MutableList<question>) {
        this.sID = sID; this.title = title; this.questions = questions
    }
    constructor() {
        this.sID = ""; this.title = ""; this.questions = emptyList<question>() as MutableList<question>
    }
    fun addSurveyQuestion(q: question){
        if (!questions.contains(q)) questions.add(q)
    }

    fun removeQuestion(q: question){
        if (questions.contains(q)) questions.remove(q)
    }
}

class question {
    val qID: String
    val text: String
    val answers: MutableList<String>
    constructor(qID: String, text: String, answers: MutableList<String>) {
        this.qID = qID; this.text = text; this.answers = answers
    }
    constructor() {
        this.qID = ""; this.text = ""; this.answers = emptyList<question>() as MutableList<String>
    }
    fun addAnswer(answer: String){
        answers.add(answer)
    }

    fun removeAnswer(answer: String){
        answers.remove(answer)
    }
}

class ResultSummary {
    val surveyID: String
    // surveySummary value is mutableMapOf<Question ID, MutableMap<Answer , # of times it has been chosen>>
    val surveySummary = mutableMapOf<String,MutableMap<Any,Int>>()

    constructor(surveyID: String){
        this.surveyID = surveyID
    }

    fun createResultsSummary(surveyID: String){
        ResultSummary(surveyID)
        if (!surveyList.get(surveyID)!!.questions.isEmpty()){
            populateSummary(surveyID)
        }
    }

    fun populateSummary(surveyID:String){
        for (question in surveyList.get(surveyID)!!.questions){
            val tempMap = mutableMapOf<Any,Int>()
            for (answer in question.answers){
                tempMap.put(answer,0)
            }
        }
    }

    fun addQuestionResult(qID: String, answer: Any, value: Int){
        if (!surveySummary.containsKey(qID)) {
            val tempMap = mutableMapOf<Any, Int>(Pair<Any,Int>(answer,value))
            surveySummary.put(qID, tempMap)
        }else surveySummary[qID]!!.put(answer,value)
    }
    fun addQuestionToSummary(newQuestion: question) {
        val tempMap = mutableMapOf<Any, Int>()
        for (answer in newQuestion.answers) tempMap.put(answer, 0)
        surveySummary.put(newQuestion.qID, tempMap)
    }

    fun updateSummaryResults(results: SurveyResults){
        val tempSummary = summaryList[results.surveyID]!!.surveySummary
        for (question in results.surveyResults){
            val oldVal:Int = tempSummary.get(question.key)?.getValue(question.value) as Int + 1
            tempSummary.get(question.key)!!.replace(question.value,oldVal)
        }
        writeSummaryFile()
    }
}

class SurveyResults {
    val surveyID: String
    val surveyResults = mutableMapOf<String,Any>()

    constructor(surveyID:String){
        this.surveyID = surveyID
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
                surv = survey(tokens[survID], tokens[title], temp)
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

fun readSummaryFile(): MutableMap<String,ResultSummary>{
    val tempList = mutableMapOf<String,ResultSummary>()
    var fileReader: BufferedReader? = null
    val survID = 0
    val questionID = 1
    val answer = 2
    val value = 3
    var summary: ResultSummary? = null

    try {
        var line: String?

        // Determines which OS is being used because path structure for Windows is different than Mac.
        if (System.getProperty("os.name").split(" ")[0] == "Windows") {
            fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString() + "\\res\\SummaryList.csv"))
        }else fileReader = BufferedReader(FileReader(Paths.get("").toAbsolutePath().toString() + "/res/SummaryList.csv"))

        fileReader.readLine()

        line = fileReader.readLine()
        while (line != null && line != ""){
            val tokens = line.split(",")
            if (tokens.size > 0){
                val temp = mutableListOf<question>()
                if (!tempList.containsKey(tokens[0])){
                    summary = ResultSummary(tokens[survID])
                    summary.addQuestionResult(tokens[questionID], tokens[answer], tokens[value].toInt())
                    tempList.put(summary.surveyID,summary)
                }else {
                    tempList.get(tokens[survID])!!.addQuestionResult(tokens[questionID],tokens[answer],tokens[value].toInt())
                }
            }
            line = fileReader.readLine()
        }
    } catch(e: Exception){
        println("Error during Summary File Read")
        e.printStackTrace()
    } finally {
        try {
            fileReader!!.close()
        } catch (e: IOException){
            println("Error Closing Summary File Reader")
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
            for (q in a.value.questions){
                fileWriter.append(',')
                fileWriter.append(q.qID.toString())
            }
            fileWriter.append('\n')
        }

    } catch (e:Exception){
        println("Error occurred while writing Survey File")
        e.printStackTrace()
    } finally {
        try {
            fileWriter!!.flush()
            fileWriter.close()
        } catch (e: IOException){
            println("Closing Error during Survey File Write")
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

private fun writeSummaryFile(){
    val header = "Survey ID, Question ID, Answers, # of times chosen"
    var fileWriter: FileWriter? = null

    try {
        // Checks OS(Windows or Mac) to use correct path structure.
        if (System.getProperty("os.name").split(" ")[0] == "Windows") {
            fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString() + "\\res\\SummaryList.csv")
        }else fileWriter = FileWriter(Paths.get("").toAbsolutePath().toString() + "/res/SummaryList.csv")

        fileWriter.append(header)
        fileWriter.append('\n')

        for (a in summaryList){
            for (q in a.value.surveySummary){
                for (answer in q.value) {
                    fileWriter.append(a.key)
                    fileWriter.append(',')
                    fileWriter.append(q.key)
                    fileWriter.append(',')
                    fileWriter.append(answer.key.toString())
                    fileWriter.append(',')
                    fileWriter.append(answer.value.toString())
                    fileWriter.append('\n')
                }
            }
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
/*
    val fakeResult = SurveyResults("s1")
    fakeResult.surveyResults.put("q1","yes")
    fakeResult.surveyResults.put("q2","maybe")

    summaryList[fakeResult.surveyID]?.updateSummary(fakeResult)

    for (q in summaryList["s1"]!!.surveySummary){
        println(q.key)
        for (a in q.value){
            println("The answer '" + a.key.toString() + "' has been chosen " + a.value.toString() + " times.")
        }

    }
*/

}