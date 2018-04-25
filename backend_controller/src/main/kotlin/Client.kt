package BackEnd

import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.client.ClientRequestContext
import javax.ws.rs.client.ClientRequestFilter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.net.PasswordAuthentication
import javax.ws.rs.ext.ContextResolver

class LoggingFilter: ClientRequestFilter {
    override fun filter(requestContext: ClientRequestContext) {
        println("URI: " + requestContext.uri)
    }
}

class RestClient {

    private val client = ClientBuilder.newClient()
    init {
        client.register(LoggingFilter()) // for debugging, prints info
        client.register(  // need this for conversion from JSON to Kotlin objects
                ContextResolver<ObjectMapper> {
                    ObjectMapper().registerModule(KotlinModule()) }
        )
    }

    fun getJsonUser(name: String): User? {
        return client
                .target(REST_URI)
                .path(name)
                .request(MediaType.APPLICATION_JSON)
                .get(User::class.java)
    }

    fun createJsonUser(user: User): Response {
        return client
                .target(REST_URI)
                .path("create")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON))

    }

    fun authenticateLogin(user: User, inputUsername: String, inputPassword: String) : Response?{
        if (inputUsername == user.username && inputPassword == user.pass) {
            println("Logging you in...")
            return client
                    .target(REST_URI)
                    .path("{username}/login")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(user, MediaType.APPLICATION_JSON))
        }
        if (inputUsername != user.username) {
            println("Username not found")
            return null
        }
        if (inputUsername != user.username && inputPassword != user.pass) {
            println("Password is incorrect")
            return null
        }
        else {
            println("Error...")
            return null
        }
    }
    
    fun getSurvey(surv: survey): Response {
        return client
                .target(REST_URI)
                .path("{username}/survey")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(surv, MediaType.APPLICATION_JSON))
    }

    fun getQuestion(q: question): Response {
        return client
                .target(REST_URI)
                .path("survey/question")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(q, MediaType.APPLICATION_JSON))
    }
    // create login, authentication, and answering questions
    companion object {
        val REST_URI = "http://localhost:8080/users"
    }
}

// This is the main() for the client. There needs to be
// a run configuration for edu.uiowa.cs.TestClient
object TestClient {
    @JvmStatic
    fun main(args: Array<String>) {
        val client = RestClient();  // the object for REST communication
    }
}