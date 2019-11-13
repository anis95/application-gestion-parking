import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Alert extends JFrame {
	JLabel msg = new JLabel("aaa");
	JPanel mainPanel = new JPanel();
	JPanel northPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JPanel southPanel1 = new JPanel();
	JPanel southPanel2 = new JPanel();
	JPanel southPanel3 = new JPanel();
	JButton actualise = new JButton("Ok, Actualiser");

	public Alert() {
		this.setVisible(true);
		this.setTitle("Alert");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainPanel.setLayout(new GridLayout(2, 1));
		northPanel.add(msg);
		southPanel.setLayout(new GridLayout(1, 3));
		southPanel2.add(actualise);
		southPanel.add(southPanel1);
		southPanel.add(southPanel2);
		southPanel.add(southPanel3);
		mainPanel.add(northPanel);
		mainPanel.add(southPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 100);
		this.add(mainPanel);
	}
}
