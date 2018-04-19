package view

import javafx.scene.control.ToggleGroup
import tornadofx.*


//simple view layout for main survey window
//borderpane, gridpane, or tabpane are options for more complex layouts

class demo : View("Survey App") {
    val controller = demoControl()
    override val root = vbox {
        label("What year student are you?") {
            style { fontSize = 25.px }
        }


        val toggleGroup = ToggleGroup()



        radiobutton("Freshman", toggleGroup)
        radiobutton("Sophomore", toggleGroup)
        radiobutton("Junior", toggleGroup)
        radiobutton("Senior", toggleGroup)

        //button to make selection
        //showcases option using controller
        button("Enter Selection") {
            action {
                controller.selectOp()
            }
        }

        //option for button layout to switch between previous views
        //showcases option using setOnAction
        hbox {
            button("<--").setOnAction {
                println("back")
            }
            button("-->").setOnAction {
                println("forward")
            }
        }
    }
}

class demoControl : Controller() {
    fun selectOp() {
        //execuses on button press to complete selection
        //could switch views at this point, send data to/from controller, etc.
        println("does something")

    }
}