package com.example.quizimages

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

data class Question(
    val text: String,
    val imageResId: Int,
    val choices: Array<String>,
    val correctAnswerIndex: Int,
    val imageDescription: String
)

class MainActivity : Activity() {

    private lateinit var txtScore: TextView
    private lateinit var txtQuestionNumber: TextView
    private lateinit var txtQuestion: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var imgQuestion: ImageView
    private lateinit var progressQuiz: ProgressBar
    private lateinit var answerButtons: List<Button>
    private lateinit var btnNext: Button
    private lateinit var txtResult: TextView
    private lateinit var btnReplay: Button

    private var currentQuestionIndex = 0
    private var score = 0
    private var hasAnswered = false

    // Exactement cinq questions statiques, chacune avec trois choix.
    private val questions = arrayOf(
        Question(
            "Quelle forme géométrique voyez-vous sur l'image ?",
            R.drawable.triangle,
            arrayOf("Carré", "Triangle", "Cercle"),
            1,
            "Un triangle"
        ),
        Question(
            "Combien de côtés possède cette forme ?",
            R.drawable.carre,
            arrayOf("Quatre", "Trois", "Cinq"),
            0,
            "Un carré"
        ),
        Question(
            "Quelle forme n'a aucun côté droit ?",
            R.drawable.cercle,
            arrayOf("Rectangle", "Triangle", "Cercle"),
            2,
            "Un cercle"
        ),
        Question(
            "Quelle forme est représentée ?",
            R.drawable.etoile,
            arrayOf("Étoile", "Cercle", "Carré"),
            0,
            "Une étoile"
        ),
        Question(
            "Quelle forme possède quatre angles droits et deux côtés longs ?",
            R.drawable.rectangle,
            arrayOf("Carré", "Rectangle", "Triangle"),
            1,
            "Un rectangle"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtScore = findViewById(R.id.txt_score)
        txtQuestionNumber = findViewById(R.id.txt_question_number)
        txtQuestion = findViewById(R.id.txt_question)
        txtFeedback = findViewById(R.id.txt_feedback)
        imgQuestion = findViewById(R.id.img_question)
        progressQuiz = findViewById(R.id.progress_quiz)
        answerButtons = listOf(
            findViewById(R.id.btn_choix1),
            findViewById(R.id.btn_choix2),
            findViewById(R.id.btn_choix3)
        )
        btnNext = findViewById(R.id.btn_next)
        txtResult = findViewById(R.id.txt_result)
        btnReplay = findViewById(R.id.btn_replay)

        answerButtons.forEachIndexed { index, button ->
            button.setOnClickListener { checkAnswer(index) }
        }

        btnNext.setOnClickListener { goToNextQuestion() }
        btnReplay.setOnClickListener { restartQuiz() }

        showQuestion()
    }

    private fun showQuestion() {
        val question = questions[currentQuestionIndex]

        txtScore.text = "Score : $score / ${questions.size}"
        txtQuestionNumber.text =
            "Question ${currentQuestionIndex + 1} sur ${questions.size}"
        txtQuestion.text = question.text
        imgQuestion.setImageResource(question.imageResId)
        imgQuestion.contentDescription = question.imageDescription

        question.choices.forEachIndexed { index, choice ->
            answerButtons[index].text = choice
            answerButtons[index].isEnabled = true
            answerButtons[index].visibility = View.VISIBLE
        }

        txtFeedback.text = ""
        txtFeedback.visibility = View.GONE
        progressQuiz.progress = currentQuestionIndex
        btnNext.visibility = View.GONE
        hasAnswered = false
    }

    private fun checkAnswer(selectedIndex: Int) {
        if (hasAnswered) return

        hasAnswered = true
        val question = questions[currentQuestionIndex]
        val isCorrect = selectedIndex == question.correctAnswerIndex

        if (isCorrect) {
            score++
            txtFeedback.text = "Bonne réponse ! Bravo."
        } else {
            txtFeedback.text =
                "Mauvaise réponse. La bonne réponse était : " +
                question.choices[question.correctAnswerIndex]
        }

        txtScore.text = "Score : $score / ${questions.size}"
        progressQuiz.progress = currentQuestionIndex + 1
        txtFeedback.visibility = View.VISIBLE

        answerButtons.forEach { it.isEnabled = false }

        btnNext.text = if (currentQuestionIndex == questions.lastIndex) {
            "Voir le résultat"
        } else {
            "Question suivante"
        }
        btnNext.visibility = View.VISIBLE
    }

    private fun goToNextQuestion() {
        if (!hasAnswered) return

        if (currentQuestionIndex < questions.lastIndex) {
            currentQuestionIndex++
            showQuestion()
        } else {
            showFinalResult()
        }
    }

    private fun showFinalResult() {
        txtQuestionNumber.visibility = View.GONE
        txtQuestion.visibility = View.GONE
        imgQuestion.visibility = View.GONE
        txtFeedback.visibility = View.GONE
        answerButtons.forEach { it.visibility = View.GONE }
        btnNext.visibility = View.GONE

        txtResult.text = "Quiz terminé !\nVotre score final : $score / ${questions.size}"
        txtResult.visibility = View.VISIBLE
        btnReplay.visibility = View.VISIBLE
        progressQuiz.progress = questions.size
        txtScore.text = "Score : $score / ${questions.size}"
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        hasAnswered = false

        txtQuestionNumber.visibility = View.VISIBLE
        txtQuestion.visibility = View.VISIBLE
        imgQuestion.visibility = View.VISIBLE
        txtResult.visibility = View.GONE
        btnReplay.visibility = View.GONE

        showQuestion()
    }
}
