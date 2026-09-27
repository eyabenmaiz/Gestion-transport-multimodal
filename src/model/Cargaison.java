package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant une cargaison transportée par la société.
 * Une cargaison contient un ensemble de marchandises et est définie par
 * sa distance de parcours. Le calcul du coût dépend du type concret de
 * cargaison (Routière, Aérienne, Maritime).
 */
public abstract class Cargaison {

    protected double distance;
    protected List<Marchandise> marchandises;

    public Cargaison(double distance) {
        this.distance = distance;
        this.marchandises = new ArrayList<>();
    }

    /**
     * Ajoute une marchandise à la cargaison.
     */
    public void ajouterMarchandise(Marchandise m) {
        marchandises.add(m);
    }

    /**
     * Retourne la liste de toutes les marchandises de la cargaison.
     */
    public List<Marchandise> afficherMarchandises() {
        return marchandises;
    }

    /**
     * Recherche une marchandise par son numéro.
     *
     * @param numero le numéro de la marchandise recherchée
     * @return la marchandise correspondante
     * @throws MarchandiseIntrouvableException si aucune marchandise ne
     *         possède ce numéro
     */
    public Marchandise consulterMarchandise(int numero) throws MarchandiseIntrouvableException {
        for (Marchandise m : marchandises) {
            if (m.getNumero() == numero) {
                return m;
            }
        }
        throw new MarchandiseIntrouvableException();
    }

    /**
     * Calcule le volume total de toutes les marchandises de la cargaison.
     */
    public double getVolumeTotal() {
        double total = 0;
        for (Marchandise m : marchandises) {
            total += m.getVolume();
        }
        return total;
    }

    /**
     * Calcule le poids total de toutes les marchandises de la cargaison.
     */
    public double getPoidsTotal() {
        double total = 0;
        for (Marchandise m : marchandises) {
            total += m.getPoids();
        }
        return total;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Calcule le coût de la cargaison. La formule dépend du type concret
     * de cargaison (implémentée dans les sous-classes).
     */
    public abstract double calculerCout();

    /**
     * Retourne le type de la cargaison sous forme de texte
     * (utile pour l'affichage et la persistance en base de données).
     */
    public abstract String getType();

    @Override
    public String toString() {
        return getType() + " - distance=" + distance + ", cout=" + calculerCout();
    }
}
