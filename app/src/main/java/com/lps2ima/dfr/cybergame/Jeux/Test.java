package com.lps2ima.dfr.cybergame.Jeux;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.lps2ima.dfr.cybergame.Jeu;
import com.lps2ima.dfr.cybergame.R;
import com.lps2ima.dfr.cybergame.Slide;
import com.lps2ima.dfr.cybergame.Slides.ChoixMultiples;
import com.lps2ima.dfr.cybergame.Slides.ChoixSimple;
import com.lps2ima.dfr.cybergame.Slides.MotsClefs;

/**
 * Created by dorian on 14/12/17.
 */

/**
 * QCM de test, on peut y mettre tout type de question
 */
public class Test extends Jeu {
    private View[] views;

    public Test(Context context) {
        super(context);
        this.views = new View[0];
    }

    @Override
    public int selectionReponse(int numero_slide, int numero_bouton) {
        int slide_suivante = 0;
        Log.d("selectionReponse()", "Numéro de la slide : "+numero_slide+", Numéro du bouton : "+numero_bouton);

        // On fait un switch pour les slides particulières
        switch (numero_slide){
            case 3: // Peut revenir à la slide 1
                slide_suivante = 1;
                if(this.getSlide(numero_slide).isBonneReponse(numero_bouton)) {
                    this.score_joueur += this.getSlide(numero_slide).getScore();
                    slide_suivante = 4;
                }
                break;

            // Par défaut, on ne fait qu'ajouter les points et passer à la slide suivante
            default:
                // Si c'est la bonne réponse, on ajoute les points
                if(this.getSlide(numero_slide).isBonneReponse(numero_bouton))
                    this.score_joueur += this.getSlide(numero_slide).getScore();

                // Si ce n'est pas la dernière slide, on passe à la suivante
                if(!this.getSlide(numero_slide+1).type())
                    slide_suivante = numero_slide+1;
        }

        // On met à jour la barre de progression
        this.setBarreProgression(slide_suivante);
        return slide_suivante;
    }

    @Override
    public int initialiser() {
        super.score_joueur = 0;

        // On crée toutes les slides
        this.slides = new Slide[5];
        this.slides[0] = new ChoixSimple("Bienvenue dans ce jeu de test.\nDans cette partie, vous répondrez à 2 questions à choix simple, 1 question à choix multiple et 1 question à réponse écrite.",
                0, new String[]{"Continuer"}, 0, 0);
        this.slides[1] = new ChoixSimple("Comment s'appelle ce jeu ?", 0, new String[]{"CyberJeu", "SuperGame", "SuperJeu", "CyberGame"}, 4, 1);
        this.slides[2] = new ChoixMultiples("Quelles matières pouvons-nous étudier en LP-S2IMA ?", 0, new String[]{"Développement mobile", "Histoire", "Gestion BDD", "Développement PHP", "Développement python", "Modélisation 3D"}, new Integer[]{1, 3, 4}, 2, 1);
        this.slides[3] = new ChoixSimple("Quel OS mobile est mort aujourd'hui ?", 0, new String[]{"IOS", "Windows phone", "Android"}, 2, 2);
        this.slides[4] = new MotsClefs("Comment s'appelle le responsable de la formation \"LP S2IMA\" ?", 0, new String[]{"Lelain", "Le lain"}, 3);

        // On indique de commencer à la première slide (1 et non 0 !!)
        return 1;
    }

    @Override
    public String getNom() {
        return "Test";
    }

    @Override
    public int getImageId() {
        return R.drawable.test;
    }

    @Override
    public View[] getResumeReponses() {
        return new View[0];
    }
}
