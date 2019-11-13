import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EtatParking extends JPanel {
	ConnectionDB cnx = new ConnectionDB();
	Statement st = cnx.connexion();
	JLabel l;
	static String[][] statuts = new String[5][10];
	JPanel entete = new JPanel();
	JPanel rangs = new JPanel();
	JPanel emps = new JPanel();
	JPanel table = new JPanel();
	JPanel mainPanel = new JPanel();

	public EtatParking() {

		this.setVisible(true);
		entete.setLayout(new BorderLayout());
		JLabel r = new JLabel("Rang");
		JLabel em = new JLabel("Emplacement");
		entete.add(r, BorderLayout.SOUTH);
		entete.add(em, BorderLayout.EAST);
		entete.setBackground(Color.lightGray);
		try {

			ResultSet rs = st.executeQuery("SELECT * FROM `places`");
			while (rs.next()) {
				if (rs.getString("etat").equals("1")) {
					statuts[rs.getInt(1) - 1][rs.getInt(2) - 1] = "Disponible";
				} else {
					statuts[rs.getInt(1) - 1][rs.getInt(2) - 1] = rs.getString(4);
				}

			}
			fill(mainPanel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void empty(JPanel p) {
		p.removeAll();
	}

	public void fill(JPanel p) {
		mainPanel.setLayout(new GridLayout(6, 1, 1, 30));
		JLabel label;
		JPanel ligne;
		for (int i = 0; i < 6; i++) {
			ligne = new JPanel();
			ligne.setBackground(Color.lightGray);
			ligne.setLayout(new GridLayout(1, 11, 10, 5));
			for (int j = 0; j < 11; j++) {
				if (i == 0) {
					if (j == 0) {
						ligne.add(entete);
					} else {
						label = new JLabel(String.valueOf(j));
						label.setForeground(Color.BLACK);
						ligne.add(label);
					}
				} else {
					if (j == 0) {
						label = new JLabel(String.valueOf(i));
						label.setForeground(Color.BLACK);
						ligne.add(label);
					} else {
						label = new JLabel(statuts[i - 1][j - 1]);
						if (statuts[i - 1][j - 1].equals("Disponible")) {
							label.setForeground(Color.BLUE);
						} else {

							label.setForeground(Color.red);
						}
						ligne.add(label);
					}
				}
			}
			p.add(ligne);
			this.add(p);
		}
	}
}
