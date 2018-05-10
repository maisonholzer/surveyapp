package view

import models.User
import tornadofx.*
import app.Styles.Companion.adminStyle
import app.Styles.Companion.inStyle
import com.fasterxml.jackson.databind.ObjectMapper
import controller.appClient
import controller.surveyList
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.ToggleGroup
import models.Admin
import models.Client
import models.survey
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

var newSurveyList = surveyList
var questionnumber: Int = 0
var surveynumber: Int = 1
//values for testing
var name: String = "Survey Title"
var qtitle: String = "Question Title"
var a1: String = "Answer One"
var a2: String = "Answer Two"
var a3: String = "Answer Three"

class clientView : View() {
    val controller = clientView.appControl(this) // controller defined below
    override val root = Form().addClass(adminStyle)

    init {
        //show first survey to start
        appClient().getSurveys()
        if (!newSurveyList.isEmpty()) {
            var surveyToTake = newSurveyList.get("s"+surveynumber)
            name = surveyToTake!!.title
            qtitle = surveyToTake.questions[questionnumber].text
            a1 = surveyToTake.questions[questionnumber].answers[0]
            a2 = surveyToTake.questions[questionnumber].answers[1]
            a3 = surveyToTake.questions[questionnumber].answers[2]
        }
        with (root) {
            borderpane {
                top = label("Client") {
                }
                bottom = vbox {
                    button("Log out") {
                        action {
                            controller.buttonaction(this)
                        }
                    }
                }
                center = vbox {
                    addClass(inStyle)



                    var nameLabel = label(name)
                    var qLabel = label(qtitle)

                    vbox {
                        var b1 = button(a1) {

                            setOnAction { println("button $a1 pressed"); appClient().sendQuestionData(qtitle, a1) }
                        }
                        var b2 = button(a2) {
                            setOnAction { println("button $a2 pressed"); appClient().sendQuestionData(qtitle, a2) }
                        }
                        var b3 = button(a3) {
                            setOnAction { println("button $a3 pressed"); appClient().sendQuestionData(qtitle, a3) }
                        }
                        button("Next ???") {
                            action { controller.buttonaction(this)

                                //refreshes button text values to reflect properly when clicked -blake
                                nameLabel.text = name
                                qLabel.text = qtitle
                                b1.text = a1
                                b2.text = a2
                                b3.text = a3}
                        }
                        button("Next Survey") {
                            //isVisible = false
                            action { controller.buttonaction(this)
                                //refreshes button text values to reflect properly when clicked -blake
                                nameLabel.text = name
                                qLabel.text = qtitle
                                b1.text = a1
                                b2.text = a2
                                b3.text = a3}


                        }
                    }
                }
                left = vbox {

                }
            }
        }
    }

    class appControl(val viewobject:View) : Controller() {
        fun buttonaction(buttonObj: Node) {
            val B = buttonObj as Button
            val text = B.text

            // now take specific action depending on button number
            when {
                (text == "Log out") -> {
                    viewobject.replaceWith(find(appTest::class))
                }
                (text == "Next Survey") -> {
                    try {
                        surveynumber += 1
                        questionnumber = 0
                        var surveyToTake = newSurveyList.get("s"+surveynumber)
                        name = surveyToTake!!.title
                        qtitle = surveyToTake.questions[questionnumber].text
                        a1 = surveyToTake.questions[questionnumber].answers[0]
                        a2 = surveyToTake.questions[questionnumber].answers[1]
                        a3 = surveyToTake.questions[questionnumber].answers[2]
                    } catch (ex: Exception) {
                        println("no more surveys")
                        surveynumber -= 1
                    }
                }
                (text == "Next ???") -> {
                    try {
                        questionnumber += 1
                        var surveyToTake = newSurveyList.get("s"+surveynumber)
                        name = surveyToTake!!.title
                        qtitle = surveyToTake.questions[questionnumber].text
                        a1 = surveyToTake.questions[questionnumber].answers[0]
                        a2 = surveyToTake.questions[questionnumber].answers[1]
                        a3 = surveyToTake.questions[questionnumber].answers[2]
                        println(questionnumber)
                    } catch (ex: Exception) {
                        println("no more questions")
                        questionnumber -= 1
                    }
                }
            }
        }
    }
}