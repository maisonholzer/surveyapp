package edu.uiowa.cs

/************** Demonstration of Gradle Project for JSON server **************/
// adapted from https://craftsmen.nl/kotlin-create-rest-services-using-jersey-and-jackson/
// which you should read at least to understand what all these things are:
//   Gradle, HTTP, REST, Jersey + Netty, JAX-RS, Serializing, De-serializing,
//   JSON, Jackson, IP address, Port number
// (these will be covered in class, so you can take notes then)

/** Start collecting backend files and getting them to work within our combined app **/
import BackEnd.User
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Application
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider
import org.glassfish.jersey.server.ResourceConfig
import java.net.URI
import javax.ws.rs.ext.ContextResolver
import BackEnd.BackEnd_API


// The User class was put in a separate file (see User.kt)

// In Java, this would be a separate file for the resource part, this
// is using JAX-RS:
//    recommended reading to understand the annotation (@ stuff)
// https://readlearncode.com/java-ee/what-are-the-jax-rs-annotations-13/
// https://dzone.com/articles/an-introduction-to-jax-rs-annotations-part-1

@Path("users")  // when this runs, try http://localhost:8080/users/Iowa for the "GET" request
@Produces(APPLICATION_JSON)
class UserResource: BackEnd_API {
    private val users = HashMap<String, User>()

    init {
        // sneaky: the "+=" turns into a call to the put method of
        // the HashMap, which can take a Pair(key,value) as argument
        users += "Iowa" to User("Iowa", "secret")
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

    @POST @Path("create")
    @Consumes(APPLICATION_JSON)
    fun createUser(user: User) {
        // JAX-RS will take the body (in JSON) and convert
        // into a User object, named as parameter "user" here
        // (it's done using KotlinModule, set up below)
        users += user.username to user
        //createClient(user.username, user.pass)
        newClient(user.username, user.pass)
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

    @Path("surveys")
    @Produces(APPLICATION_JSON)
    class SurveyResource {
        @POST @Path("allsurveys")
        fun getSurveys(): BackEnd_API {

        }
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


