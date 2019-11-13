import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SupperCar extends JPanel {
	JLabel lImmatricule = new JLabel("Immatricule :");
	JTextField tImmatricule = new JTextField();
	JLabel mImmatricule = new JLabel();
	JLabel lRang = new JLabel("Rang :");
	JLabel vRang = new JLabel("---");
	JLabel lEmp = new JLabel("Emplacement :");
	JLabel vEmp = new JLabel("---");
	JLabel lHeureEntrer = new JLabel("Heure d'entrer");
	JLabel vHeureEntrer = new JLabel("---");
	JButton ok = new JButton("Supprimer");
	JButton check = new JButton("Verifier");
	JLabel etartInPark = new JLabel("En Parking");
	JLabel vEtartInPark = new JLabel("---");
	JLabel msg = new JLabel();

	JLabel empty1 = new JLabel();
	JLabel empty2 = new JLabel();
	JLabel empty3 = new JLabel();
	JLabel empty4 = new JLabel();
	JLabel empty5 = new JLabel();
	JLabel empty6 = new JLabel();
	JLabel empty7 = new JLabel();
	JLabel empty8 = new JLabel();

	public SupperCar() {
		// TODO Auto-generated constructor stub
		this.setLayout(new GridLayout(6, 4, 5, 5));
		mImmatricule.setIcon(new ImageIcon("src/fauxxx.png"));
		mImmatricule.setVisible(false);
		this.add(lImmatricule);
		this.add(tImmatricule);
		this.add(mImmatricule);
		this.add(check);
		this.add(etartInPark);
		this.add(vEtartInPark);
		this.add(empty7);
		this.add(empty8);
		this.add(lRang);
		this.add(vRang);
		this.add(empty1);
		this.add(empty2);
		this.add(lEmp);
		this.add(vEmp);
		this.add(empty3);
		this.add(empty4);
		this.add(lHeureEntrer);
		this.add(vHeureEntrer);
		this.add(empty5);
		this.add(empty6);
		this.add(ok);
		this.add(msg);
	}
}
