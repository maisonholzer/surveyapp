package view

import tornadofx.*
import app.Styles.Companion.mainStyle
import javafx.geometry.Orientation
import javafx.geometry.Pos

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

                    fieldset {
                        labelPosition = Orientation.VERTICAL

                        field("Username") {
                            textfield()

                        }

                        field("Password") {
                            passwordfield {  }

                        }
                    }

                    //buttons for login screen
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
