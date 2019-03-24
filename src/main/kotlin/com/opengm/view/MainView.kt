package com.opengm.view

import javafx.application.Platform
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import tornadofx.*
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess


data class Vehicle(val name: String, val symbol: String, val date: LocalDate)


class MainView : View() {

    var model: Vehicle? = null
    val listVehicles = mutableListOf<Vehicle>()
    var iterator = 1


    init {


        loadVehicles()
        if (listVehicles.isNotEmpty()) {
            model = listVehicles[0]
        }

        if (model == null) {
            exitProcess(0)
        }
    }


    private fun loadVehicles() {
        //File("src/main/resources/vehicles.txt")
        File("vehicles.txt")
                .forEachLine {
                    val line = it.split("|")
                    val vehicle = Vehicle(
                            name = line[0],
                            symbol = line[1],
                            date = LocalDate.parse(line[2])
                    )
                    if (vehicle.date > LocalDate.now() && vehicle.date < (LocalDate.now().plusMonths(2))) {
                        listVehicles.add(vehicle)
                    }
                }
    }


    override val root = vbox {
        hbox {
            text("       ") {
                font = Font(20.0)

            }
            vbox {
                text(model!!.name) {
                    font = Font(20.0)
                    textAlignment = TextAlignment.CENTER
                    fill = Color.DARKRED
                }
                text("s poznávací značkou: '${model!!.symbol}' \n" +
                     "je třeba dát do: '${model!!.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}' \n" +
                     "na technickou kontrolu."
                ) {
                    font = Font(20.0)
                    textAlignment = TextAlignment.LEFT

                }
            }
            text("       ") {
                font = Font(20.0)

            }
        }
        label()
        label()
        button("Close").action {
            if (listVehicles.isEmpty()) {
                Platform.exit()
            }
            if (iterator != listVehicles.size) {
                model = listVehicles[iterator]
                iterator++

                val nameLabel = text(model!!.name) {
                    font = Font(20.0)
                    textAlignment = TextAlignment.CENTER
                    fill = Color.DARKRED
                }
                val describeLabel =
                        text("s poznávací značkou: '${model!!.symbol}' \n" +
                             "je třeba dát do: '${model!!.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}' \n" +
                             "na technickou kontrolu."
                        ) {
                            font = Font(20.0)
                            textAlignment = TextAlignment.LEFT

                        }


                getChildList()!![0].getChildList()!![1].getChildList()!![0] = nameLabel
                getChildList()!![0].getChildList()!![1].getChildList()!![1] = describeLabel

            } else {
                Platform.exit()
            }
        }
    }
}

