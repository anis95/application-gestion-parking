import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AjouterVoiturePanel extends JPanel {
	JLabel lImmatricule = new JLabel("Immatricule");
	JTextField tImmatricule = new JTextField();
	JLabel mImmatricule = new JLabel();

	JLabel lRang = new JLabel("Rang");
	JComboBox<String> cbRang = new JComboBox<>();
	JLabel lEmp = new JLabel("Emplacement");
	JComboBox<String> cbEmp = new JComboBox<>();
	JButton ok = new JButton("Valider");
	JLabel msg = new JLabel();

	JLabel empty1 = new JLabel();
	JLabel empty2 = new JLabel();
	JLabel empty3 = new JLabel();
	JLabel empty4 = new JLabel();

	ConnectionDB conx = new ConnectionDB();
	Statement st;

	SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public AjouterVoiturePanel() {
		mImmatricule.setIcon(new ImageIcon("src/fauxxx.png"));
		mImmatricule.setVisible(false);
		try {
			st = conx.connexion();
			ResultSet rs = st.executeQuery("select num from rangs where nbPlaceDispo <> 0");
			while (rs.next()) {
				cbRang.addItem(rs.getString("num"));
			}
			rs = st.executeQuery(
					"select emplacement from places where rang=" + cbRang.getSelectedItem() + " and etat=1");
			while (rs.next()) {
				cbEmp.addItem(rs.getString("emplacement"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cbRang.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				ResultSet rs;
				cbEmp.removeAllItems();
				try {
					rs = st.executeQuery(
							"select emplacement from places where rang=" + cbRang.getSelectedItem() + " and etat=1");
					while (rs.next()) {
						cbEmp.addItem(rs.getString("emplacement"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		this.setLayout(new GridLayout(4, 3, 5, 5));
		this.add(lImmatricule);
		this.add(tImmatricule);
		this.add(mImmatricule);
		this.add(lRang);
		this.add(cbRang);
		this.add(empty1);
		this.add(lEmp);
		this.add(cbEmp);
		this.add(empty2);
		this.add(ok);
		this.add(msg);
	}
}
