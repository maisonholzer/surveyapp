package controller

import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType
import com.fasterxml.jackson.databind.ObjectMapper
import models.*


val surveyList: MutableMap<String, survey> = HashMap()
val questionList: MutableMap<String, question> = HashMap()

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
}