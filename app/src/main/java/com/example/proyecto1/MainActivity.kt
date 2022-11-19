package com.example.proyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import java.io.Serializable
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private lateinit var btnPlay: Button
    private lateinit var btnOption: Button
    private lateinit var spinnerDifficulty: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: QuizViewModel by viewModels()

        btnPlay = findViewById(R.id.btnPlay)
        btnOption = findViewById(R.id.btnOption)
        spinnerDifficulty = findViewById(R.id.spinnerDifficulty)

        ArrayAdapter.createFromResource(
            this,
            R.array.difficulty,
            R.layout.spinner_selected_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
            spinnerDifficulty.adapter = adapter
        }

        btnPlay.setOnClickListener { _ ->
            val intent = Intent(this, GameActivity::class.java)
            val setDifficulty = spinnerDifficulty.selectedItemPosition

            val randomInts =
                generateSequence { Random.nextInt(1 until model.questionsSize) }.distinct().take(
                    model.getMaxQuestions
                ).toSet().toList()

            intent.putExtra(RANDOM_LIST_QUESTION, randomInts as Serializable)
            intent.putExtra(DIFFICULTY_TEXT_EXTRA, setDifficulty)
            startActivity(intent)
        }

        btnOption.setOnClickListener { _ ->
            val intent = Intent(this, OptionActivity::class.java)
            startActivity(intent)
        }

        spinnerDifficulty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@MainActivity,
                    //.selectedItemPosition
                    "Selección: ${adapterView?.getItemAtPosition(position)}",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }
}