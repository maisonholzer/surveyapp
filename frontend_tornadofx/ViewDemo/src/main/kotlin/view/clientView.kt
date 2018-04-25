package view

import models.User
import tornadofx.*
import app.Styles.Companion.adminStyle
import app.Styles.Companion.inStyle
import javafx.scene.control.ToggleGroup

class clientView : View() {
    override val root = Form().addClass(adminStyle)

    //values for testing
    var name: String = "Survey Question Here"
    var a1: String = "Answer One"
    var a2: String = "Answer Two"
    var a3: String = "Answer Three"
    var a4: String = "Answer Four"

    init {

        with (root) {
            borderpane {
                top = label("Client") {

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


                    label(name)

                    val tg = ToggleGroup()

                    vbox {
                        radiobutton(a1, tg)
                        radiobutton(a2, tg)
                        radiobutton(a3, tg)
                        radiobutton(a4, tg)

                    }
                    hbox {
                        button("<-")
                        button("->")
                    }



                }
                left = vbox {
                    button("New") {
                        action {


                        }
                    }
                    button("Next") {
                        action {

                        }
                    }
                    button("Log Out") {
                        action {

                        }
                    }
                }
            }
        }
    }
}