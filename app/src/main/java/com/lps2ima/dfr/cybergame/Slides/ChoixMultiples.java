package com.lps2ima.dfr.cybergame.Slides;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lps2ima.dfr.cybergame.MainActivity;
import com.lps2ima.dfr.cybergame.Slide;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by Dorian on 14/12/2017.
 */

public class ChoixMultiples extends Slide {
    private String[] reponses;
    private int[] bonnes_reponses;
    private CheckBox[] boxes;
    private int score_mauvais;
    private int image;

    /**
     * Constructeur, contient toutes les propriétés du slide
     *
     * @param texte         Texte de présentation de la Slide/la question
     * @param image         Image à afficher, si présente
     * @param reponses      Ensemble des réponses possibles
     * @param bonnes_reponses Numéro du bouton étant la bonne réponse
     * @param score_bon       Nombre de points que la slide rapporte pour chaque bonne réponse
     * @param score_mauvais   Nombre de points que la slide retire en cas de mauvaise réponse
     */
    public ChoixMultiples(String texte, int image, String[] reponses, int[] bonnes_reponses, int score_bon, int score_mauvais) {
        super(texte, 0, score_bon);
        this.image = image;
        this.reponses = reponses;
        this.bonnes_reponses = bonnes_reponses;
        this.boxes = new CheckBox[reponses.length];
        this.score_mauvais = score_mauvais;
    }

    @Override
    public void afficher(LinearLayout layout, MainActivity activite) {
        // On affiche l'image
        if(this.image != 0){
            ImageView img = new ImageView(activite);
            img.setImageResource(image);
            layout.addView(img);
        }
        
        // On ajoute les checkboxes
        for(int i=0; i<this.boxes.length; i++){
            boxes[i] = new CheckBox(activite);
            boxes[i].setText(reponses[i]);
            boxes[i].setChecked(false);
            layout.addView(boxes[i]);
        }

        // On ajoute le bouton valider
        layout.addView(this.creerBouton(activite, "Valider", 1));
    }

    @Override
    public boolean isBonneReponse(int numero_bouton){
        return true;
    }

    @Override
    public int getScore() {
        int pts = 0;

        // Selon les réponses données par l'utilisateur, on calcul le nombre de points
        for(int i=0; i<this.boxes.length; i++){
            // S'il est coché
            if(this.boxes[i].isChecked()){
                // Si c'est bon, on gagne 1 point
                if(Arrays.asList(this.reponses).contains(""+(i+1)))
                    pts += super.getScore();
                else
                    pts -= this.score_mauvais;
            }
        }

        return pts;
    }
}
