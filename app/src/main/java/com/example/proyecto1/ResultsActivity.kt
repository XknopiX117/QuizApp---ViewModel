package com.example.proyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels

const val SCORE_EXTRA = "com.fiuady.score_extra"
const val DIFFICULTY_EXTRA = "com.fiuady.difficulty_extra"

class ResultsActivity : AppCompatActivity() {

    private lateinit var txtPoints: TextView
    private lateinit var imageResult: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val model: QuizViewModel by viewModels()

        val score = intent.getIntExtra(SCORE_EXTRA, 0)

        val difficulty = intent.getIntExtra(DIFFICULTY_EXTRA, 0)

        model.increaseScore = score

        txtPoints = findViewById(R.id.txtPoints)
        imageResult = findViewById(R.id.imageResults)

        txtPoints.text = "Puntuación:" + model.currentScore

        if (difficulty == 0) {
            //score, texto
            if (score in 401..600) {
                imageResult.setImageResource(R.drawable.skiper)
            } else if (score in 201..400) {
                imageResult.setImageResource(R.drawable.tenor)
            } else if (score in 0..200) {
                imageResult.setImageResource(R.drawable.bob1)
            }
        }
        else if (difficulty == 1) {
            if (score in 601..800) {
                imageResult.setImageResource(R.drawable.skiper)
            } else if (score in 401..600) {
                imageResult.setImageResource(R.drawable.tenor)
            } else if (score in 201..400) {
                imageResult.setImageResource(R.drawable.patricio)
            } else if (score in 0..200) {
                imageResult.setImageResource(R.drawable.bob1)
            }
        }
        else if (difficulty == 3) {
            if (score in 751..1000) {
                imageResult.setImageResource(R.drawable.skiper)
            } else if (score in 501..750) {
                imageResult.setImageResource(R.drawable.tenor)
            } else if (score in 251..500) {
                imageResult.setImageResource(R.drawable.patricio)
            } else if (score in 0..250) {
                imageResult.setImageResource(R.drawable.bob1)
            }
        }
    }
}