package com.lps2ima.dfr.cybergame;

/**
 * Created by dorian on 14/12/17.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Classe "Slide", contient :
 *  - Une question
 *
 * Une slide correspond à une question/épreuve d'une partie.
 * Chaque slide rapporte un certain nombre de points en cas de bonne réponse, 0 en cas de mauvaise réponse.
 * Une slide peut ne pas avoir de bonne réponse (appelée "slide impasse").
 */
public abstract class Slide {
    private String texte_slide = ""; // Texte à afficher sur la slide
    protected ArrayList<View> reponses_view = new ArrayList<>();
    protected int score = 0;

    /**
     * Constructeur, contient toutes les propriétés du slide
     * @param texte Texte de présentation de la Slide/la question
     */
    public Slide(String texte){
        this.texte_slide = texte;
    }

    /**
     * Donne le texte à afficher sur la slide (la question)
     * @return Le texte de la slide
     */
    public String getTexteSlide(){
        return this.texte_slide;
    }

    /**
     * Indique le numéro de bouton qui a été choisi. En retour, donne le nombre de points que cette réponse donne
     * @param numero_bouton Numéro du bouton appuyé
     * @param activity MainActivity, pour créer les views
     * @return Le nombre de points remportés
     */
    public abstract int choixReponse(int numero_bouton, MainActivity activity);

    /**
     * Indique le nombre maximum de points que cette slide peut fournir
     * @return Score maximum
     */
    public int maxPoints(){
        return this.score;
    }

    /**
     * Indique si c'est une slide spécifique (Void)
     * @return true si c'est une slide spéciale
     */
    public boolean type(){
        return false;
    }

    /**
     * Crée un nouveau bouton, lui assigne le texte et son numéro de réponse, ainsi que son listener
     * @param texte Texte à afficher sur le bouton
     * @param numero Numéro de réponse correspondant
     * @return Le bouton fraichement créé
     */
    public static Button creerBouton(final MainActivity activite, String texte, int numero){
        // On crée le bouton avec son nom
        final Button bt = new Button(activite);
        bt.setText(texte);
        bt.setContentDescription(String.valueOf(numero));

        // On ajoute le listener
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activite.appuieBouton(bt);
            }
        });

        return bt;
    }

    /**
     * Crée un nouveau bouton, lui assigne le texte et son numéro de réponse, ainsi que son listener
     * @param texte Texte à afficher sur le bouton
     * @return Le bouton fraichement créé
     */
    public static CheckBox creerCaseCocher(final MainActivity activite, String texte){
        // On crée le checkbox avec son nom
        final CheckBox ck = new CheckBox(activite);
        ck.setText(texte);
        ck.setChecked(false);

        return ck;
    }

    /**
     * Ajoute ses vues à la layout de l'écran
     * @param layout LinearLayout dans lequel on peut ajouter nos infos
     * @param activite Activité principale, utile pour l'accès à l'affichage
     */
    public abstract void afficher(LinearLayout layout, MainActivity activite);

    /**
     * Enregistre toutes les vues dans un autre layout, pour les afficher dans l'écran de réponse
     * @param layout linearLayout dans lequel enregistrer les réponses
     * @param context MainActivity pour créer de nouvelles vues
     */
    public void afficherReponse(LinearLayout layout, Context context) {
        layout.setOrientation(LinearLayout.VERTICAL);
        for(View v : this.reponses_view){
            // Selon le type de la vue
            if(v instanceof CheckBox){
                TextView t = new TextView(context);
                t.setText(((CheckBox) v).getText());
                if(((CheckBox)v).isChecked()) {
                    t.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
                    if(v.getContentDescription() == "valide")
                        t.setTextColor(Color.GREEN);
                    else
                        t.setTextColor(Color.RED);
                }
                else if(v.getContentDescription() == "valide")
                    t.setTextColor(Color.rgb(0, 150, 0));
                layout.addView(t);
            }
            else if(v instanceof EditText){
                TextView t = new TextView(context);
                t.setText("Vous avez écrit \""+((EditText) v).getText()+"\"");
                layout.addView(t);
            }
            else if(v instanceof TextView){
                layout.addView(v);
            }
        }
    }
}
