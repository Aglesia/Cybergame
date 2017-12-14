package com.lps2ima.dfr.cybergame.Slides;

import android.widget.LinearLayout;

import com.lps2ima.dfr.cybergame.MainActivity;
import com.lps2ima.dfr.cybergame.Slide;

/**
 * Created by Dorian on 14/12/2017.
 */

public class ChoixSimple extends Slide {
    private String[] textes_boutons;

    /**
     * Constructeur, contient toutes les propriétés du slide
     *
     * @param texte         Texte de présentation de la Slide/la question
     * @param textes_boutons Textes à afficher sur les boutons
     * @param bonne_reponse Numéro du bouton étant la bonne réponse
     * @param score         Nombre de points que la slide rapporte en cas de bonne réponse
     */
    public ChoixSimple(String texte, String[] textes_boutons, int bonne_reponse, int score) {
        super(texte, bonne_reponse, score);
        this.textes_boutons = textes_boutons;
    }

    @Override
    public void afficher(LinearLayout layout, MainActivity activite) {
        for(int i=0; i<this.textes_boutons.length; i++)
            layout.addView(this.creerBouton(activite, this.textes_boutons[i], i+1));
    }
}
