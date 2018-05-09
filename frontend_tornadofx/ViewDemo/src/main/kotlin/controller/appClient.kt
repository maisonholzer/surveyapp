package controller

import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType
import com.fasterxml.jackson.databind.ObjectMapper
import models.*
import javax.ws.rs.client.ClientRequestContext
import javax.ws.rs.client.ClientRequestFilter
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Response


val surveyList: MutableMap<String, survey> = HashMap()
val questionList: MutableMap<String, question> = HashMap()

class LoggingFilter: ClientRequestFilter {
    override fun filter(requestContext: ClientRequestContext) {
        println("URI: " + requestContext.uri)
    }
}

class appClient {
    val REST_URI = "http://localhost:8080/users"
    private val client = ClientBuilder.newClient()
    init { client.register(LoggingFilter())}

    fun getJsonUserForLogin(name: String): Any? {
        println("getting user")
        try {
            val jresponse = client
                    .target(REST_URI)
                    .path(name)
                    .request(MediaType.APPLICATION_JSON)
                    .get()
            // jresponse is a JAX response object
            val jstring = jresponse.readEntity(String::class.java)
            // jstring is a JSON string from the response
            val mapper = ObjectMapper()
            val userObject = mapper.readValue(jstring, Admin::class.java)
            return userObject
        } catch (ex: Exception) {
            println("user not an admin")
        }
        try {
            val jresponse = client
                    .target(REST_URI)
                    .path(name)
                    .request(MediaType.APPLICATION_JSON)
                    .get()
            // jresponse is a JAX response object
            val jstring = jresponse.readEntity(String::class.java)
            // jstring is a JSON string from the response
            val mapper = ObjectMapper()
            val userObject = mapper.readValue(jstring, Client::class.java)
            return userObject
        } catch (ex: Exception) {
            println("user not a client")
        }
        return null
    }

    fun getSurveys() {
        var surveyCount = 1
        var gettingSurveys = true
        while (gettingSurveys) {
            try {
                val jresponse = client
                        .target(REST_URI)
                        .path("survey/s"+surveyCount)
                        .request(MediaType.APPLICATION_JSON)
                        .get()
                val jstring = jresponse.readEntity(String::class.java)
                val mapper = ObjectMapper()
                val surveyObject = mapper.readValue(jstring, survey::class.java)
                surveyList.put(surveyObject.sID, surveyObject)
                println("got a survey")
                surveyCount += 1
            } catch (ex: Exception) {
                println("no survey to get")
                gettingSurveys = false
            }
        }

    }

    fun getQuestions() {
        var questionCount = 1
        var gettingquestions = true
        while (gettingquestions) {
            try {
                val jresponse = client
                        .target(REST_URI)
                        .path("question/q"+questionCount)
                        .request(MediaType.APPLICATION_JSON)
                        .get()
                val jstring = jresponse.readEntity(String::class.java)
                val mapper = ObjectMapper()
                val surveyObject = mapper.readValue(jstring, question::class.java)
                questionList.put(surveyObject.qID, surveyObject)
                println("got a question")
                questionCount += 1
            } catch (ex: Exception) {
                println("no question to get")
                gettingquestions = false
            }
        }
    }

    fun createClient(n:String,p: String): Response {
        val client = ClientBuilder.newClient()
        val newUser = User(n,p)
        val mapper = ObjectMapper()
        val jsonString = mapper.writeValueAsString(newUser)
        val resp = client
                .target(REST_URI)
                .path("create")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jsonString, MediaType.APPLICATION_JSON))
        return resp
    }

    fun authenticateLogin(n: String, p: String): Response {
        val client = ClientBuilder.newClient()
        val newUser = User(n,p)
        val mapper = ObjectMapper()
        val jsonString = mapper.writeValueAsString(newUser)
        val resp = client
                .target(REST_URI)
                .path("login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jsonString, MediaType.APPLICATION_JSON))
        return resp
    }

    fun sendQuestionData(qID: String, answer: String): Response {
        val client = ClientBuilder.newClient()
        val questionanswer = Pair(qID, answer)
        val mapper = ObjectMapper()
        val jsonString = mapper.writeValueAsString(questionanswer)
        val resp = client
                .target(REST_URI)
                .path("answer")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jsonString, MediaType.APPLICATION_JSON))
        return resp
    }

    fun getNumberData(): List<*> {
        val jresponse = client
                .target(REST_URI)
                .path("numbers")
                .request(MediaType.APPLICATION_JSON)
                .get()
        val jstring = jresponse.readEntity(String::class.java)
        val mapper = ObjectMapper()
        val numberList = mapper.readValue(jstring, List::class.java)
        return numberList
    }

    fun sendNewQuestion(s: survey, q: question): Response {
        val client = ClientBuilder.newClient()
        val newquestion = Pair(s, q)
        val mapper = ObjectMapper()
        val jsonString = mapper.writeValueAsString(newquestion)
        val resp = client
                .target(REST_URI)
                .path("newquestion")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jsonString, MediaType.APPLICATION_JSON))
        return resp
    }

    fun deleteQuestion(s: survey, q: question): Response {
        val client = ClientBuilder.newClient()
        val deletequestion = Pair(s, q)
        val mapper = ObjectMapper()
        val jsonString = mapper.writeValueAsString(deletequestion)
        val resp = client
                .target(REST_URI)
                .path("deletequestion")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jsonString, MediaType.APPLICATION_JSON))
        return resp
    }

    fun addAnswerToQuestion(q: question, answer: String): Response {
        val client = ClientBuilder.newClient()
        val newanswer = Pair(q, answer)
        val mapper = ObjectMapper()
        val jsonString = mapper.writeValueAsString(newanswer)
        val resp = client
                .target(REST_URI)
                .path("addanswer")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jsonString, MediaType.APPLICATION_JSON))
        return resp
    }

    fun deleteAnswerFromQuestion(q: question, answer: String): Response {
        val client = ClientBuilder.newClient()
        val deleteanswer = Pair(q, answer)
        val mapper = ObjectMapper()
        val jsonString = mapper.writeValueAsString(deleteanswer)
        val resp = client
                .target(REST_URI)
                .path("deleteanswer")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jsonString, MediaType.APPLICATION_JSON))
        return resp
    }
}