package com.lps2ima.dfr.cybergame.Jeux;

import android.util.Log;

import com.lps2ima.dfr.cybergame.Jeu;
import com.lps2ima.dfr.cybergame.Slide;
import com.lps2ima.dfr.cybergame.Slides.ChoixMultiples;
import com.lps2ima.dfr.cybergame.Slides.ChoixSimple;

/**
 * Created by dorian on 14/12/17.
 */

/**
 * QCM de test, on peut y mettre tout type de question
 */
public class Test extends Jeu {
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

        return slide_suivante;
    }

    @Override
    public int initialiser() {
        // On crée toutes les slides
        this.slides = new Slide[5];
        this.slides[0] = new ChoixSimple("Bienvenue dans le jeu de test, Slide 1", new String[]{"Prendre le point", "Ne pas le prendre"}, 1, 1);
        this.slides[1] = new ChoixSimple("Bienvenue dans le jeu de test, Slide 2", new String[]{"Prendre le point", "Ne pas le prendre"}, 1, 1);
        this.slides[2] = new ChoixSimple("Bienvenue dans le jeu de test, Slide 3\npeut revenir à la slide 1", new String[]{"Prendre le point", "Ne pas le prendre"}, 1, 1);
        this.slides[3] = new ChoixMultiples("Bienvenue dans le jeu de test, Slide 4\nQCM, répondez bien !", new String[]{"Prendre le point", "Ne pas le prendre", "Luke meurt", "Nan c'est faux, c'est Leia"}, new int[]{1, 3}, 2, 1);
        this.slides[4] = new ChoixSimple("Bienvenue dans le jeu de test, Slide 5", new String[]{"Prendre le point", "Ne pas le prendre"}, 1, 1);

        // On indique de commencer à la première slide (1 et non 0 !!)
        return 1;
    }

    @Override
    public String getNom() {
        return "Test";
    }
}
