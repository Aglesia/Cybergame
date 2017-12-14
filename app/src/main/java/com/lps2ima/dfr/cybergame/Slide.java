package com.lps2ima.dfr.cybergame;

/**
 * Created by dorian on 14/12/17.
 */

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
public class Slide {
    private int nb_boutons = 0; // Nombre de bouton pour la slide
    private String texte_slide = ""; // Texte à afficher pour la slide
    private String[] textes_boutons = new String[0]; // Ensemble de tous les textes des boutons
    private int bonne_reponse = 0; // Numéro du bouton correct (de 1 à nb_boutons) ou 0 si impasse
    private int score = 0; // Nombre de points que rapporte la slide

    /**
     * Constructeur, contient toutes les propriétés du slide
     * @param texte Texte de présentation de la Slide/la question
     * @param nb_boutons Nombre de choix possibles
     * @param textes_boutons Les différents textes à placer sur les boutons de choix de réponse
     * @param bonne_reponse Numéro du bouton étant la bonne réponse
     * @param score Nombre de points que la slide rapporte en cas de bonne réponse
     */
    public Slide(String texte,
                 int nb_boutons,
                 String[] textes_boutons,
                 int bonne_reponse,
                 int score){
        this.texte_slide = texte;
        this.nb_boutons = nb_boutons;
        this.textes_boutons = textes_boutons;
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
     * Indique combien de choix de réponse il faut afficher
     * @return Le nombre de réponses possibles
     */
    public int getNbBoutons(){
        return this.nb_boutons;
    }

    /**
     * Indique le texte à afficher sur le bouton correspondant au numéro de réponse
     * @param numero_bouton Numéro de la réponse pointée par le bouton
     * @return Texte à afficher sur le bouton
     */
    public String getTexteBouton(int numero_bouton){
        String ret = "";
        if(numero_bouton <= this.textes_boutons.length && numero_bouton>0)
            ret = this.textes_boutons[numero_bouton-1];
        return ret;
    }

    /**
     * Indique l'ensemble des textes à afficher sur les boutons de choix de réponse
     * @return Ensemble des textes à mettre sur les boutons
     */
    public String[] getTextesBoutons(){
        return this.textes_boutons;
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
}
