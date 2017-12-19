package com.lps2ima.dfr.cybergame.Slides;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lps2ima.dfr.cybergame.MainActivity;
import com.lps2ima.dfr.cybergame.Slide;

/**
 * Created by Dorian on 14/12/2017.
 */

public class ChoixSimple extends Slide {
    private String[] textes_boutons;
    private int image;
    private int bonne_reponse;

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
        super(texte);
        this.textes_boutons = textes_boutons;
        this.image = image;
        this.bonne_reponse = bonne_reponse;
        this.score = score;
    }

    /**
     * Indique si la réponse donnée est la bonne réponse (Il peut ne pas y avoir de bonne réponse)
     * @param numero_bouton Numéro du bouton appuyé
     * @return Vrai si c'est la bonne réponse
     */
    public int choixReponse(int numero_bouton, MainActivity activite){
        Log.d("lenght_qsdjbdf", ""+textes_boutons.length);
        for(int i=0; i<textes_boutons.length; i++) {
            TextView t = new TextView(activite);
            t.setText(textes_boutons[i]);
            if(this.bonne_reponse == i+1)
                t.setTextColor(Color.GREEN);
            else if(numero_bouton == i+1)
                t.setTextColor(Color.RED);
            this.reponses_view.add(t);
        }
        if(this.bonne_reponse == numero_bouton)
            return this.score;
        return 0;
    }

    @Override
    public void afficher(LinearLayout layout, MainActivity activite) {
        // On affiche l'image
        if(this.image != 0){
            ImageView img = new ImageView(activite);
            img.setImageResource(image);
            img.setAdjustViewBounds(true);
            layout.addView(img);
        }

        // On crée les boutons
        for(int i=0; i<this.textes_boutons.length; i++) {
            Button b = Slide.creerBouton(activite, this.textes_boutons[i], i + 1);
            layout.addView(b);
        }
    }
}
