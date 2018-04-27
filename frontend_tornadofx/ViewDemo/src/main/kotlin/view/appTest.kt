package view

import tornadofx.*
import app.Styles.Companion.mainStyle
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.client.ClientRequestContext
import javax.ws.rs.client.ClientRequestFilter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.glassfish.jersey.filter.LoggingFilter
import java.net.PasswordAuthentication
import javax.ws.rs.ext.ContextResolver
import models.User

class appTest : View() {
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
                            createUser(name.getText(),pass.getText())
                        }
                    }
                    button("Log in") {
                        setOnAction {
                            println("does not work in test view")
                        }
                    }
                    //demo buttons, not included in regular app
                    label("Demo buttons")
                    button("Go to Admin View") {
                        setOnAction {
                            replaceWith(adminView::class)
                        }
                    }
                    button("Go to User View") {
                        setOnAction {
                            replaceWith(clientView::class)
                        }
                    }
                }
            }
        }
    }
}

fun createUser(n:String,p: String): Response {
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
