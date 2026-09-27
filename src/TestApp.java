import model.Cargaison;
import model.CargaisonAerienne;
import model.CargaisonRoutiere;
import model.Marchandise;
import model.MarchandiseIntrouvableException;

/**
 * Application de test en console, conforme à la partie 3 de l'énoncé :
 * - Créer une cargaison routière, ajouter 3 marchandises, afficher, coût
 * - Créer une cargaison aérienne, ajouter 3 marchandises, afficher, coût
 */
public class TestApp {

    public static void main(String[] args) {

        // ----- Cargaison Routière -----
        System.out.println("=== Cargaison Routière ===");
        Cargaison cargaisonRoutiere = new CargaisonRoutiere(120);
        cargaisonRoutiere.ajouterMarchandise(new Marchandise(1, 5000, 100000));
        cargaisonRoutiere.ajouterMarchandise(new Marchandise(2, 3000, 150000));
        cargaisonRoutiere.ajouterMarchandise(new Marchandise(3, 2000, 90000));

        afficherMarchandises(cargaisonRoutiere);
        System.out.println("Coût de la cargaison routière : " + cargaisonRoutiere.calculerCout());

        // ----- Cargaison Aérienne -----
        System.out.println("\n=== Cargaison Aérienne ===");
        Cargaison cargaisonAerienne = new CargaisonAerienne(800);
        cargaisonAerienne.ajouterMarchandise(new Marchandise(10, 200, 20000));
        cargaisonAerienne.ajouterMarchandise(new Marchandise(11, 150, 30000));
        cargaisonAerienne.ajouterMarchandise(new Marchandise(12, 100, 15000));

        afficherMarchandises(cargaisonAerienne);
        System.out.println("Coût de la cargaison aérienne : " + cargaisonAerienne.calculerCout());

        // ----- Démonstration de l'exception -----
        System.out.println("\n=== Test de l'exception MarchandiseIntrouvableException ===");
        try {
            cargaisonRoutiere.consulterMarchandise(999);
        } catch (MarchandiseIntrouvableException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void afficherMarchandises(Cargaison cargaison) {
        System.out.println("Marchandises :");
        for (Marchandise m : cargaison.afficherMarchandises()) {
            System.out.println("  " + m);
        }
        System.out.println("Volume total : " + cargaison.getVolumeTotal());
        System.out.println("Poids total  : " + cargaison.getPoidsTotal());
    }
}
