package com.lps2ima.dfr.cybergame.Slides;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lps2ima.dfr.cybergame.MainActivity;
import com.lps2ima.dfr.cybergame.Slide;

/**
 * Created by Dorian on 14/12/2017.
 */

public class ChoixSimple extends Slide {
    private String[] textes_boutons;
    private int image;

    /**
     * Constructeur, contient toutes les propriétés du slide
     *
     * @param texte         Texte de présentation de la Slide/la question
     * @param image         Image à afficher (s'il y en a une) juste en dessous du texte
     * @param textes_boutons Textes à afficher sur les boutons
     * @param bonne_reponse Numéro du bouton étant la bonne réponse
     * @param score         Nombre de points que la slide rapporte en cas de bonne réponse
     */
    public ChoixSimple(String texte, int image, String[] textes_boutons, int bonne_reponse, int score) {
        super(texte, bonne_reponse, score);
        this.textes_boutons = textes_boutons;
        this.image = image;
    }

    @Override
    public void afficher(LinearLayout layout, MainActivity activite) {
        // On affiche l'image
        if(this.image != 0){
            ImageView img = new ImageView(activite);
            img.setImageResource(image);
            layout.addView(img);
        }

        // On crée les boutons
        for(int i=0; i<this.textes_boutons.length; i++)
            layout.addView(this.creerBouton(activite, this.textes_boutons[i], i+1));
    }
}
