package edu.uiowa.cs

/** Start collecting backend files and getting them to work within our combined app **/
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Application
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider
import org.glassfish.jersey.server.ResourceConfig
import java.net.URI
import javax.ws.rs.ext.ContextResolver
import BackEnd.*

// The User class was put in a separate file (see User.kt)

// In Java, this would be a separate file for the resource part, this
// is using JAX-RS:
//    recommended reading to understand the annotation (@ stuff)
// https://readlearncode.com/java-ee/what-are-the-jax-rs-annotations-13/
// https://dzone.com/articles/an-introduction-to-jax-rs-annotations-part-1

@Path("users")  // when this runs, try http://localhost:8080/users/Iowa for the "GET" request
@Produces(APPLICATION_JSON)
class UserResource: BackEnd_API {
    private val users = HashMap<String, Any>()
    private val surveys = HashMap<String, Any>()
    private val questions = HashMap<String, Any>()

    init {
        users += getAllUsersList()
        surveys += getAllSurveys()
        questions += getAllQuestions()
    }

    @GET @Path("{username}")
    @Produces(APPLICATION_JSON)
    fun getUser(@PathParam("username") username: String): Any? {
        return users[username]
        // note the return type is Any? which means it will either
        // be null (no user found) or a User object -- but then
        // that user object is "serialized" into JSON, because the
        // annotation above says @Produces(APPLICATION_JSON)
    }

    @POST @Path("create")
    @Consumes(APPLICATION_JSON)
    fun createClient(user: User) {
        // JAX-RS will take the body (in JSON) and convert
        // into a Client object, named as parameter "user" here
        // (it's done using KotlinModule, set up below)
        if (usernameAvailable(user.username)) {
            newClient(user.username, user.pass)
            println("Created: " + user.username)
            users.put(user.username, Client(user.username, user.pass))
        }
        else {
            println("username ${user.username} taken")
        }
    }

    @POST @Path("login")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun authenticate(user: User): Any? {
        println("received user")
        println("authenticating and sending user")
        if (ClientList.get(user.username)!!.username == user.username && ClientList.get(user.username)!!.pass == user.pass) {
            println("user is client, sending back")
            return user as Client
        }
        else if (AdminList.get(user.username)!!.username == user.username && ClientList.get(user.username)!!.pass == user.pass) {
            println("user is admin, sending back")
            return user as Admin
        }
        else {
            println("no user found that matches")
            return null
        }
    }

    /*** For some reason, my updateUser function is throwing an error.
     *   However, we don't need it, so it will be left out.       ***/
    /*
    @PUT @Path("{username}")
    @Consumes(APPLICATION_JSON)
    fun updateUser(@PathParam("username") username: String, user: Any) {
        when (user) {
            is Admin -> updateUser(user as Admin)
            is Client -> updateUser(user as Client)
            else -> println("No user found to update...")
        }
    }
    */
    @DELETE @Path("{username}")
    fun deleteUser(@PathParam("username") username: String) {
        //return users.remove(username)
    }

    @GET @Path("survey/{survey}")
    @Produces(APPLICATION_JSON)
    fun getSurveys(@PathParam("survey") sID: String): Any? {
        return surveys[sID]
    }

    @GET @Path("question/{question}")
    fun getQuestion(@PathParam("question") qID: String): Any? {
        return questions[qID]
    }

    @POST @Path("answer")
    @Consumes(APPLICATION_JSON)
    fun getQuestionAnswer(stringToRead: Pair<String, String>) {
        val qID: String = stringToRead.first
        val answer: String = stringToRead.second
        println("Question asked: $qID; answer: $answer")
        // backend call
    }

    @POST @Path("numbers")
    @Produces(APPLICATION_JSON)
    fun sendNumberData(numberList: MutableList<Int>): List<Int> {
        numberList.add(getAllAdminList().size)
        numberList.add(getAllClientList().size)
        numberList.add(surveys.size)
        return numberList
    }

    @POST @Path("newquestion")
    @Consumes(APPLICATION_JSON)
    fun getNewQuestion(stringToRead: Pair<BackEnd.survey, BackEnd.question>) {
        val surv: BackEnd.survey = stringToRead.first
        val q: BackEnd.question = stringToRead.second
        addQuestionToSurvey(surv, q)
    }

    @POST @Path("deletequestion")
    @Consumes(APPLICATION_JSON)
    fun deleteQuestion(stringToRead: Pair<BackEnd.survey, BackEnd.question>) {
        val surv: BackEnd.survey = stringToRead.first
        val q: BackEnd.question = stringToRead.second
        removeQuestionFromSurvey(surv, q)
    }

    @POST @Path("addanswer")
    @Consumes(APPLICATION_JSON)
    fun getNewAnswer(stringToRead: Pair<BackEnd.question, String>) {
        val q: BackEnd.question = stringToRead.first
        val answer: String = stringToRead.second
        addAnswerToQuestion(q, answer)
    }

    @POST @Path("deleteanswer")
    @Consumes(APPLICATION_JSON)
    fun deleteAnswer(stringToRead: Pair<BackEnd.question, String>) {
        val q: BackEnd.question = stringToRead.first
        val answer: String = stringToRead.second
        removeAnswerFromQuestion(q, answer)
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


