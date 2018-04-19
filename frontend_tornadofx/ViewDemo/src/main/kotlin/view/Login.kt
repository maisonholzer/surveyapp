package view

import controller.localHost
import javafx.geometry.Orientation
import javafx.scene.control.Button
import javafx.scene.control.ProgressIndicator
import models.User
import models.UserModel
import tornadofx.*
import app.Styles
import app.Styles.Companion.login


class login : View("Survey Login") {
    override val root = Form().addClass(login)


    val model = UserModel(User())
    val lh: localHost by inject()

    init {
        //title = "Survey Login"

        with (root) {
            fieldset {
                labelPosition = Orientation.VERTICAL

                field("Username") {
                    textfield(model.username).required()
                }

                field("Password") {
                    passwordfield(model.password).required()
                }
            }

            button("Log in") {
                setOnAction {
                    login()
                }
            }
        }
        fun Button.login() {
            //graphic = ProgressIndicator()

            model.commit()

            if (model.commit()) {
                runAsync {
                    lh.login(model.user)
                } ui { success ->
                    if (success)
                        replaceWith(demo::class)
                    //alert(Alert.AlertType.WARNING, "Login Failed")
                }

            }

        }

    }

}
