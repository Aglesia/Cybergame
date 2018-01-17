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

public class Securite extends Jeu {
    public Securite(Context context) {
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

        this.slides[0] = new ChoixSimple("Bienvenue dans le quiz sur la sécurité",
                0, new String[]{"Continuer"}, 0, 0);
        this.reponses[0] = "";

        this.slides[1] = new ChoixSimple("Sélectionnez la requête qu'on peut utilliser pour faire une injection SQL", 0, new String[]{"SELECT * FROM Database", "' OR 1==1 OR '", "' AND 1==1 AND '"}, 2, 1);
        this.reponses[1] = "On ferme la quote, on fait une condition vraie, puis on réouvre la quote. On place des 'OR' entre chaque";

        this.slides[2] = new ChoixSimple("Vous recevez ce mail, que faites-vous ?", R.drawable.mail, new String[]{"Je l'ouvre et clique sur le lien pour vérifier mon compte Apple", "Je le transmet à tous mes amis, pour qu'ile vérifient leur compte Apple", "Je le supprime sans même l'ouvrir"}, 3, 1);
        this.reponses[2] = "C'est forcément un mail frauduleux (les charactères non latins le prouvent), je le supprime sans en tenir compte";

        this.slides[3] = new ChoixMultiples("Sélectionnez les 10 propositions du Top10 d'OWASP", 0, new String[]{"Redirection et envoi non valide", "Injection", "Mauvais utilisateur", "XSS", "Ordinateur virusé", "Violation de gestion d'authentification et de session", "Mauvaise configuration de sécurité", "Exposition de données sensibles", "Utilisation de composants avec des vulnérabilités connus", "Falsification de requêtes intersite", "Serveur hors fonction", "Fenêtre ouverte", "Manque de contrôles d'accès au niveau fonctionnel", "référence directe non sécurisé à un objet", "Chat"}, new Integer[]{1, 2, 4, 6, 7, 8, 9, 10, 13, 14}, 1, 2);
        this.reponses[3] = "Les réponses sont disponibles sur le site de l'OWASP";

        this.slides[4] = new ChoixSimple("Quelle est la classe de l'adresse IP '192.168.1.1' ?", 0, new String[]{"Classe A", "Classe B", "Classe C", "Classe D", "Classe E"}, 3, 1);
        this.reponses[4] = "C'est le plus souvent utilisé avec un masque de 255.255.255.0, soit classe C";

        this.slides[5] = new ChoixSimple("Qu'est-ce que le cryptage ?", 0, new String[]{"Le fait de crypter des données", "C'est un procédé de cryptographie grâce auquel on rend le document illisible", "Un terme qui ne veux absolument rien dire", "Aucune idée"}, 3, 1);
        this.reponses[5] = "NE ! DITES ! JAMAIS !    CRYPTAGE !!!! C'est un terme qui ne veut rien dire, en français le terme exacte est le chiffrement";

        this.slides[6] = new ChoixSimple("Quel langage n'est pas un langage de programmation ?", 0, new String[]{"ASM", "C", "JAVA", "HTML", "PHP"}, 4, 1);
        this.reponses[6] = "Le HTML est un lagage de description";

        this.slides[7] = new ChoixSimple("A quoi sert le site RootMe ?", 0, new String[]{"A faire des défis de programmation en sécurité", "Un site de rencontre pour geeks", "A Tchater avec d'autres gens"}, 1, 1);
        this.reponses[7] = "RootMe met à disposition des défis de hacking et de programmation";

        this.slides[8] = new ChoixSimple("Pourquoi ne faut-il pas stocker des mots de passe en clair ?", 0, new String[]{"Si quelque'un de mal intentionné les récupère, il ne doit pas pouvoir les lire", "parce que le stockage en clair prend plus de place que les mots de passes chiffrés", "Ils sont trop difficiles à relire"}, 1, 1);
        this.reponses[8] = "Il ne faut jamais stocker les mots de passe en clair, car toute donnée peut être récupérée et partagée";

        this.slides[9] = new ChoixSimple("Je suis logué en ROOT sur mon PC, je fait la commande 'rm -rf /'. Que se passe-t-il ?", 0, new String[]{"ça crée une sauvegarde de mon disque dur", "ça sécurise mes données", "ça efface ma partition système"}, 3, 1);
        this.reponses[9] = "STOP !!! Ne faites jamais ça, ça supprime absolument tout sur votre système !!";

        // On indique de commencer à la première slide (1 et non 0 !!)
        return 1;
    }

    @Override
    public String getNom() {
        return "Sécurité";
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
