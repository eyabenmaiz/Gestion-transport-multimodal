package model;

/**
 * Cargaison transportée par voie maritime (navire).
 * Coût = 5 * distance * poidsTotal si poidsTotal < 300000
 * Coût = 8 * distance * poidsTotal si poidsTotal >= 300000
 */
public class CargaisonMaritime extends Cargaison {

    private static final double SEUIL_POIDS = 300000;

    public CargaisonMaritime(double distance) {
        super(distance);
    }

    @Override
    public double calculerCout() {
        double poidsTotal = getPoidsTotal();
        if (poidsTotal < SEUIL_POIDS) {
            return 5 * distance * poidsTotal;
        } else {
            return 8 * distance * poidsTotal;
        }
    }

    @Override
    public String getType() {
        return "Maritime";
    }
}
