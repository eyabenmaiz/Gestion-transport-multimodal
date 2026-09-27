package model;

/**
 * Cargaison transportée par voie routière (camion).
 * Coût = 4 * distance * poidsTotal si volumeTotal < 380000
 * Coût = 6 * distance * poidsTotal si volumeTotal >= 380000
 */
public class CargaisonRoutiere extends Cargaison {

    private static final double SEUIL_VOLUME = 380000;

    public CargaisonRoutiere(double distance) {
        super(distance);
    }

    @Override
    public double calculerCout() {
        double volumeTotal = getVolumeTotal();
        double poidsTotal = getPoidsTotal();
        if (volumeTotal < SEUIL_VOLUME) {
            return 4 * distance * poidsTotal;
        } else {
            return 6 * distance * poidsTotal;
        }
    }

    @Override
    public String getType() {
        return "Routière";
    }
}
