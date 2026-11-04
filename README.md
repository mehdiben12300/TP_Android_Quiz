# 📱 QuizImages - Application Android Native

> **Projet Mobile :** Quiz en images sur les formes géométriques  
> **Étudiant :** Mehdi Benomrane  
> **Classe :** L3DSI  

---

## 📌 Accès Rapide au Compte Rendu et Captures

- 📄 **[Compte Rendu au format PDF](./compte-rendu/compte_rendu.pdf)** *(Format officiel prêt à la lecture)*
- 📝 **[Compte Rendu au format Markdown](./compte-rendu/compte_rendu.md)**
- 🌐 **[Compte Rendu au format HTML](./compte-rendu/compte_rendu.html)**
- 📸 **[Captures d'écran de l'application](./captures/)**

---

## 📸 Captures d'Écran de l'Application

| Question 1 | Question 1 (Réponse) | Résultat Final |
| :---: | :---: | :---: |
| ![Question 1](./captures/screen_question1.png) | ![Feedback Q1](./captures/screen_question1_feedback.png) | ![Résultat](./captures/screen_resultat_final.png) |

---

## 🗂️ Structure du Dépôt et Navigation

```text
QuizImages/
├── README.md                           <- Ce fichier de présentation
├── captures/                           <- Captures d'écran de l'application
│   ├── screen_question1.png
│   ├── screen_question1_feedback.png
│   ├── screen_question2.png
│   ├── screen_question3.png
│   ├── screen_question4.png
│   ├── screen_question5.png
│   └── screen_resultat_final.png
├── compte-rendu/
│   ├── compte_rendu.pdf                <- Compte rendu au format PDF
│   ├── compte_rendu.md                 <- Compte rendu au format Markdown
│   └── compte_rendu.html               <- Compte rendu au format HTML
├── app/
│   ├── build.gradle.kts                <- Configuration du module app
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml     <- Fichier manifeste de l'application
│           ├── java/com/example/quizimages/
│           │   └── MainActivity.kt     <- Code source Kotlin principal (Logique & Quiz)
│           └── res/
│               ├── drawable/           <- Ressources d'images vectorielles des formes
│               └── layout/
│                   └── activity_main.xml <- Layout XML de l'interface utilisateur
├── build.gradle.kts                    <- Configuration Gradle racine
└── settings.gradle.kts                 <- Paramètres de projet Gradle
```

---

## 🛠️ Composition du Projet

| Élément | Emplacement | Description |
| :--- | :--- | :--- |
| **Logique Applicative** | [`MainActivity.kt`](./app/src/main/java/com/example/quizimages/MainActivity.kt) | Contient la structure des questions (`Question`), la gestion des événements (`OnClickListener`), le calcul du score et le contrôle de la `ProgressBar`. |
| **Interface Utilisateur** | [`activity_main.xml`](./app/src/main/res/layout/activity_main.xml) | Layout responsive en `ScrollView` et `LinearLayout` contenant les `TextView`, `ImageView`, `ProgressBar` et `Button`. |
| **Images des Questions** | [`res/drawable/`](./app/src/main/res/drawable/) | Formes géométriques vectorielles XML (`triangle`, `carre`, `cercle`, `etoile`, `rectangle`). |
| **Captures d'écran** | [`captures/`](./captures/) | Captures réelles de l'application en cours d'exécution. |
| **Documentation & Rapport** | [`compte-rendu/`](./compte-rendu/) | Dossier contenant les livrables du rapport d'évaluation. |

---

## ✨ Fonctionnalités de l'Application

- ❓ **Questions interactives avec images :** 5 questions illustrées sur les formes géométriques.
- 🎯 **Calcul du score en temps réel :** Score mis à jour dynamiquement à chaque réponse.
- 📊 **Suivi de progression :** Barre de progression visuelle (`ProgressBar`) évoluant de 0 à 5.
- 🔒 **Sécurisation des réponses :** Blocage des boutons après le choix du joueur pour éviter le double clic.
- 🏆 **Écran de synthèse final :** Affichage du bilan du quiz et possibilité de **Rejouer**.

---

## 🚀 Compilation et Exécution

### Prérequis
- Android Studio Ladybug ou version plus récente
- JDK 17
- Émulateur Android (ou appareil physique avec débogage USB activé)

### Étapes d'exécution
1. Ouvrir le projet dans **Android Studio**.
2. Attendre la fin de la synchronisation Gradle (*Gradle Sync*).
3. Sélectionner la cible d'exécution (ex: `emulator-5554`).
4. Cliquer sur le bouton **Run** (`Shift + F10`) ou utiliser le CLI Gradle :
   ```bash
   ./gradlew assembleDebug
   ```
