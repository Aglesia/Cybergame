package com.lps2ima.dfr.cybergame;

/**
 * Created by dorian on 13/12/17.
 */

import com.lps2ima.dfr.cybergame.Slides.Void;

/**
 * Classe "com.lps2ima.dfr.cybergame.Jeu", abstraite.
 * Un jeu est un ensemble de plusieurs questions (Slides) à la suite, pas forcément dans un ordre fixe.
 * A chaque réponse donnée, le jeu passe à une nouvelle question (nouvelle Slide) ou termine la partie.
 * La nouvelle Slide à afficher peut dépendre de la réponse donnée lors de la Slide précédente.
 * Toute bonne réponse peut rapporter des points, à la fin de la partie le cumul de ces points est indiqué au joueur.
 *
 * Pour utiliser cette classe abstraite, il faut créer une nouvelle classe fille de celle-ci.
 * Surcharger "initialiser()", qui se chargera de créer tous les slides du jeu et d'indiquer à l'application la Slide à charger en premier
 * Surcharger "selectionReponse(int, int)", qui se chargera de récolter les réponses, d'ajouter les points, et d'indiquer les n° des Slides suivantes.
 */
public abstract class Jeu {
    protected Slide[] slides = new Slide[0]; // Slides de la partie
    protected int score_joueur = 0; // Score actuel du joueur

    /**
     * Récupère l'action de l'utilisateur, la traite, et indique l'écran suivant à afficher
     * @param numero_slide Numéro de la slide actuelle de la partie
     * @param numero_bouton Numéro du bouton qui a été appuyé (de 1 à x)
     * @return Le numéro du slide à afficher, 0 pour terminer la partie
     */
    public abstract int selectionReponse(int numero_slide, int numero_bouton);

    /**
     * Remplie la partie avec des slides, et des valeurs initiales.
     * @return Numéro du slide à charger au démarrage de la partie
     */
    public abstract int initialiser();

    /**
     * Indique le score actuel du joueur, pour cette partie
     * @return Score du joueur
     */
    public int getScore(){
        return this.score_joueur;
    }

    /**
     * Retourne le slide actuel
     * @param no_slide Numéro de la slide (de 1 à nb_slides)
     * @return Slide demandée
     */
    public Slide getSlide(int no_slide){
        Slide ret = new Void(); // Slide vide
        if(no_slide <= this.slides.length && no_slide>0)
            ret = this.slides[no_slide-1];
        return ret;
    }

    /**
     * Indique le nom du jeu
     * @return Nom du jeu
     */
    public abstract String getNom();

    /**
     * Indique l'ID de la ressource à utiliser en tant qu'image
     * @return image du jeu sous forme d'ID ressource (R.drawable.[image])
     */
    public abstract int getImageId();
}
