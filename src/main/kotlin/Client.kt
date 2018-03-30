package edu.uiowa.cs

import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.client.ClientRequestContext
import javax.ws.rs.client.ClientRequestFilter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
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

    fun getWebPage(): String {
        val response = client
                .target("https://www.uiowa.edu/index.html")
                .request(MediaType.TEXT_PLAIN)
                .get(String::class.java)
        return response
        }

    fun getJsonUser(name: String): User? {
        return client
                .target(REST_URI)
                .path(name)
                .request(MediaType.APPLICATION_JSON)
                .get(User::class.java)
        }

    fun getJsonUser2(name:String): User? {
        return client
                .target(REST_URI)
                .path("get/$name")
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
    val response0 = client.getJsonUser("Iowa"); println(response0)
    val newuser = User("William", "123", 18)
    val response1 = client.createJsonUser(newuser); println("Create user response $response1")
    val response2 = client.getJsonUser2(newuser.username); println("Fetch William response $response2")

    val response4 = client.getJsonUser("Bob"); println(response4)
      val response5 = client.getJsonUser("Alice"); println(response5)
      val response6 = client.getJsonUser("Alice"); println("Fetch Alice response $response6")
    }
  }