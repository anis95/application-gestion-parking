import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ParkCar extends JFrame {
	boolean[][] etatPark = new boolean[5][10];
	AjouterVoiturePanel addTab = new AjouterVoiturePanel();
	EtatParking etatParking = new EtatParking();
	SortirVoiture sortirVoiture = new SortirVoiture();
	SupperCar supperCar = new SupperCar();
	JTabbedPane mainTab = new JTabbedPane();
	JPanel removeTab = new JPanel();
	JPanel listTab = new JPanel();
	static ConnectionDB conx = new ConnectionDB();
	static Statement st = conx.connexion();
	Dimension dimensionFrame = new Dimension();
	SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 
	// static Task task = new Task();
	public Dimension getDimensionFrame() {
		return dimensionFrame;
	}

	public void setDimensionFrame(Dimension dimensionFrame) {
		this.dimensionFrame = dimensionFrame;
	}

	public ParkCar() {
		this.setTitle("Parking");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainTab.addTab("Ajouter une voiture", addTab);
		mainTab.addTab("Faire sorter une voiture", sortirVoiture);
		mainTab.addTab("L'etat de parking", etatParking);
		mainTab.addTab("Supprmier une voiture", supperCar);
		this.add(mainTab);
		this.pack();
		
		// l'espace de ajouter une voiture
		addTab.ok.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < addTab.tImmatricule.getText().length(); i++) {
					if (!((addTab.tImmatricule.getText().charAt(i) >= '0')
							&& (addTab.tImmatricule.getText().charAt(i) <= '9'))) {
						addTab.mImmatricule.setVisible(true);
						break;
					}
				}
				if (addTab.tImmatricule.getText().length() == 0) {
					addTab.msg.setText("Saisir l'immatricule");
				} else if (!(addTab.mImmatricule.isVisible())) {
					//verfication si le matricule enter existe ou non si existe il va t'afficher msg sinon il va d'ajouter
					int rowsCount = 0;
					
					
					try {
						ResultSet rs = st.executeQuery("select * from parking where immatricule='"
								+ addTab.tImmatricule.getText() + "' and heureSortie = heureEntree");
						while (rs.next()) {
							rowsCount++;
						}
						if (rowsCount > 0) {
							addTab.msg.setText("La voiture deja existe dans le parking");
						} else {
							
							
							st.executeUpdate("insert into parking (immatricule,rang,emplacement,heureEntree,heureSortie) values ('"
									+ addTab.tImmatricule.getText() + "'," + addTab.cbRang.getSelectedItem() + ","
									+ addTab.cbEmp.getSelectedItem() + ",'" + addTab.sdt.format(new Date()) + "','" + addTab.sdt.format(new Date()) + "');");
							st.executeUpdate("Update places set etat = '0' , immatricule = '"
									+ addTab.tImmatricule.getText() + "'where rang=" + addTab.cbRang.getSelectedItem()
									+ " and emplacement=" + addTab.cbEmp.getSelectedItem() + ";");
							st.executeUpdate("Update rangs set nbPlaceDispo = nbPlaceDispo-1 where num="
									+ addTab.cbRang.getSelectedItem() + ";");
							addTab.msg.setText("Voiture Ajouter");
							Alert alert = new Alert();

							alert.msg.setText("La voiture " + addTab.tImmatricule.getText()
									+ " a ajouté au parking, veuillez redémarrer l'application pour actualiser l'etat du parking");
							alert.actualise.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void mousePressed(MouseEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									dispose();
									alert.dispose();
									new ParkCar();
								}
							});
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		sortirVoiture.check.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}
//Button Verfier de faire sortir une voiture
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < sortirVoiture.tImmatricule.getText().length(); i++) {
					if (!((sortirVoiture.tImmatricule.getText().charAt(i) >= '0')
							&& (sortirVoiture.tImmatricule.getText().charAt(i) <= '9'))) {
						sortirVoiture.mImmatricule.setVisible(true);
						break;
					}
				}
				if (sortirVoiture.tImmatricule.getText().length() == 0) {
					sortirVoiture.msg.setText("Saisir l'immatricule");
				} else if (!(sortirVoiture.mImmatricule.isVisible())) {
					try {
						//sortir une voiture
						int rowsCount = 0;
						st = conx.connexion();
						ResultSet rs = st.executeQuery("select * from parking where immatricule='"
								+ sortirVoiture.tImmatricule.getText() + "' and heureSortie = heureEntree");
						while (rs.next()) {
							sortirVoiture.vRang.setText(String.valueOf(rs.getInt("rang")));
							sortirVoiture.vEmp.setText(String.valueOf(rs.getInt("emplacement")));
							sortirVoiture.vHeureEntrer.setText(sdt.format(rs.getDate("heureEntree")));
							rowsCount++;
						}
						// si le matricule entrer n'existe pas dans le base il vas t'afficher ce message
						if (rowsCount == 0) {
							sortirVoiture.msg.setText("verifier l'immatricule");
							sortirVoiture.vRang.setText("---");
							sortirVoiture.vEmp.setText("---");
							sortirVoiture.vHeureEntrer.setText("---");
						} else {
							sortirVoiture.msg.setText("");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		sortirVoiture.ok.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < sortirVoiture.tImmatricule.getText().length(); i++) {
					if (!((sortirVoiture.tImmatricule.getText().charAt(i) >= '0')
							&& (sortirVoiture.tImmatricule.getText().charAt(i) <= '9'))) {
						sortirVoiture.mImmatricule.setVisible(true);
						break;
					}
				}
				if (sortirVoiture.tImmatricule.getText().length() == 0) {
					sortirVoiture.msg.setText("Saisir l'immatricule");
				} else if ((sortirVoiture.msg.getText().length() == 0)
						&& ((sortirVoiture.vRang.getText().length() != 0))) {
					st = conx.connexion();
					try {
						st.executeUpdate("update parking set heureSortie='" + sdt.format(new Date())
								+ "' where immatricule='" + sortirVoiture.tImmatricule.getText() + "'");
						st.executeUpdate("update places set etat=1 , immatricule = NULL where rang="
								+ sortirVoiture.vRang.getText() + " and emplacement = " + sortirVoiture.vEmp.getText()
								+ "");
						st.executeUpdate("update rangs set nbPlaceDispo = nbPlaceDispo+1 where num = "
								+ sortirVoiture.vRang.getText() + "");
						Alert alert = new Alert();
						alert.msg.setText("La voiture " + sortirVoiture.tImmatricule.getText()
								+ " a sorti du parking, veuillez redémarrer l'application pour actualiser l'etat du parking");
						alert.actualise.addMouseListener(new MouseListener() {

							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								dispose();
								alert.dispose();
								new ParkCar();
							}
						});
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		supperCar.ok.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < supperCar.tImmatricule.getText().length(); i++) {
					if (!((supperCar.tImmatricule.getText().charAt(i) >= '0')
							&& (supperCar.tImmatricule.getText().charAt(i) <= '9'))) {
						supperCar.mImmatricule.setVisible(true);
						break;
					}
				}
				if (supperCar.tImmatricule.getText().length() == 0) {
					supperCar.msg.setText("Saisir l'immatricule");
				} else if ((supperCar.msg.getText().length() == 0) && ((supperCar.vRang.getText().length() != 0))) {
					st = conx.connexion();
					try {
						st.executeUpdate(
								"delete from parking where immatricule='" + supperCar.tImmatricule.getText() + "'");
						if (supperCar.vEtartInPark.getText().equals("Oui")) {
								st.executeUpdate("update places set etat=1 , immatricule = NULL where rang="
										+ supperCar.vRang.getText() + " and emplacement = " + supperCar.vEmp.getText());
								st.executeUpdate("update rangs set nbPlaceDispo = nbPlaceDispo+1 where num = " + supperCar.vRang.getText());
						}
						Alert alert = new Alert();
						alert.msg.setText("La voiture " + supperCar.tImmatricule.getText()
								+ " a ete effacé, veuillez redémarrer l'application pour actualiser l'etat du parking");
						alert.actualise.addMouseListener(new MouseListener() {

							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								dispose();
								alert.dispose();
								new ParkCar();
							}
						});
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		supperCar.check.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < supperCar.tImmatricule.getText().length(); i++) {
					if (!((supperCar.tImmatricule.getText().charAt(i) >= '0')
							&& (supperCar.tImmatricule.getText().charAt(i) <= '9'))) {
						supperCar.mImmatricule.setVisible(true);
						break;
					}
				}
				if (supperCar.tImmatricule.getText().length() == 0) {
					supperCar.msg.setText("Saisir l'immatricule");
				} else if ((supperCar.msg.getText().length() == 0) && ((supperCar.vRang.getText().length() != 0))) {
					try {
						int rowsCount = 0;
						st = conx.connexion();
						ResultSet rs = st.executeQuery("select * from parking where immatricule='"
								+ supperCar.tImmatricule.getText() + "'");
						while (rs.next()) {
							rowsCount++;
							supperCar.vEtartInPark.setText("Oui");
							supperCar.vRang.setText(rs.getString("rang"));
							supperCar.vEmp.setText(rs.getString("emplacement"));
							supperCar.vHeureEntrer.setText(sdt.format(rs.getDate("heureEntree")));
							if (!(sdt.format(rs.getDate("heureSortie")).equals(sdt.format(rs.getDate("heureEntree"))))) {
								supperCar.vEtartInPark.setText("Non");
								supperCar.vRang.setText("---");
								supperCar.vEmp.setText("---");
								supperCar.vHeureEntrer.setText("---");
							}
						}
						if (rowsCount == 0) {
							supperCar.vEtartInPark.setText("---");
							supperCar.msg.setText("verifier l'immatricule");
							supperCar.vRang.setText("---");
							supperCar.vEmp.setText("---");
							supperCar.vHeureEntrer.setText("---");
						} else {
							sortirVoiture.msg.setText("");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		Timer t = new Timer();
		TimerTask ts = new TimerTask() {

			@Override
			public void run() {

				// TODO Auto-generated method stub
				Boolean b = true;
				for (int i = 0; i < addTab.tImmatricule.getText().length(); i++) {

					if (!((addTab.tImmatricule.getText().charAt(i) >= '0')
							&& (addTab.tImmatricule.getText().charAt(i) <= '9'))) {
						b = false;
						break;
					}
				}
				if (addTab.tImmatricule.getText().length() == 0) {
					addTab.tImmatricule.setBackground(Color.cyan);
					addTab.mImmatricule.setVisible(false);
				} else if (b) {
					addTab.tImmatricule.setBackground(Color.green);
					addTab.mImmatricule.setVisible(false);
				} else {
					addTab.tImmatricule.setBackground(Color.red);
				}

				Boolean b1 = true;
				for (int i = 0; i < sortirVoiture.tImmatricule.getText().length(); i++) {

					if (!((sortirVoiture.tImmatricule.getText().charAt(i) >= '0')
							&& (sortirVoiture.tImmatricule.getText().charAt(i) <= '9'))) {
						b1 = false;
						break;
					}
				}
				if (sortirVoiture.tImmatricule.getText().length() == 0) {
					sortirVoiture.tImmatricule.setBackground(Color.cyan);
					sortirVoiture.mImmatricule.setVisible(false);
				} else if (b1) {
					sortirVoiture.tImmatricule.setBackground(Color.green);
					sortirVoiture.mImmatricule.setVisible(false);
				} else {
					sortirVoiture.tImmatricule.setBackground(Color.red);
				}

				if (mainTab.getSelectedComponent() == addTab) {
					setSize(new Dimension(440, 350));
				} else if (mainTab.getSelectedComponent() == etatParking) {
					if (addTab.msg.getText().equals("Voiture Ajouter")) {
						addTab.msg.setText("");
					}
					setSize(new Dimension(1050, 450));
				} else {
					if (addTab.msg.getText().equals("Voiture Ajouter")) {
						addTab.msg.setText("");
					}
					setSize(new Dimension(600, 350));
				}
				Boolean b2 = true;
				for (int i = 0; i < supperCar.tImmatricule.getText().length(); i++) {

					if (!((supperCar.tImmatricule.getText().charAt(i) >= '0')
							&& (supperCar.tImmatricule.getText().charAt(i) <= '9'))) {
						b1 = false;
						break;
					}
				}
				if (supperCar.tImmatricule.getText().length() == 0) {
					supperCar.tImmatricule.setBackground(Color.cyan);
					supperCar.mImmatricule.setVisible(false);
				} else if (b1) {
					supperCar.tImmatricule.setBackground(Color.green);
					supperCar.mImmatricule.setVisible(false);
				} else {
					supperCar.tImmatricule.setBackground(Color.red);
				}
			}
		};
		t.schedule(ts, 0, 1);
	}

	public static void main(String[] args) {
		ParkCar p = new ParkCar();

	}

}
