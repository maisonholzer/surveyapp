package view

import javafx.geometry.Orientation
import javafx.scene.control.Button
import javafx.scene.control.ToggleGroup
import tornadofx.*
import app.Styles.Companion.adminStyle
import app.Styles.Companion.inStyle
import app.Styles.Companion.sideStyle
import app.Styles.Companion.botStyle
import controller.appClient
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import models.User
import models.*


class adminView : View() {
    override val root = Form().addClass(adminStyle)

    val v1: adminV by inject()
    val v2: clientV by inject()
    val v3: surveyV by inject()

    init {
        //title = "Survey Login"

        with (root) {
            borderpane {
                top = label("Admin Options") {

                }
                bottom = vbox {
                    button("back") {
                        action {
                            replaceWith(appTest::class)
                        }
                    }
                }
                center = vbox {
                    addClass(inStyle)

                    label("Select from menu on left")

                }
                left = vbox {
                    button("Admin") {
                        action {
                            center = v1.root
                        }
                    }
                    button("Clients") {
                        action {
                            center = v2.root
                        }
                    }
                    button("Surveys") {
                        action {
                            center = v3.root
                        }
                    }
                }
            }
        }
    }
}
//inject views
class adminV: View() {
    override val root = tabpane {

        tab("Home", VBox()) {
            label("Admin: 0\n" + //should be able to connect AdminList.count() instead of '0'
                    "Clients: 0\n" +
                    "Surveys: 0")
        }
        tab("Admin List", VBox()) {
            tableview<User> {

                //items = listOf().obervable this will be a list gathered from controller
                //hardcoded demo values currently in place
                items = listOf(
                        User("admin1", "pass1"),
                        User("admin2", "pass2"),
                        User("admin3", "pass3")
                ).observable()

                column("Name", User::username)
            }
        }
    }
}
class clientV: View() {
    override val root = tabpane {

        tab("Client List", VBox()) {
            tableview<User> {

                //items = listOf().obervable this will be a list gathered from controller
                //hardcoded demo values currently in place
                items = listOf(
                        User("client1", "pass1"),
                        User("client2", "pass2"),
                        User("client3", "pass3")
                ).observable()

                column("Name", User::username)
            }

        }
        tab("Create User", VBox()) {

        }
    }
}

class surveyV: View() {
    override val root = tabpane {

        tab("Survey List", VBox()) {
            tableview<User> {

                //items = listOf().obervable this will be a list gathered from controller
                //hardcoded demo values currently in place
                items = listOf(
                        User("name1", "pass1"),
                        User("name2", "pass2"),
                        User("name3", "pass3")
                ).observable()

                column("Name", User::username)
            }
        }
        tab("Create Client", VBox()) {

        }
    }
}