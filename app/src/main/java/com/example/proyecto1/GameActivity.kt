package com.example.proyecto1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import kotlin.random.Random

const val DIFFICULTY_TEXT_EXTRA = "com.fiuady.difficulty_text"
const val RANDOM_LIST_QUESTION = "com.fiuady.random_list_question"

class GameActivity : AppCompatActivity(), View.OnClickListener {

    private val model: QuizViewModel by viewModels()

    private lateinit var txtNumberQuestion: TextView
    private lateinit var txtDifficulty: TextView
    private lateinit var txtTheme: TextView
    private lateinit var txtQuestionString: TextView

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btnNext: Button
    private lateinit var btnPrev: Button
    private lateinit var btnHint: Button
    private var hintEasy = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //model.changeDifficulty = intent.getIntExtra(DIFFICULTY_TEXT_EXTRA, 3)
        val difficultyText = model.stringDifficulty(intent.getIntExtra(DIFFICULTY_TEXT_EXTRA, 3))

        txtNumberQuestion = findViewById(R.id.questionNumber)
        txtDifficulty = findViewById(R.id.difficultyChoice)
        txtTheme = findViewById(R.id.theme)
        txtQuestionString = findViewById(R.id.questionString)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btnNext = findViewById(R.id.btnNext)
        btnPrev = findViewById(R.id.btnPrev)
        btnHint = findViewById(R.id.btnHint)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)

        model.initializeGame(intent.getIntExtra(DIFFICULTY_TEXT_EXTRA, 3))

        //Creación de la lista de las 10 preguntas aleatorias
        model.indexQuestionsList = intent.getSerializableExtra(RANDOM_LIST_QUESTION) as List<Int>

        val list: MutableList<String> = model.currentAllAnswers as MutableList<String>
        list.remove(model.currentQuestionAnswer)



        selectAnswers(
            model.getDifficulty,
            model.currentAllAnswers as MutableList<String>,
            model.currentQuestionAnswer,
            model.actualQuestionIndex
        )

        txtDifficulty.text = difficultyText
        txtTheme.text = model.currentQuestionTheme
        txtQuestionString.text = model.currentQuestionString
        txtNumberQuestion.text = getString(R.string.txtQuestionNumber) + model.currentQuestionCounter

        btnHint.text = getString(R.string.btnHint) +" "+ model.changeHints.toString()

        if (model.currentQuestionAnswered) {
            btn1.isEnabled = false
            btn2.isEnabled = false
            btn3.isEnabled = false
            btn4.isEnabled = false

            btn1.setBackgroundColor(Color.rgb(133, 133, 133))
            btn2.setBackgroundColor(Color.rgb(133, 133, 133))
            btn3.setBackgroundColor(Color.rgb(133, 133, 133))
            btn4.setBackgroundColor(Color.rgb(133, 133, 133))

            if (model.currentQuestionCorrect == 1)
                txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
            else if (model.currentQuestionCorrect == 0)
                txtQuestionString.setTextColor(Color.rgb(255, 0, 0))

            if (model.getUsedHint){
                txtQuestionString.setTextColor(Color.rgb(74, 0, 255))
            }
        }

        btnPrev.setOnClickListener { v ->
            btn1.isEnabled = true
            btn2.isEnabled = true
            btn3.isEnabled = true
            btn4.isEnabled = true

            btn1.setBackgroundColor(Color.rgb(251, 170, 255))
            btn2.setBackgroundColor(Color.rgb(251, 170, 255))
            btn3.setBackgroundColor(Color.rgb(251, 170, 255))
            btn4.setBackgroundColor(Color.rgb(251, 170, 255))

            model.prevQuestion()
            txtQuestionString.setTextColor(Color.rgb(0, 0, 0))
            txtNumberQuestion.text = getString(R.string.txtQuestionNumber) + model.currentQuestionCounter
            txtTheme.text = model.currentQuestionTheme
            txtQuestionString.text = model.currentQuestionString

            //model.changeDifficulty = difficultyText as String

            selectAnswers(
                model.getDifficulty,
                model.currentAllAnswers as MutableList<String>,
                model.currentQuestionAnswer,
                model.actualQuestionIndex
            )

            if (model.currentQuestionAnswered) {
                btn1.isEnabled = false
                btn2.isEnabled = false
                btn3.isEnabled = false
                btn4.isEnabled = false

                btn1.setBackgroundColor(Color.rgb(133, 133, 133))
                btn2.setBackgroundColor(Color.rgb(133, 133, 133))
                btn3.setBackgroundColor(Color.rgb(133, 133, 133))
                btn4.setBackgroundColor(Color.rgb(133, 133, 133))

                if (model.currentQuestionCorrect == 1) {
                    txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
                } else if (model.currentQuestionCorrect == 0) {
                    txtQuestionString.setTextColor(Color.rgb(255, 0, 0))
                }

                if (model.getUsedHint){
                    txtQuestionString.setTextColor(Color.rgb(74, 0, 255))
                }

            }

            txtQuestionString.text = model.currentQuestionString
            txtNumberQuestion.text = getString(R.string.txtQuestionNumber) + model.currentQuestionCounter

            if (model.checkAllAnswered()){
                val intent = Intent(this, ResultsActivity::class.java)
                intent.putExtra(SCORE_EXTRA, model.currentScoreInt)
                finish()
                startActivity(intent)
            }
            println(model.checkAllAnswered())
            println(model.getAnswered)
        }

        btnNext.setOnClickListener { v ->
            btn1.isEnabled = true
            btn2.isEnabled = true
            btn3.isEnabled = true
            btn4.isEnabled = true

            btn1.setBackgroundColor(Color.rgb(251, 170, 255))
            btn2.setBackgroundColor(Color.rgb(251, 170, 255))
            btn3.setBackgroundColor(Color.rgb(251, 170, 255))
            btn4.setBackgroundColor(Color.rgb(251, 170, 255))

            model.nextQuestion()
            txtQuestionString.setTextColor(Color.rgb(0, 0, 0))
            txtNumberQuestion.text = getString(R.string.txtQuestionNumber) + model.currentQuestionCounter
            txtTheme.text = model.currentQuestionTheme
            txtQuestionString.text = model.currentQuestionString

            //model.changeDifficulty = difficultyText as String

            selectAnswers(
                model.getDifficulty,
                model.currentAllAnswers as MutableList<String>,
                model.currentQuestionAnswer,
                model.actualQuestionIndex
            )

            if (model.currentQuestionAnswered) {
                btn1.isEnabled = false
                btn2.isEnabled = false
                btn3.isEnabled = false
                btn4.isEnabled = false

                btn1.setBackgroundColor(Color.rgb(133, 133, 133))
                btn2.setBackgroundColor(Color.rgb(133, 133, 133))
                btn3.setBackgroundColor(Color.rgb(133, 133, 133))
                btn4.setBackgroundColor(Color.rgb(133, 133, 133))

                if (model.currentQuestionCorrect == 1) {
                    txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
                } else if (model.currentQuestionCorrect == 0) {
                    txtQuestionString.setTextColor(Color.rgb(255, 0, 0))
                }

                if (model.getUsedHint){
                    txtQuestionString.setTextColor(Color.rgb(74, 0, 255))
                }

            }

            txtQuestionString.text = model.currentQuestionString
            txtNumberQuestion.text = getString(R.string.txtQuestionNumber) + model.currentQuestionCounter

            if (model.checkAllAnswered()){
                val intent = Intent(this, ResultsActivity::class.java)
                intent.putExtra(SCORE_EXTRA, model.currentScoreInt)
                intent.putExtra(DIFFICULTY_EXTRA, model.setAllDifficulty)
                finish()
                startActivity(intent)
            }
        }
        btnHint.setOnClickListener { v ->

            model.setUsedHint = true

            if (model.changeHints > 0 && model.getDifficulty>=0 && !model.changeAnswered) {
                model.streaks =0
                model.changeHints -= 1
                btnHint.text = getString(R.string.btnHint) +" "+ model.changeHints.toString()

                // /*

                list.remove(model.currentQuestionAnswer)
                if (model.getDifficulty == 2) {
                    model.changeDifficulty = 1
                    selectAnswers(
                        model.getDifficulty,
                        model.currentAllAnswers as MutableList<String>,
                        model.currentQuestionAnswer,
                        model.actualQuestionIndex
                    )
                } else if (model.getDifficulty == 1) {
                    model.changeDifficulty = 0
                    selectAnswers(
                        model.getDifficulty,
                        model.currentAllAnswers as MutableList<String>,
                        model.currentQuestionAnswer,
                        model.actualQuestionIndex
                    )
                }else if(model.getDifficulty == 0){
                    model.changeAnswered = true

                    btn1.setBackgroundColor(Color.rgb(133, 133, 133))
                    btn2.setBackgroundColor(Color.rgb(133, 133, 133))
                    btn3.setBackgroundColor(Color.rgb(133, 133, 133))
                    btn4.setBackgroundColor(Color.rgb(133, 133, 133))

                    if (btn1.text == model.currentQuestionAnswer) {
                        model.changeCorrectAnswered = 1
                        txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else if (btn2.text == model.currentQuestionAnswer) {
                        model.changeCorrectAnswered = 1
                        txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }
                    hintEasy = 1
                    correctAnswer()
                }
                // */
            }
        }
    }

    //Funcion que selecciona las respuestas que se muestra en función de la dificultad
    private fun selectAnswers(
        difficultyText: Int,
        currentAllAnswers: MutableList<String>,
        currentQuestionAnswer: String,
        currentQuestionIndex: Int
    ) {
        val list: MutableList<String> = currentAllAnswers
        list.remove(currentQuestionAnswer)
        var newList = listOf(currentQuestionAnswer)

        if (difficultyText == 0) {
            newList += list[0]
            newList = newList.shuffled(Random(currentQuestionIndex))
            btn1.text = newList[0]
            btn2.text = newList[1]
            btn3.visibility = View.INVISIBLE
            btn4.visibility = View.INVISIBLE
        } else if (difficultyText == 1) {
            newList += list[0]
            newList += list[1]
            newList = newList.shuffled(Random(currentQuestionIndex))
            btn1.text = newList[0]
            btn2.text = newList[1]
            btn3.text = newList[2]
            btn3.visibility = View.VISIBLE
            btn4.visibility = View.INVISIBLE

        } else if (difficultyText == 2) {
            newList += list[0]
            newList += list[1]
            newList += list[2]
            newList = newList.shuffled(Random(currentQuestionIndex))
            btn1.text = newList[0]
            btn2.text = newList[1]
            btn3.text = newList[2]
            btn4.text = newList[3]
            btn3.visibility = View.VISIBLE
            btn4.visibility = View.VISIBLE
        }
    }

    override fun onClick(p0: View?) {
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        btn4.isEnabled = false

        btn1.setBackgroundColor(Color.rgb(133, 133, 133))
        btn2.setBackgroundColor(Color.rgb(133, 133, 133))
        btn3.setBackgroundColor(Color.rgb(133, 133, 133))
        btn4.setBackgroundColor(Color.rgb(133, 133, 133))

        model.changeAnswered = true

        when (p0?.id) {
            R.id.btn1 -> {
                if (btn1.text == model.currentQuestionAnswer) {
                    correctAnswer()
                } else {
                    incorrectAnswer()
                }
            }
            R.id.btn2 -> {
                if (btn2.text == model.currentQuestionAnswer) {
                    correctAnswer()
                } else {
                    incorrectAnswer()
                }
            }
            R.id.btn3 -> {
                if (btn3.text == model.currentQuestionAnswer) {
                    correctAnswer()
                } else {
                    incorrectAnswer()
                }
            }
            R.id.btn4 -> {
                if (btn4.text == model.currentQuestionAnswer) {
                    correctAnswer()
                } else {
                    incorrectAnswer()
                }
            }
        }
        println(model.streaks)
        if (model.streaks == 2){
            model.changeHints+=1
            model.streaks = 0
            println(model.changeHints)
            btnHint.text = getString(R.string.btnHint) +" "+ model.changeHints.toString()
        }

    }
    private fun correctAnswer(){
        if (model.getDifficulty == 0) {
            if (hintEasy == 1) model.increaseScore += 40
            else model.increaseScore += 60

        }
        else if (model.getDifficulty == 1) {
            model.increaseScore += 80
        }
        else if (model.getDifficulty == 2) {
            model.increaseScore += 100
        }
        /*
        (0 -2-1)*(3-2*20) = 40
        100-3*1*20=-80
        0-1 (1*20) =80
        0 no penaliza
        -1 penaliza
        -2 penaliza más
         */
        //model.increaseScore++
        model.streaks += 1
        //txtScore.text = model.currentScore
        model.changeCorrectAnswered = 1
        txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
        hintEasy = 0
    }
    private fun incorrectAnswer(){
        model.streaks = 0
        txtQuestionString.setTextColor(Color.rgb(255, 0, 0))
        model.changeCorrectAnswered = 0
        Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
    }

}