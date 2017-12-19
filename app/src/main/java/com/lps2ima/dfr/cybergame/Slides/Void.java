package com.lps2ima.dfr.cybergame.Slides;

import android.widget.LinearLayout;

import com.lps2ima.dfr.cybergame.MainActivity;
import com.lps2ima.dfr.cybergame.Slide;

/**
 * Created by Dorian on 14/12/2017.
 */

public class Void extends Slide {
    /**
     * Constructeur, contient toutes les propriétés du slide
     */
    public Void() {
        super("");
    }

    @Override
    public void afficher(LinearLayout layout, MainActivity activity) {

    }

    @Override
    public boolean isBonneReponse(int numero_bouton) {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public boolean type() {
        return true;
    }
}
