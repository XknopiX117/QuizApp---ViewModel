package com.example.proyecto1

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private var questions = mutableListOf<Question>()
    private var currentQuestionIndex = 0
    private var currentListIndex = 0
    private var score = 0
    private var hints = 5
    private var indexList = listOf(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
    private var difficulty = 2
    private var gameInitialize = false
    private var maxQuestionList = 10
    private var streak = 0

    init {
        questions.add(
            Question(
                "¿La invasión de qué fortaleza por parte de los revolucionarios es considerada como el punto de inicio de la Revolución Francesa?",
                "Historia",
                "Bastilla",
                arrayListOf(
                    "Bastilla",
                    "Palacio de Versalles",
                    "El Castillo de Bonaguil",
                    "La ciudadela de Carcasona"
                ),
                false,
            )
        )
        questions.add(
            Question(
                "¿En qué año el hombre pisó la Luna por primera vez?",
                "Historia",
                "1969",
                arrayListOf("1979", "1969", "1959", "1971"),
                false
            )
        )
        questions.add(
            Question(
                "¿ Quién fue el primer presidente de la democracia española tras el franquismo?",
                "Historia",
                "Adolfo Suárez",
                arrayListOf(
                    "Adolfo Suárez",
                    "Leopoldo Calvo",
                    "Felipe González",
                    "José María Aznar"
                ),
                false
            )
        )
        questions.add(
            Question(
                "¿Quién fue el primer presidente de Estados Unidos?",
                "Historia",
                "George Washington",
                arrayListOf("George Washington", "John Adams", "Thomas Jefferson", "James Madison"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cuánto duró la Guerra de los Cien Años?",
                "Historia",
                "116",
                arrayListOf("100", "96", "116", "153"),
                false
            )
        )
        questions.add(
            Question(
                " ¿Cuál es el río más caudaloso del mundo?",
                "Geografía",
                "Amazonas",
                arrayListOf("Nilo", "Missisipi", "Amazonas", "Bravo"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cuál es el monte más alto del mundo?",
                "Geogafía",
                "Everest",
                arrayListOf("Pico de Orizaba", "Everest", "Kilawea", "Olimpo"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cuál es la lengua más hablada del mundo?",
                "Geografía",
                "Chino",
                arrayListOf("Ingles", "Español", "Chino", "Hindi"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cuál es la capital de Brasil?",
                "Geogafía",
                "Brasilia",
                arrayListOf("Río de Janeiro", "Sao Paolo", "Brasilia", "Caracas"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cuál es el país de mayor tamaño del mundo?",
                "Geografía",
                "Rusia",
                arrayListOf("Canada", "Brasil", "Estados Unidos", "Rusia"),
                false
            )
        )
        questions.add(
            Question(
                " ¿Quién fue el famoso cantante del grupo musical Queen?",
                "Entretenimiento",
                "Freddy Mercury",
                arrayListOf("Freddy Mercury", "Bob Dylan", "John Lenon", "Bono"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cómo se llama la madre de Simba en la película de Disney “El Rey León”?",
                "Entretenimiento",
                "Sarabi",
                arrayListOf("Sarabi", "Kimba", "Mufasa", "Kaya"),
                false
            )
        )
        questions.add(
            Question(
                "¿A qué banda de música metal pertenece el disco Master of Puppets?",
                "Entretenimiento",
                "Metallica",
                arrayListOf("Queen", "Kiss", "Metallica", "AC/DC"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cómo se llama la protagonista de la saga de videojuegos \"The Legend of Zelda\"?",
                "Entretenimiento",
                "Link",
                arrayListOf("Zelda", "Ganon", "Link", "Furbo"),
                false
            )
        )
        questions.add(
            Question(
                "¿En qué país transcurre la acción de la película \"Chappie\"?",
                "Entretenimiento",
                "Sudafrica",
                arrayListOf("Francia", "Mexico", "Estados Unidos", "Reino Unido"),
                false
            )
        )
        questions.add(
            Question(
                "¿Quién escribió la Ilíada y la Odisea?",
                "Arte y Literatura",
                "Homero",
                arrayListOf("Odiseo", "Homero", "Antipas", "Herodoto"),
                false
            )
        )
        questions.add(
            Question(
                "¿Quién pintó el “Guernica”?",
                "Arte y Literatura",
                "Pablo Picasso",
                arrayListOf("Miguel Ángel", "Andy Warhol", "Pablo Picasso", "Salvador Dalí"),
                false
            )
        )
        questions.add(
            Question(
                "¿Qué nombre tenía el caballo de Don Quijote de la Mancha?",
                "Arte y Literatura",
                "Rocinante",
                arrayListOf("Blanco", "Rocinante", "Salvador", "Aventurero"),
                false
            )
        )
        questions.add(
            Question(
                " ¿De qué país es originario el tipo de poesía conocido como haiku?",
                "Arte y Literatura",
                "Japón",
                arrayListOf("China", "Corea", "Japón", "India"),
                false
            )
        )
        questions.add(
            Question(
                " ¿Qué personaje del universo literario de Harry Potter tiene una rata llamada Scabbers?",
                "Arte y Literatura",
                "Ron",
                arrayListOf("Harry", "Ron", "Hermione", "Draco"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cuál es el ave de mayor envergadura que sigue viva actualmente?",
                "Ciencias",
                "Albatros",
                arrayListOf("Aguila", "Pelicano", "Buitre", "Albatros"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cómo se llama la planta a partir de la cual suele ser elaborado el tequila?",
                "Ciencias",
                "Agave",
                arrayListOf("Agave", "Maiz", "Pulque", "Cebada"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cómo se llama el tipo de célula nerviosa más abundante en el cerebro humano?",
                "Ciencias",
                "Glía",
                arrayListOf("Glía", "Neuronas", "Fibroblastos", "Plaquetas"),
                false
            )
        )
        questions.add(
            Question(
                "¿Qué nombre recibe el sistema de transcripción fonética usado en el chino mandarín?",
                "Ciencias",
                "Pinyin",
                arrayListOf("Pinyin", "Hiragana", "Katakana", "Ideograma"),
                false
            )
        )
        questions.add(
            Question(
                "¿Cuál es el nombre técnico del miedo o fobia a las alturas?",
                "Ciencias",
                "Acrofobia",
                arrayListOf("Alturafobia", "Acrofobia", "Hirofobia", "Valofobia"),
                false
            )
        )
    }

    val getMaxQuestions: Int
        get() = maxQuestionList

    //Obtener la pregunta
    val currentQuestionString: String
        get() = questions[currentQuestionIndex].text

    val currentQuestionTheme: String
        get() = questions[currentQuestionIndex].theme

    val currentAllAnswers: List<String>
        get() = questions[currentQuestionIndex].allAnswers

    //Obtener la respuesta
    val currentQuestionAnswer: String
        get() = questions[currentQuestionIndex].correctAnswer

    //Obtener si está respondido
    val currentQuestionAnswered: Boolean
        get() = questions[currentQuestionIndex].answered

    //Obtener si se respondió de manera correcta o incorrecta
    val currentQuestionCorrect: Int
        get() = questions[currentQuestionIndex].correctAnswered

    //Cadena de texto para mostrar la pregunta y el total
    val currentQuestionCounter: String
        get() = " ${currentListIndex + 1} / ${indexList.size}"

    var setUsedHint: Boolean
        get() = questions[currentQuestionIndex].fUsedHints
        set(value){
            questions[currentQuestionIndex].fUsedHints = value
        }

    val getUsedHint: Boolean
        get() = questions[currentQuestionIndex].fUsedHints

    //Cadena de texto para mostrar el puntaje
    val currentScore: String
        get() = " $score"

    val currentScoreInt: Int
        get() = score

    //Number of total questions
    val questionsSize: Int
        get() = questions.size

    val actualQuestionIndex: Int
        get() = currentQuestionIndex

    var changeDifficulty: Int
        get() = questions[currentQuestionIndex].difficulty
        set(value) {
            questions[currentQuestionIndex].difficulty = value
        }

    val getDifficulty: Int
        get() = questions[currentQuestionIndex].difficulty

    //Cambiar la pregunta a ya respondida
    var changeAnswered: Boolean
        get() = questions[currentQuestionIndex].answered
        set(value) {
            questions[currentQuestionIndex].answered = value
        }

    val getAnswered: Boolean
        get() = questions[currentQuestionIndex].answered

    //Cambiar la pregunta a respondida correctamente
    var changeCorrectAnswered: Int
        get() = questions[currentQuestionIndex].correctAnswered
        set(value) {
            questions[currentQuestionIndex].correctAnswered = value
        }

    //Aumentar marcador
    var increaseScore: Int
        get() = score
        set(value) {
            score = value
        }

    //Modificar pistas
    var changeHints: Int
        get() = hints
        set(value) {
            hints = value
        }

    var indexQuestionsList: List<Int>
        get() = indexList
        set(value) {
            indexList = value
            currentQuestionIndex = indexList[currentListIndex]
        }

    var streaks: Int
        get() = streak
        set(value){
            streak = value
        }

    var setAllDifficulty: Int
        get() = difficulty
        set(value) {
            difficulty = value
        }

    var usedHintsNumber : Int
        get() = questions[currentQuestionIndex].usedHints
        set(value){
            questions[currentQuestionIndex].usedHints
        }

    //Cambiar de pregunta, aumentando
    fun nextQuestion() {
        //currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        currentListIndex = (currentListIndex + 1) % indexList.size
        currentQuestionIndex = indexList[currentListIndex]
    }

    //Cambiar de pregunta, disminuyendo
    fun prevQuestion() {
        currentListIndex = (currentListIndex - 1) % indexList.size
        if (currentListIndex < 0) currentListIndex = indexList.size - 1
        currentQuestionIndex = indexList[currentListIndex]
    }

    private fun changeAllQuestionsDifficulty() {
        questions.forEach {
            it.difficulty = difficulty
        }
    }

    fun stringDifficulty(difficulty: Int): String {
        var string = ""
        if (difficulty == 0) {
            string = "Fácil"
        } else if (difficulty == 1) {
            string = "Normal"
        } else if (difficulty == 2) {
            string = "Difícil"
        }
        return string
    }

    fun initializeGame(difficulty: Int) {
        if (!gameInitialize) {
            gameInitialize = true
            setAllDifficulty = difficulty
            changeAllQuestionsDifficulty()
        }
    }

    fun checkAllAnswered() : Boolean {
        for (i in indexList){
            if (!questions[i].answered){
                return false
            }
        }
        return true
    }
}