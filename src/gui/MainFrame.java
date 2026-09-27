package gui;

import dao.CargaisonDAO;
import dao.MarchandiseDAO;
import model.Cargaison;
import model.CargaisonAerienne;
import model.CargaisonMaritime;
import model.CargaisonRoutiere;
import model.Marchandise;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Fenêtre principale de gestion des cargaisons et marchandises.
 *
 * - Formulaire de création d'une cargaison (distance + type)
 * - Liste des cargaisons créées
 * - Formulaire d'ajout de marchandise à la cargaison sélectionnée
 * - Tableau des marchandises de la cargaison sélectionnée
 * - Affichage du coût total et bouton d'enregistrement en base
 */
public class MainFrame extends JFrame {

    // Données en mémoire
    private final List<Cargaison> cargaisons = new ArrayList<>();
    private Cargaison cargaisonCourante;

    // Composants - création de cargaison
    private final JTextField txtDistance = new JTextField(8);
    private final JComboBox<String> cbType = new JComboBox<>(new String[]{"Routière", "Aérienne", "Maritime"});
    private final JButton btnCreerCargaison = new JButton("Ajouter");

    // Liste des cargaisons
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> listCargaisons = new JList<>(listModel);

    // Composants - ajout de marchandise
    private final JTextField txtNum = new JTextField(5);
    private final JTextField txtPoids = new JTextField(5);
    private final JTextField txtVolume = new JTextField(5);
    private final JButton btnAjouterMarchandise = new JButton("Add");

    // Tableau des marchandises
    private final DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"NUM", "Poids", "Volume"}, 0);
    private final JTable table = new JTable(tableModel);

    // Coût + enregistrement
    private final JLabel lblCout = new JLabel("0");
    private final JButton btnEnregistrer = new JButton("Enregistrer dans la base");

    public MainFrame() {
        super("Cargaisons - Société de Transport Multimodal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        add(construirePanneauCreation(), BorderLayout.NORTH);
        add(construirePanneauCentral(), BorderLayout.CENTER);
        add(construirePanneauBas(), BorderLayout.SOUTH);

        brancherEvenements();
    }

    private JPanel construirePanneauCreation() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Distance:"));
        panel.add(txtDistance);
        panel.add(new JLabel("Type:"));
        panel.add(cbType);
        panel.add(btnCreerCargaison);
        return panel;
    }

    private JPanel construirePanneauCentral() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Gauche : liste des cargaisons
        listCargaisons.setBorder(BorderFactory.createTitledBorder("Cargaisons"));
        JScrollPane scrollListe = new JScrollPane(listCargaisons);
        scrollListe.setPreferredSize(new Dimension(220, 0));
        panel.add(scrollListe, BorderLayout.WEST);

        // Droite : formulaire marchandise + tableau
        JPanel droite = new JPanel(new BorderLayout(5, 5));

        JPanel formMarchandise = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formMarchandise.add(new JLabel("Num:"));
        formMarchandise.add(txtNum);
        formMarchandise.add(new JLabel("Poids:"));
        formMarchandise.add(txtPoids);
        formMarchandise.add(new JLabel("Volume:"));
        formMarchandise.add(txtVolume);
        formMarchandise.add(btnAjouterMarchandise);

        droite.add(formMarchandise, BorderLayout.NORTH);
        droite.add(new JScrollPane(table), BorderLayout.CENTER);

        panel.add(droite, BorderLayout.CENTER);
        return panel;
    }

    private JPanel construirePanneauBas() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Coût de la cargaison:"));
        lblCout.setPreferredSize(new Dimension(120, 20));
        lblCout.setBorder(BorderFactory.createEtchedBorder());
        panel.add(lblCout);
        panel.add(btnEnregistrer);
        return panel;
    }

    private void brancherEvenements() {
        btnCreerCargaison.addActionListener(e -> creerCargaison());
        btnAjouterMarchandise.addActionListener(e -> ajouterMarchandise());
        btnEnregistrer.addActionListener(e -> enregistrerDansLaBase());
        listCargaisons.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectionnerCargaison();
            }
        });
    }

    private void creerCargaison() {
        try {
            double distance = Double.parseDouble(txtDistance.getText().trim());
            String type = (String) cbType.getSelectedItem();

            Cargaison nouvelle;
            switch (type) {
                case "Routière":
                    nouvelle = new CargaisonRoutiere(distance);
                    break;
                case "Aérienne":
                    nouvelle = new CargaisonAerienne(distance);
                    break;
                case "Maritime":
                    nouvelle = new CargaisonMaritime(distance);
                    break;
                default:
                    throw new IllegalStateException("Type de cargaison inconnu : " + type);
            }

            cargaisons.add(nouvelle);
            listModel.addElement("Cargaison " + type + " - Distance=" + distance);
            txtDistance.setText("");

            listCargaisons.setSelectedIndex(listModel.getSize() - 1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir une distance valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selectionnerCargaison() {
        int index = listCargaisons.getSelectedIndex();
        if (index >= 0 && index < cargaisons.size()) {
            cargaisonCourante = cargaisons.get(index);
            rafraichirTableau();
            rafraichirCout();
        }
    }

    private void ajouterMarchandise() {
        if (cargaisonCourante == null) {
            JOptionPane.showMessageDialog(this, "Veuillez d'abord sélectionner ou créer une cargaison.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int num = Integer.parseInt(txtNum.getText().trim());
            double poids = Double.parseDouble(txtPoids.getText().trim());
            double volume = Double.parseDouble(txtVolume.getText().trim());

            cargaisonCourante.ajouterMarchandise(new Marchandise(num, poids, volume));

            txtNum.setText("");
            txtPoids.setText("");
            txtVolume.setText("");

            rafraichirTableau();
            rafraichirCout();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir des valeurs numériques valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rafraichirTableau() {
        tableModel.setRowCount(0);
        if (cargaisonCourante == null) {
            return;
        }
        for (Marchandise m : cargaisonCourante.afficherMarchandises()) {
            tableModel.addRow(new Object[]{m.getNumero(), m.getPoids(), m.getVolume()});
        }
    }

    private void rafraichirCout() {
        if (cargaisonCourante == null) {
            lblCout.setText("0");
            return;
        }
        lblCout.setText(String.valueOf(cargaisonCourante.calculerCout()));
    }

    private void enregistrerDansLaBase() {
        if (cargaisonCourante == null) {
            JOptionPane.showMessageDialog(this, "Aucune cargaison sélectionnée.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            CargaisonDAO cargaisonDAO = new CargaisonDAO();
            MarchandiseDAO marchandiseDAO = new MarchandiseDAO();

            int idCargaison = cargaisonDAO.inserer(cargaisonCourante);
            for (Marchandise m : cargaisonCourante.afficherMarchandises()) {
                marchandiseDAO.inserer(m, idCargaison);
            }

            JOptionPane.showMessageDialog(this, "Cargaison et marchandises enregistrées avec succès (id=" + idCargaison + ").");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de l'enregistrement en base : " + ex.getMessage(),
                    "Erreur base de données", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
