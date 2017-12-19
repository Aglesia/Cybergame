package com.lps2ima.dfr.cybergame.Slides;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.lps2ima.dfr.cybergame.MainActivity;
import com.lps2ima.dfr.cybergame.R;

/**
 * Created by Dorian on 14/12/2017.
 */

public class TitreImage extends Titre {
    private int[] images;

    /**
     * Constructeur, contient toutes les propriétés du slide
     *
     * @param noms Noms des catégories possibles
     * @param images Liste des ID (R.drawable) des images à afficher à la place des boutons classiques
     */
    public TitreImage(String[] noms, int[] images) {
        super(noms);
        this.images = images;
    }

    @Override
    public void afficher(LinearLayout layout, MainActivity activity) {
        // On affiche la liste des jeux
        for(int i=0; i<this.noms.length; i++)
            this.addImageBouton(layout, activity, this.noms[i], this.images[i], i+1);
        this.addImageBouton(layout, activity, "Quitter", R.drawable.quitter, 0);

        // On affiche un séparateur
        Space s = new Space(activity);
        s.setMinimumHeight(256);
        layout.addView(s);

        // On affiche nos informations
        TextView texte = new TextView(activity);
        texte.setText(super.MESSAGE_A_PROPOS);
        layout.addView(texte);

        // On affiche un séparateur
        s = new Space(activity);
        s.setMinimumHeight(128);
        layout.addView(s);

        s = new Space(activity);
        s.setMinimumHeight(256);
        layout.addView(s);

        // On affiche les remerciements
        texte = new TextView(activity);
        texte.setText(super.MESSAGE_REMERCIEMENT);
        layout.addView(texte);
    }



    /**
     * Crée un nouveau bouton, lui assigne le texte et son numéro de réponse, ainsi que son listener
     * @param texte Texte à afficher sur le bouton
     * @param numero Numéro de réponse correspondant
     * @return Le bouton fraichement créé
     */
    protected void addImageBouton(LinearLayout layout, final MainActivity activite, String texte, int image, int numero){
        // On crée le bouton avec son nom
        final ImageButton bt = new ImageButton(activite);
        bt.setImageResource(image);
        bt.setContentDescription(String.valueOf(numero));
        bt.setClickable(true);
        bt.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        bt.setScaleType(ImageView.ScaleType.CENTER);
        bt.setAdjustViewBounds(true);
        bt.setBackgroundColor(Color.TRANSPARENT);

        // On ajoute le listener
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activite.appuieBouton(bt);
            }
        });

        // On crée le texte
        TextView t = new TextView(activite);
        t.setText(texte);
        t.setTextSize(30);
        t.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        t.setGravity(Gravity.CENTER);
        t.setClickable(true);
        t.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2));

        // On ajoute le listener
        t.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activite.appuieBouton(bt);
            }
        });

        // On crée le layout horizontal
        LinearLayout l = new LinearLayout(activite);
        l.setOrientation(LinearLayout.HORIZONTAL);
        l.addView(bt);
        l.addView(t);

        // On ajoute le bouton à la liste
        layout.addView(l);
    }
}
