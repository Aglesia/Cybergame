package com.lps2ima.dfr.cybergame.Jeux;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.lps2ima.dfr.cybergame.Jeu;
import com.lps2ima.dfr.cybergame.R;
import com.lps2ima.dfr.cybergame.Slide;
import com.lps2ima.dfr.cybergame.Slides.ChoixMultiples;
import com.lps2ima.dfr.cybergame.Slides.ChoixSimple;
import com.lps2ima.dfr.cybergame.Slides.MotsClefs;

import java.util.ArrayList;

/**
 * Created by robin on 17/01/2018.
 */

public class Agile extends Jeu {

    public Agile(Context context) {
        super(context);
    }

    @Override
    public int selectionReponse(int numero_slide, int numero_bouton) {
        int slide_suivante = 0;
        Log.d("selectionReponse()", "Numéro de la slide : "+numero_slide+", Numéro du bouton : "+numero_bouton);

        // On fait un switch pour les slides particulières
        switch (numero_slide){
            case 1: // Slide de présentation
                slide_suivante = 2;
                break;

            // Par défaut, on ne fait qu'ajouter les points et passer à la slide suivante
            default:
                slide_suivante = super.selectionReponse(numero_slide, numero_bouton);
                break;
        }

        // On met à jour la barre de progression
        this.setBarreProgression(slide_suivante);
        return slide_suivante;
    }

    @Override
    public int initialiser() {
        super.score_joueur = 0;
        this.views = new ArrayList<>();

        // On crée toutes les slides
        this.slides = new Slide[10];
        this.reponses = new String[10];

        this.slides[0] = new ChoixSimple("Bienvenue dans le quiz sur l'agilité",
                0, new String[]{"Continuer"}, 0, 0);
        this.reponses[0] = "";

        this.slides[1] = new ChoixSimple("A quoi sert le Scrum master ?", 0, new String[]{"Rien", "A diriger l'équipe", "A gérer le projet", "Rendre des comptes au client"}, 2, 1);
        this.reponses[1] = "Le scrum master sert à diriger une équipe lors d'un projet.";

        this.slides[2] = new ChoixMultiples("Qu'est-ce qui rentre en compte pour le calcul de la vélocité ?", 0, new String[]{"Story points", "business values", "nombre de membres de l'équipe", "nombre de sprints", "durée du sprint"}, new Integer[]{1, 3, 5}, 1, 2);
        this.reponses[2] = "On calcul la vélocité par (Nombre de membres de l'équipe * nombre de jours)/nombre de story points";

        this.slides[3] = new ChoixSimple("Quelle valeur peut-on ajuster dans un projet ?", 0, new String[]{"Le budget", "Le périmètre", "La durée", "rien, tout est figé"}, 2, 1);
        this.reponses[3] = "En accord avec le client, nous pouvons modifier le périmètre du projet";

        this.slides[4] = new ChoixSimple("A ce jour, quelle méthode de gestion de projet est le mieux ?", 0, new String[]{"Agile", "Cycle en V"}, 1, 1);
        this.reponses[4] = "Aujourd'hui, les entreprises se tournent vers la méthode agile";

        this.slides[5] = new ChoixSimple("Pour quel type d'entreprise est prévue la méthode agile ?", 0, new String[]{"PME", "multinationnales", "CGE", "Toutes"}, 1, 1);
        this.reponses[5] = "La méthode agile est adaptée pour tout type d'entreprises";

        this.slides[6] = new MotsClefs("Comment s'appelle le rôle du chargé de communication avec le client (en toutes lettres) ?", 0, new String[]{"Product Owner"}, 1);
        this.reponses[6] = "Le rôle est : Product Owner";

        this.slides[7] = new ChoixSimple("Combien de temps dure un sprint ?", 0, new String[]{"Un jour", "3 semaines", "Un semestre", "Un an"}, 2, 1);
        this.reponses[7] = "Un sprint dure en moyenne 3 semaines";

        this.slides[8] = new ChoixMultiples("Qu'a-t-on besoin pour faire un product backlog ?", 0, new String[]{"EPIC", "US", "Burn-Up", "Burn-Down", "Features"}, new Integer[]{1, 2, 5}, 1, 1);
        this.reponses[8] = "On utilise des EPIC, US et Features";

        this.slides[9] = new ChoixSimple("Quelle est la différence entre un burn-up et un burn-down ?", 0, new String[]{"Le burn-up monte, le burn-down descend", "Le burn-up est sur le projet, le Burn-down est sur le sprint", "C'est la même chose", "La réponse D"}, 2, 1);
        this.reponses[9] = "Sérieusement ?!";

        // On indique de commencer à la première slide (1 et non 0 !!)
        return 1;
    }

    @Override
    public String getNom() {
        return "Agile";
    }

    @Override
    public int getImageId() {
        return R.drawable.test;
    }

    @Override
    public ArrayList<View> getResumeReponses() {
        return this.views;
    }
}
