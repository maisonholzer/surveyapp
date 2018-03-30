package edu.uiowa.cs

/************** Demonstration of Gradle Project for JSON server **************/
// adapted from https://craftsmen.nl/kotlin-create-rest-services-using-jersey-and-jackson/
// which you should read at least to understand what all these things are:
//   Gradle, HTTP, REST, Jersey + Netty, JAX-RS, Serializing, De-serializing,
//   JSON, Jackson, IP address, Port number
// (these will be covered in class, so you can take notes then)

import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Application
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jdk.nashorn.internal.runtime.logging.Logger
import org.glassfish.jersey.jackson.JacksonFeature
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider
import org.glassfish.jersey.server.ResourceConfig
import java.net.URI
import javax.ws.rs.ext.ContextResolver

// The User class was put in a separate file (see User.kt)

// In Java, this would be a separate file for the resource part, this
// is using JAX-RS:
//    recommended reading to understand the annotation (@ stuff)
// https://readlearncode.com/java-ee/what-are-the-jax-rs-annotations-13/
// https://dzone.com/articles/an-introduction-to-jax-rs-annotations-part-1

@Path("users")  // when this runs, try http://localhost:8080/users/Iowa for the "GET" request
@Produces(APPLICATION_JSON)
class UserResource {
    private val users = HashMap<String, User>()

    init {
        // sneaky: the "+=" turns into a call to the put method of
        // the HashMap, which can take a Pair(key,value) as argument
        users += "Iowa" to User("Iowa", "secret", 25)
        // Creating new users to test
        users += "Bob" to User("Bob", "abc", 30)
        users += "Alice" to User ("Alice", "12345", 31)
    }

    @GET @Path("{username}")
    // Test in browser with http://localhost:8080/users/Iowa
    fun getUser(@PathParam("username") username: String): User? {
        println("Get " + "$username")
        return users[username]
        // note the return type is User? which means it will either
        // be null (no user found) or a User object -- but then
        // that user object is "serialized" into JSON, because the
        // annotation above says @Produces(APPLICATION_JSON)
    }

    /** A duplicate of getUser, but with a different @Path **/
    @GET @Path("get/{username}")  // Test in browser with http://localhost:8080/users/get/Iowa
    fun getUser2(@PathParam("username") username: String): User? {
        println("Get2 " + "$username")
        return users[username]
        }


    @POST @Path("create")
    @Consumes(APPLICATION_JSON)
    // Test with unix command:
    // curl -H "Content-Type: application/json" -X POST
    // -d '{"username":"Bob","password":"Mo","age":21}'
    // -L http://localhost:8080/users/create
    fun createUser(user: User) {
        // JAX-RS will take the body (in JSON) and convert
        // into a User object, named as parameter "user" here
        // (it's done using KotlinModule, set up below)
        users += user.username to user
        println("Created: " + "$user")
    }

    @PUT @Path("{username}")
    @Consumes(APPLICATION_JSON)
    fun updateUser(@PathParam("username") username: String, user: User) {
        users -= username
        users += user.username to user
    }

    @DELETE @Path("{username}")
    fun deleteUser(@PathParam("username") username: String): User? {
        return users.remove(username)
    }
}

// In Java, this would be a third file, for the Application
class JerseyApplication: Application() {
    override fun getSingletons(): MutableSet<Any> {
        return mutableSetOf(UserResource())
    }
}

// this shows making main as a "static" method via a Singleton object
// main() creates a JerseyApplication instance (see above), which
// delegates to an instance of UserResource (defined above)
// the run configuration should specify edu.uiowa.cs.NettyServer
// as the location of the main program to run
object NettyServer {
    @JvmStatic
    fun main(args: Array<String>) {
        // val resourceConfig = ResourceConfig.forApplication(JerseyApplication())
        val resourceConfig = ResourceConfig.forApplication(JerseyApplication())
                .register(
                    ContextResolver<ObjectMapper> {
                      ObjectMapper().registerModule(KotlinModule()) }
                    )
        val server = NettyHttpContainerProvider.createHttp2Server(URI.create("http://localhost:8080/"), resourceConfig, null)
        Runtime.getRuntime().addShutdownHook(Thread(Runnable { server.close() }))
    }
}


