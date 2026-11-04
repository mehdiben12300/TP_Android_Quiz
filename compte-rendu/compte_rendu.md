# Compte Rendu de Projet Mobile - Quiz en Images

---

## 1. Informations Générales

- **Nom de l'étudiant :** Mehdi Benomrane
- **Classe :** L3DSI
- **Thème choisi :** Quiz en images sur les formes géométriques (Application Android native en Kotlin)

---

## 2. Présentation de l'Interface et des Widgets Utilisés

L'interface utilisateur est définie dans le fichier layout XML `activity_main.xml`. Elle adopte une structure moderne, réactive et ergonomique conçue avec les composants standards Android :

- **`ScrollView`** : Conteneur principal assurant un défilement fluide sur toutes les tailles d'écrans.
- **`LinearLayout`** : Organisation structurée en disposition verticale et horizontale.
- **`TextView`** :
  - `txt_score` : Affiche le score en temps réel (ex: *Score : 3 / 5*).
  - `txt_question_number` : Indique le numéro de la question courante (ex: *Question 1 sur 5*).
  - `txt_question` : Présente le libellé de la question posée.
  - `txt_feedback` : Donne un retour visuel instantané après le choix du joueur (ex: *Bonne réponse !* ou l'indication de la bonne réponse).
  - `txt_result` : Affiche le résultat final récapitulatif à la fin du quiz.
- **`ProgressBar` (`progress_quiz`)** : Barre de progression horizontale indiquant l'avancement du quiz de 0 à 5.
- **`ImageView` (`img_question`)** : Zone d'affichage centrale de l'image de la forme géométrique associée à la question avec mise à l'échelle automatique (`scaleType="fitCenter"`).
- **`Button`** :
  - `btn_choix1`, `btn_choix2`, `btn_choix3` : Les trois options de réponse proposées pour chaque question.
  - `btn_next` : Bouton de passage à la question suivante ou vers le score final.
  - `btn_replay` : Bouton permettant de recommencer le quiz.

---

## 3. Structure du Tableau des Questions et Association des Images

### Structure de la donnée (`Question.kt`)

Une classe de données `Question` regroupe l'ensemble des attributs nécessaires :

```kotlin
data class Question(
    val text: String,
    val imageResId: Int,
    val choices: Array<String>,
    val correctAnswerIndex: Int,
    val imageDescription: String
)
```

### Tableau des Questions

Les questions sont stockées sous forme d'un tableau `arrayOf<Question>` dans `MainActivity.kt` :

```kotlin
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
```

### Association des Images aux Questions

Chaque question est associée à un identifiant de ressource drawable (`R.drawable.triangle`, `R.drawable.carre`, etc.). Lors du chargement d'une question (`showQuestion()`), l'image est associée dynamiquement à l' `ImageView` via :

```kotlin
imgQuestion.setImageResource(question.imageResId)
imgQuestion.contentDescription = question.imageDescription
```

---

## 4. Événements, Calcul du Score et Progression

### Gestion des Événements (`OnClickListener`)

Les écouteurs d'événements sont attachés au démarrage (`onCreate`) :
- Clic sur un bouton de choix $\rightarrow$ déclenche `checkAnswer(index)`.
- Clic sur le bouton Suivant $\rightarrow$ déclenche `goToNextQuestion()`.
- Clic sur le bouton Rejouer $\rightarrow$ déclenche `restartQuiz()`.

### Calcul du Score et Gestion des Reponses

Lorsqu'un utilisateur sélectionne une réponse :
1. Le booléen `hasAnswered` passe à `true` et les 3 boutons de choix sont désactivés (`isEnabled = false`) pour éviter les clics multiples.
2. Si `selectedIndex == question.correctAnswerIndex`, la variable `score` est incrémentée (`score++`) et un message positif s'affiche.
3. Sinon, le message de feedback indique la réponse exacte.
4. L'affichage du score (`txtScore.text`) est immédiatement réactualisé.

### Suivi de la Progression

- La `ProgressBar` est initialisée avec `max = 5`.
- Après chaque réponse, sa valeur est mise à jour avec `progressQuiz.progress = currentQuestionIndex + 1`.

### Écran de Fin de Quiz

Lorsque la 5ème question est terminée :
- Les éléments de question (`ImageView`, boutons de choix) sont masqués (`View.GONE`).
- L'écran final s'affiche avec le texte de résultat (`txtResult`) récapitulant le score obtenu et le bouton **Rejouer** (`btnReplay`) permettant de relancer une nouvelle partie.

---

## 5. Tests Réalisés, Résultats et Difficultés Rencontrées

### Tests Réalisés
1. **Test d'exécution des questions** : Vérification du bon défilement des 5 questions dans l'ordre.
2. **Test du calcul de score** : Validation du comportement pour des parcours sans faute (5/5) et avec des erreurs.
3. **Test d'interface (UI / UX)** : Contrôle du blocage des choix après réponse, de la visibilité dynamique du bouton *Question Suivante*, et de l'affichage final.
4. **Test de réinitialisation** : Vérification de la remise à zéro du score et de l'index lors d'un clic sur *Rejouer*.
5. **Test de build & compilation Gradle** : Validation du déploiement sur l'émulateur Android (`emulator-5554`).

### Résultats
Toutes les fonctionnalités ont été validées avec succès sur l'émulateur. L'application répond rapidement, le score est calculé correctement et la navigation est fluide.

### Difficultés Rencontrées et Solutions
- **Incompatibilité de version Java/Kotlin** : Un problème de compatibilité de cible JVM entre Java (1.8) et Kotlin (17) est survenu lors du build.
  - *Solution* : Ajout de la configuration `compileOptions` et `kotlinOptions` dans `app/build.gradle.kts` fixant la version cible du bytecode à Java 17.
- **Gestion de l'état après sélection** : Il fallait s'assurer qu'un utilisateur ne puisse pas cliquer plusieurs fois ou changer sa réponse.
  - *Solution* : Introduction du flag `hasAnswered` et désactivation des boutons `answerButtons.forEach { it.isEnabled = false }`.
