package com.opengm.app

import javafx.geometry.Pos
import tornadofx.Stylesheet
import tornadofx.px

class Styles : Stylesheet() {
    init {
        root {
            prefWidth = 400.px
            prefHeight = 200.px
            alignment = Pos.CENTER
        }
    }
}