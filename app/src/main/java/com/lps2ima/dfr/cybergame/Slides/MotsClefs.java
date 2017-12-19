package com.lps2ima.dfr.cybergame.Slides;

import android.graphics.drawable.Drawable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lps2ima.dfr.cybergame.MainActivity;
import com.lps2ima.dfr.cybergame.Slide;

import java.util.Arrays;

/**
 * Created by Dorian on 14/12/2017.
 */

/**
 * Le but de ce genre de slide est de montrer une image, poser une question à propos de cette image et récuérer une réponse écrite par l'utilisateur.
 * Si aucune image n'est indiquée, il n'y en a tout simplement pas
 * La réponse est considérée exacte si une des phrases clefs est présente dans la réponse
 */
public class MotsClefs extends Slide {
    private int image;
    private String[] bonnes_reponses;
    private int score;
    private EditText zone_rendu = null;

    /**
     * Constructeur, contient toutes les propriétés du slide
     *
     * @param texte             Texte de présentation de la Slide/la question
     * @param image             Image à afficher
     * @param bonnes_reponses   Numéro du bouton étant la bonne réponse
     * @param score             Nombre de points que la slide rapporte en cas de bonne réponse
     */
    public MotsClefs(String texte, int image, String[] bonnes_reponses, int score) {
        super(texte);
        this.image = image;
        this.bonnes_reponses = bonnes_reponses;
        this.score = score;
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

        // On affiche la zone de rendu
        this.zone_rendu = new EditText(activite);
        layout.addView(this.zone_rendu);

        // On ajoute le bouton valider
        layout.addView(this.creerBouton(activite, "Valider", 1));
    }

    /**
     * Indique le nombre de points de cette question
     * @return Score de la slide
     */
    public int getScore(){
        return this.score;
    }

    @Override
    public boolean isBonneReponse(int numero_bouton) {
        String t = this.zone_rendu.getText().toString().toUpperCase();
        String[] entree_user = t.split(" ");
        boolean ret = false;

        for(String motClef : this.bonnes_reponses)
            if(Arrays.asList(entree_user).contains(motClef.toUpperCase()))
                ret = true;

        return ret;
    }
}
