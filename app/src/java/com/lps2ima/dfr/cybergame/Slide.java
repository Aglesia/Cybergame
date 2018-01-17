package com.lps2ima.dfr.cybergame;

/**
 * Created by dorian on 14/12/17.
 */

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Classe "Slide", contient :
 *  - Une question
 *  - Un ensemble de réponses possibles
 *  - Une explication à afficher si besoin
 *
 * Une slide correspond à une question/épreuve d'une partie.
 * Chaque slide rapporte un certain nombre de points en cas de bonne réponse, 0 en cas de mauvaise réponse.
 * Une slide peut ne pas avoir de bonne réponse (appelée "slide impasse").
 */
public abstract class Slide {
    private String texte_slide = ""; // Texte à afficher sur la slide
    private int bonne_reponse = 0; // Numéro du bouton correct (de 1 à nb_boutons) ou 0 si impasse
    private int score = 0; // Nombre de points que rapporte la slide

    /**
     * Constructeur, contient toutes les propriétés du slide
     * @param texte Texte de présentation de la Slide/la question
     * @param bonne_reponse Numéro du bouton étant la bonne réponse
     * @param score Nombre de points que la slide rapporte en cas de bonne réponse
     */
    public Slide(String texte,
                 int bonne_reponse,
                 int score){
        this.texte_slide = texte;
        this.bonne_reponse = bonne_reponse;
        this.score = score;
    }

    /**
     * Donne le texte à afficher sur la slide (la question)
     * @return Le texte de la slide
     */
    public String getTexteSlide(){
        return this.texte_slide;
    }

    /**
     * Indique si la réponse donnée est la bonne réponse (Il peut ne pas y avoir de bonne réponse)
     * @param numero_bouton Numéro du bouton appuyé
     * @return Vrai si c'est la bonne réponse
     */
    public boolean isBonneReponse(int numero_bouton){
        return (this.bonne_reponse == numero_bouton);
    }

    /**
     * Indique le nombre de points de cette question
     * @return Score de la slide
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Indique si c'est une slide spécifique (Void)
     * @return true si c'est une slide spéciale
     */
    public boolean type(){
        return false;
    }

    /**
     * Crée un nouveau bouton, lui assigne le texte et son numéro de réponse, ainsi que son listener
     * @param texte Texte à afficher sur le bouton
     * @param numero Numéro de réponse correspondant
     * @return Le bouton fraichement créé
     */
    protected Button creerBouton(final MainActivity activite, String texte, int numero){
        // On crée le bouton avec son nom
        final Button bt = new Button(activite);
        bt.setText(texte);
        bt.setContentDescription(String.valueOf(numero));

        // On ajoute le listener
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activite.appuieBouton(bt);
            }
        });

        return bt;
    }

    /**
     * Ajoute ses vues à la layout de l'écran
     * @param layout LinearLayout dans lequel on peut ajouter nos infos
     * @param activite Activité principale, utile pour l'accès à l'affichage
     */
    public abstract void afficher(LinearLayout layout, MainActivity activite);
}
