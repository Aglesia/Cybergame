package com.lps2ima.dfr.cybergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Classe principale, lance et coordonne l'application
 */
public class MainActivity extends AppCompatActivity {
    // Constantes
    public static final int ECRAN_TITRE = 0;
    public static final int ECRAN_SCORE = 1;
    public static final int ECRAN_JEU = 2; // DOIT ETRE LA DERNIERE VALEUR (LA PLUS GRANDE) DES CONSTANTES D'ECRAN
    public static final String[] LISTE_JEUX = new String[]{"Premier", "Second", "Quitter"};

    // Attributs
    private int ecran_actuel = ECRAN_TITRE; // Ecran titre
    private Jeu jeu = null;
    private Slide slide_titre = new Slide("Sélectionnez votre jeu", 2, LISTE_JEUX, 0, 0);

    // Etats de l'application
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
    }

    // Méthodes de l'application
    /**
     * Methode appelée lors d'un appuie sur un bouton
     * @param view bouton appuyé
     */
    public void appuieBouton(View view) {
        // Si c'est un écran de question, on appelle la méthode de sélection de la réponse
        if(this.ecran_actuel >= ECRAN_JEU && this.jeu != null) {
            // On traite la réponse donnée par l'utilisateur
            int slide_suivante = jeu.selectionReponse(this.ecran_actuel-ECRAN_JEU+1, this.numeroBouton(view));

            // Si la partie est finie, on passe à l'écran des scores
            if(slide_suivante <= 0){

            }
            // Sinon, on passe à la slide suivante
            else{

            }
        }
        // Si c'est la fin d'un jeu
        else if(this.ecran_actuel == ECRAN_SCORE){
            // On revient à l'écran titre
        }
        // Sinon on regarde quel jeu doit être lancé
        else{

        }
    }

    /**
     * Affiche la slide demandée, avec une question (ou une explication) et un ou plusieurs boutons de réponse
     * @param s Slide à afficher
     */
    private void afficherSlide(Slide s){

    }

    /**
     * Charge l'écran d'affichage du score, avec le nom du jeu, le nombre de points max et le nombre de points récoltés
     * @param j Jeu terminé
     */
    private void afficherScore(Jeu j){

    }

    /**
     * Charge l'écran titre, qui contient la liste des jeux possibles, le bouton quitter et la liste des remerciements
     * (Liste des personnes ayant participés à la création de l'appli, mais aussi des questions/Slides)
     */
    private void afficherEcranTitre(){

    }

    /**
     * Charge un nouveau jeu et lance sa première Slide
     * @param jeu Jeu à initialiser et lancer
     */
    private void chargerJeu(Jeu jeu){

    }

    /**
     * Crée un nouveau bouton, lui assigne le texte et son numéro de réponse, ainsi que son listener
     * @param texte Texte à afficher sur le bouton
     * @param numero Numéro de réponse correspondant
     * @return Le bouton fraichement créé
     */
    private Button creerBouton(String texte, int numero){
        Button bt = new Button(this);
        bt.setText(texte);
        bt.setContentDescription(String.valueOf(numero));

        // On ajoute le listener


        return bt;
    }

    /**
     * Indique le numéro porté par le bouton passé en paramètre
     * @param v Bouton (vue)
     * @return Numéro du bouton, ou 0 s'il n'en a pas
     */
    private int numeroBouton(View v){
        String name = ((Button)v).getContentDescription().toString();
        int ret = 0;
        // On récupère le numéro du bouton via sa description

        return ret;
    }
}
