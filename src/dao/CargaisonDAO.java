package dao;

import model.Cargaison;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Accès aux données pour la table "cargaison".
 */
public class CargaisonDAO {

    /**
     * Insère une cargaison en base et retourne l'id généré automatiquement.
     */
    public int inserer(Cargaison cargaison) throws SQLException {
        String sql = "INSERT INTO cargaison (type, distance) VALUES (?, ?)";
        Connection cnx = ConnexionBD.getConnection();

        try (PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cargaison.getType());
            ps.setDouble(2, cargaison.getDistance());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Échec de l'insertion de la cargaison, aucun id généré.");
    }
}
