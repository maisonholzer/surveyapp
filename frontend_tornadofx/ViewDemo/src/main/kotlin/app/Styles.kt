package app

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import tornadofx.*
import java.awt.Color
import java.awt.Color.black
import java.awt.Paint

class Styles : Stylesheet() {
    companion object {
        val mainStyle by cssclass()
        val adminStyle by cssclass()
        val inStyle by cssclass()
        val botStyle by cssclass()
        val sideStyle by cssclass()

        val appWidth = 600.px
        val appHeight = 600.px
        val butWidthL = 200.px
        val butWidthS = 110.px
        val borColor = c("#171515")
        val backColor = c("#E1DFE5")
    }

    init {
        botStyle {
            padding = box(25.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            borderColor += box(borColor)
            backgroundColor += backColor

            button {

            }
        }
        sideStyle {
            padding = box(25.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            borderColor += box(borColor)
            backgroundColor += backColor

            button {

            }
        }
        inStyle {
            padding = box(25.px)
            fontSize = 25.px
            fontWeight = FontWeight.BOLD
            prefWidth = 200.px
            prefHeight = 500.px
            borderColor += box(borColor)
            backgroundColor += backColor

            button {
                prefWidth = butWidthS
                alignment = Pos.CENTER

            }
        }
        form and adminStyle {
            padding = box(25.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            prefWidth = appWidth
            prefHeight = appHeight
            button {
                prefWidth = butWidthS
                alignment = Pos.CENTER

            }
        }
        form and mainStyle {
            padding = box(25.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            prefWidth = appWidth
            prefHeight = appHeight
            button {
                prefWidth = butWidthL
                alignment = Pos.CENTER

            }
            progressIndicator {
                prefWidth = 16.px
                prefHeight = prefWidth

            }
        }
    }
}