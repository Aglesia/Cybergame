package com.lps2ima.dfr.cybergame;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lps2ima.dfr.cybergame.Jeux.*;
import com.lps2ima.dfr.cybergame.Slides.ChoixSimple;
import com.lps2ima.dfr.cybergame.Slides.Titre;

/**
 * Classe principale, lance et coordonne l'application
 */
public class MainActivity extends Activity {
    // Constantes
    public static final int ECRAN_TITRE = 0;
    public static final int ECRAN_SCORE = 1;
    public static final int ECRAN_JEU = 2; // DOIT ETRE LA DERNIERE VALEUR (LA PLUS GRANDE) DES CONSTANTES D'ECRAN

    public static final Jeu[] LISTE_JEUX = new Jeu[]{new Test()}; // Ensemble de tous les jeux à charger

    // Attributs
    private int ecran_actuel = ECRAN_TITRE; // Ecran titre
    private Jeu jeu = null;
    private Slide slide_titre = null;
    private Slide slide_score = new ChoixSimple("Pour le jeu \"Test\", vous avez obtenu 0 points.", new String[]{"Revenir à l'accueil"}, 0, 0);

    // Etats de l'application
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide);

        // On crée la liste des noms de jeu
        String[] noms = new String[LISTE_JEUX.length];
        for(int i=0; i<LISTE_JEUX.length; i++)
            noms[i] = LISTE_JEUX[i].getNom();

        // On crée la slide d'écran titre
        slide_titre = new Titre(noms);

        this.afficherEcranTitre();
    }

    // Méthodes de l'application
    /**
     * Methode appelée lors d'un appuie sur un bouton
     * @param view bouton appuyé
     */
    public void appuieBouton(View view) {
        int no_bouton = this.numeroBouton(view);
        Log.d("appuieBouton()", "Appuie sur le bouton "+no_bouton);
        // Si c'est un écran de question, on appelle la méthode de sélection de la réponse
        if(this.ecran_actuel >= ECRAN_JEU && this.jeu != null) {
            // On traite la réponse donnée par l'utilisateur
            int slide_suivante = this.jeu.selectionReponse(this.ecran_actuel-ECRAN_JEU, no_bouton);
            Log.d("appuieBouton()", "Nouvelle Slide : "+slide_suivante);

            // On affiche la slide suivante
            afficherSlide(this.jeu.getSlide(slide_suivante));
            if(this.ecran_actuel >= ECRAN_JEU)
                this.ecran_actuel = slide_suivante+ECRAN_JEU;
        }
        // Si c'est la fin d'un jeu
        else if(this.ecran_actuel == ECRAN_SCORE){
            Log.d("appuieBouton()", "Fin du jeu \""+this.jeu.getNom()+"\"");
            // On revient à l'écran titre
            this.jeu = null;
            this.afficherEcranTitre();
        }
        // S on demande à quitter
        else if(no_bouton>LISTE_JEUX.length || no_bouton<=0) {
            Log.d("appuieBouton()", "Fin du game !");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                finishAndRemoveTask (); // Tue l'appli
            else
                finish(); // Ne le tue pas totalement
        }
        // Sinon on regarde quel jeu doit être lancé
        else{
            this.ecran_actuel = ECRAN_JEU+no_bouton;
            this.chargerJeu(this.LISTE_JEUX[no_bouton-1]);
        }
    }

    /**
     * Affiche la slide demandée, avec une question (ou une explication) et un ou plusieurs boutons de réponse
     * @param s Slide à afficher
     */
    private void afficherSlide(Slide s){
        // Si la partie est finie, on passe à l'écran des scores
        if(s.type()){
            Log.d("afficherSlide()", "Affichage de la Slide des scores");
            this.ecran_actuel = ECRAN_SCORE;
            afficherScore(this.jeu);
        }
        // Sinon, on affiche la vue Slide et on la remplie
        else{
            Log.d("afficherSlide()", "Affichage de la nouvelle Slide");
            // On vide la slide
            LinearLayout layout_secondaire = this.findViewById(R.id.layout_secondaire);
            layout_secondaire.removeAllViews();

            // On modifie le texte
            TextView titre = this.findViewById(R.id.titre);
            TextView texte = this.findViewById(R.id.texte);
            if(this.jeu != null)
                titre.setText(this.jeu.getNom());
            else
                titre.setText(R.string.app_name);
            texte.setText(s.getTexteSlide());

            // On ajoute les éléments
            s.afficher(layout_secondaire, this);
        }
    }

    /**
     * Charge l'écran d'affichage du score, avec le nom du jeu, le nombre de points max et le nombre de points récoltés
     * @param j com.lps2ima.dfr.cybergame.Jeu terminé
     */
    private void afficherScore(Jeu j){
        Log.d("afficherScore()", "Affichage du score");
        this.ecran_actuel = ECRAN_SCORE;
        this.afficherSlide(this.slide_score);
        TextView texte = this.findViewById(R.id.texte);
        texte.setText("Pour le jeu \""+j.getNom()+"\", vous avez obtenu "+j.getScore()+" points.");
    }

    /**
     * Charge l'écran titre, qui contient la liste des jeux possibles, le bouton quitter et la liste des remerciements
     * (Liste des personnes ayant participés à la création de l'appli, mais aussi des questions/Slides)
     */
    private void afficherEcranTitre(){
        Log.d("afficherEcranTitre()", "Affichage de l'écran d'accueil");
        this.afficherSlide(this.slide_titre);
        this.ecran_actuel = ECRAN_TITRE;
        this.jeu = null;
    }

    /**
     * Charge un nouveau jeu et lance sa première Slide
     * @param jeu Jeu à initialiser et lancer
     */
    private void chargerJeu(Jeu jeu){
        Log.d("chargerJeu()", "Chargement du jeu \""+jeu.getNom()+"\"");
        // S'il y a un jeu à lancer, on le lance
        if(jeu != null){
            this.jeu = jeu;
            int s = jeu.initialiser();
            Log.d("chargerJeu()", "Slide à afficher : "+s);
            // On charge la slide s'il y en a une
            if(s > 0)
                this.afficherSlide(jeu.getSlide(s));
            // Sinon on retourne à l'écran titre
            else {
                this.afficherEcranTitre();
                this.ecran_actuel = ECRAN_TITRE;
            }
        }
        // Sinon, le jeu n'existe pas, on retourne à l'écran titre
        else{
            this.afficherEcranTitre();
            this.ecran_actuel = ECRAN_TITRE;
        }
    }

    /**
     * Indique le numéro porté par le bouton passé en paramètre
     * @param v Bouton (vue)
     * @return Numéro du bouton, ou 0 s'il n'en a pas
     */
    private int numeroBouton(View v){
        String desc = ((Button)v).getContentDescription().toString();
        int ret = 0;
        // On récupère le numéro du bouton via sa description
        if(android.text.TextUtils.isDigitsOnly(desc))
            ret = Integer.parseInt(desc);

        return ret;
    }
}
