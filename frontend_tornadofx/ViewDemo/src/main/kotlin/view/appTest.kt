package view

import tornadofx.*
import app.Styles.Companion.mainStyle
import javafx.geometry.Pos
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.ObjectMapper
import models.*
import models.User
import controller.*
import javafx.scene.Node
import javafx.scene.control.Button

val takenSurveys: MutableMap<String, survey> = HashMap()

class appTest : View() {
    val controller = appControl(this) // controller defined below
    override val root = Form().addClass(mainStyle)
    init {
        //title = "Survey Login"

        with(root) {
            borderpane {
                left = label {
                    prefWidth = 100.00
                }
                right = label {
                    prefWidth = 100.00
                }

                center = vbox {
                    alignment = Pos.CENTER


                    val name = textfield()
                    val pass = passwordfield {

                    }

                    //buttons for login screen
                    button("New User") {
                        action {
                            controller.buttonaction(this, name.text, pass.text)
                        }
                    }
                    button("Login") {
                        action {
                            controller.buttonaction(this, name.text, pass.text)
                        }
                    }
                }
            }
        }
    }

    class appControl(val viewobject:View) : Controller() {
        fun buttonaction(buttonObj:Node, name: String, pass:String) {
            val B = buttonObj as Button
            val text = B.text
            // now take specific action depending on button number
            when {
                (text == "New User") -> {
                    createClient(name, pass)
                }
                (text == "Login") -> {
                    authenticateLogin(name, pass)
                    println("checking")
                    val user = appClient().getJsonUserForLogin(name)
                    println("checked")
                    if (user is Client && user.pass == pass) {
                        println("user is Client and password matches, Logging in")
                        storeClientSurveyData(user)
                        viewobject.replaceWith(find(clientView::class))
                    }
                    else if (user is Admin && user.pass == pass) {
                        println("user is Admin and password matches, Logging in")
                        viewobject.replaceWith(find(adminView::class))
                    }
                    else {
                        println("username/password not a match")
                    }
                }
                else -> {
                    println("not found")
                }
            }
        }
    }
}

fun createClient(n:String,p: String): Response {
    val client = ClientBuilder.newClient()
    val newUser = User(n,p)
    val mapper = ObjectMapper()
    val jsonString = mapper.writeValueAsString(newUser)
    val resp = client
            .target("http://localhost:8080/users")
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
            .target("http://localhost:8080/users")
            .path("login")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(jsonString, MediaType.APPLICATION_JSON))
    return resp
}

fun storeClientSurveyData(user: Client) {
    val userSurveys: MutableMap<String, survey> = HashMap()
    for (item in user.surveys) {
        takenSurveys.put(item.key, item.value)
    }
}