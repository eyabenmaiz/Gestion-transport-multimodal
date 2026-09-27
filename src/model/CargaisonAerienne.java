package model;

/**
 * Cargaison transportée par voie aérienne (avion).
 * Coût = 10 * distance * poidsTotal si volumeTotal < 80000
 * Coût = 12 * distance * poidsTotal si volumeTotal >= 80000
 */
public class CargaisonAerienne extends Cargaison {

    private static final double SEUIL_VOLUME = 80000;

    public CargaisonAerienne(double distance) {
        super(distance);
    }

    @Override
    public double calculerCout() {
        double volumeTotal = getVolumeTotal();
        double poidsTotal = getPoidsTotal();
        if (volumeTotal < SEUIL_VOLUME) {
            return 10 * distance * poidsTotal;
        } else {
            return 12 * distance * poidsTotal;
        }
    }

    @Override
    public String getType() {
        return "Aérienne";
    }
}
