package com.lps2ima.dfr.cybergame.Slides;

import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.lps2ima.dfr.cybergame.MainActivity;
import com.lps2ima.dfr.cybergame.Slide;

/**
 * Created by Dorian on 14/12/2017.
 */

public class Titre extends Slide {
    static final String MESSAGE_REMERCIEMENT = "Un grand merci à ";
    static final String MESSAGE_A_PROPOS = "A propos de nous :";
    String[] noms = new String[0];

    /**
     * Constructeur, contient toutes les propriétés du slide
     * @param noms Noms des catégories possibles
     */
    public Titre(String[] noms) {
        super("Bienvenue, choisissez une catégorie");
        this.noms = noms;
    }

    @Override
    public int choixReponse(int numero_bouton, MainActivity activity) {
        return 0;
    }

    @Override
    public void afficher(LinearLayout layout, MainActivity activity) {
        // On affiche la liste des jeux
        for(int i=0; i<this.noms.length; i++)
            layout.addView(this.creerBouton(activity, this.noms[i], i+1));
        layout.addView(this.creerBouton(activity, "Quitter", 0));

        // On affiche un séparateur
        Space s = new Space(activity);
        s.setMinimumHeight(256);
        layout.addView(s);

        // On affiche nos informations
        TextView texte = new TextView(activity);
        texte.setText(MESSAGE_A_PROPOS);
        layout.addView(texte);

        // On affiche un séparateur
        s = new Space(activity);
        s.setMinimumHeight(256);
        layout.addView(s);

        // On affiche les remerciements
        texte = new TextView(activity);
        texte.setText(MESSAGE_REMERCIEMENT);
        layout.addView(texte);
    }
}
