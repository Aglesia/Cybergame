package com.lps2ima.dfr.cybergame.Slides;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lps2ima.dfr.cybergame.MainActivity;
import com.lps2ima.dfr.cybergame.Slide;

import java.util.Arrays;

/**
 * Created by Dorian on 14/12/2017.
 */

public class ChoixMultiples extends Slide {
    private String[] reponses;
    private Integer[] bonnes_reponses;
    private CheckBox[] boxes;
    private int score_bon;
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
    public ChoixMultiples(String texte, int image, String[] reponses, Integer[] bonnes_reponses, int score_bon, int score_mauvais) {
        super(texte);
        this.image = image;
        this.reponses = reponses;
        this.bonnes_reponses = bonnes_reponses;
        this.boxes = new CheckBox[reponses.length];
        this.score_bon = score_bon;
        this.score_mauvais = score_mauvais;
        this.score = score_bon * bonnes_reponses.length;
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
        
        // On ajoute les checkboxes
        for(int i=0; i<this.boxes.length; i++){
            boxes[i] = Slide.creerCaseCocher(activite, reponses[i]);
            if(Arrays.asList(this.bonnes_reponses).contains(i+1))
                boxes[i].setContentDescription("valide");
            layout.addView(boxes[i]);
            this.reponses_view.add(boxes[i]);
        }

        // On ajoute le bouton valider
        layout.addView(Slide.creerBouton(activite, "Valider", 1));
    }

    @Override
    public int choixReponse(int numero_bouton, MainActivity activite) {
        int pts = 0;

        // Selon les réponses données par l'utilisateur, on calcul le nombre de points
        for(int i=0; i<this.boxes.length; i++){
            // S'il est coché
            if(this.boxes[i].isChecked()){
                // Si c'est bon, on gagne 1 point
                if(Arrays.asList(this.bonnes_reponses).contains(i+1))
                    pts += this.score_bon;
                else
                    pts -= this.score_mauvais;
            }
        }

        return pts;
    }
}
