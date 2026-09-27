package model;

/**
 * Exception levée lorsqu'on recherche une marchandise par son numéro
 * et qu'elle n'existe pas dans la cargaison.
 */
public class MarchandiseIntrouvableException extends Exception {

    public MarchandiseIntrouvableException() {
        super("Marchandise introuvable");
    }

    public MarchandiseIntrouvableException(String message) {
        super(message);
    }
}
