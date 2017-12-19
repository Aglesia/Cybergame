package com.lps2ima.dfr.cybergame;

/**
 * Created by dorian on 13/12/17.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Space;
import android.widget.TextView;

import com.lps2ima.dfr.cybergame.Slides.Void;

import java.util.ArrayList;

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
    protected MainActivity context; // Main Activity
    protected ArrayList<View> views = new ArrayList<>(); // Ensemble des vues à afficher sur l'écran des scores
    protected String[] reponses = new String[0]; // Ensemble des réponses aux questions

    public Jeu(Context context){
        this.context = (MainActivity)context;
        this.score_joueur = 0;
    }

    /**
     * Récupère l'action de l'utilisateur, la traite, et indique l'écran suivant à afficher
     * @param numero_slide Numéro de la slide actuelle de la partie
     * @param numero_bouton Numéro du bouton qui a été appuyé (de 1 à x)
     * @return Le numéro du slide à afficher, 0 pour terminer la partie
     */
    public int selectionReponse(int numero_slide, int numero_bouton){
        int slide_suivante = 0;
        Log.d("selectionReponse()", "Numéro de la slide : "+numero_slide+", Numéro du bouton : "+numero_bouton);
        // On ajoute aux réponses
        TextView t = new TextView(this.context);
        t.setText("Slide n°"+numero_slide+")");
        t.setTextSize(18);
        t.setTextColor(Color.WHITE);
        t.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        this.views.add(t);

        int score = this.getSlide(numero_slide).choixReponse(numero_bouton, this.context);

        LinearLayout layout = new LinearLayout(this.context);
        layout.removeAllViews();
        this.getSlide(numero_slide).afficherReponse(layout, this.context);
        this.views.add(layout);

        // Si on doit ajouter la correction
        if(score < this.getSlide(numero_slide).maxPoints()) {
            t = new TextView(this.context);
            t.setText("Correction :");
            t.setTextColor(Color.rgb(180, 0, 0));
            t.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
            this.views.add(t);
            t = new TextView(this.context);
            t.setText(this.reponses[numero_slide - 1]);
            this.views.add(t);
        }

        // Si c'est la bonne réponse, on ajoute les points
        if(score > 0){
            this.score_joueur += score;
            t = new TextView(this.context);
            t.setText("Vous avez remporté "+score+" point(s).");
            t.setTextColor(Color.rgb(0, 70, 0));
            this.views.add(t);
        }
        // Si on a perdu
        else if(score < 0){
            this.score_joueur -= score;
            t = new TextView(this.context);
            t.setText("Vous avez perdu "+(score*-1)+" point(s).");
            t.setTextColor(Color.rgb(70, 0, 0));
            this.views.add(t);
        }

        // On ajoute un séparateur
        if(this.getSlide(numero_slide).maxPoints()>0){
            Space space = new Space(this.context);
            space.setMinimumHeight(80);
            this.views.add(space);
        }

        // Si ce n'est pas la dernière slide, on passe à la suivante
        if(!this.getSlide(numero_slide+1).type())
            slide_suivante = numero_slide+1;

        return slide_suivante;
    }

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
     * Indique à l'utilisateur l'avancement du jeu, via une barre de progression
     * @param valeur valeur actuelle, entre 0 et NB_SLIDES
     */
    public void setBarreProgression(int valeur){
        ProgressBar progression = this.context.findViewById(R.id.barre_progression);
        progression.setMax(this.slides.length);
        progression.setProgress(valeur);
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

    /**
     * Affiche le résumé de toutes les réponses données, ainsi que les corrections
     * C'est au créateur de la sous-classe de modéliser cette partie
     * @return La liste des vues (de haut en bas) à afficher sur l'écran des scores
     */
    public ArrayList<View> getResumeReponses(){
        return this.views;
    }
}
