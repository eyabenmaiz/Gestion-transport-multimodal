package dao;

import model.Marchandise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Accès aux données pour la table "marchandise".
 */
public class MarchandiseDAO {

    /**
     * Insère une marchandise en base, rattachée à une cargaison (idCargaison).
     */
    public void inserer(Marchandise marchandise, int idCargaison) throws SQLException {
        String sql = "INSERT INTO marchandise (id, poids, volume, idCargaison) VALUES (?, ?, ?, ?)";
        Connection cnx = ConnexionBD.getConnection();

        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, marchandise.getNumero());
            ps.setDouble(2, marchandise.getPoids());
            ps.setDouble(3, marchandise.getVolume());
            ps.setInt(4, idCargaison);
            ps.executeUpdate();
        }
    }
}
