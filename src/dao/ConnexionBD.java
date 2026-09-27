package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gère la connexion à la base de données MySQL "transportdb".
 *
 * Nécessite le driver JDBC MySQL (mysql-connector-j) dans le classpath
 * du projet (voir le dossier lib/ et les instructions du README).
 */
public class ConnexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/transportdb?useSSL=false&serverTimezone=UTC";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "root"; // à adapter selon votre configuration MySQL locale

    private static Connection connection;

    private ConnexionBD() {
        // Classe utilitaire : pas d'instanciation
    }

    /**
     * Retourne une connexion unique (singleton) vers la base de données.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
        }
        return connection;
    }

    public static void fermerConnexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
