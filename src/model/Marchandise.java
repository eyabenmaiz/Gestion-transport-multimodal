package model;

/**
 * Représente une marchandise transportée au sein d'une cargaison.
 * Chaque marchandise est identifiée par un numéro unique dans sa cargaison.
 */
public class Marchandise {

    private int numero;
    private double poids;   // en kg
    private double volume;  // en unités de volume (ex: cm3 ou m3, selon convention du projet)

    public Marchandise(int numero, double poids, double volume) {
        this.numero = numero;
        this.poids = poids;
        this.volume = volume;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Marchandise{" +
                "numero=" + numero +
                ", poids=" + poids +
                ", volume=" + volume +
                '}';
    }
}
