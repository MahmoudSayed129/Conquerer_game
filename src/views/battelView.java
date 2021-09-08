package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import buildings.ArcheryRange;
import buildings.Farm;
import engine.City;
import engine.Game;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class battelView implements ActionListener {
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	Army defending;
	Army attack;
	JFrame frame;
	JPanel graph1, graph2;
	JButton attackbutton;
	JLabel playern, archer, infantry, cavlary, archer1, infantry1, cavlary1, vs;
	JTextArea text;
	int width, height;
	JRadioButton rbutton;
	JPanel panel1;
	JPanel panel2;
	ArrayList<JRadioButton> alist;
	ButtonGroup groub1;
	ButtonGroup groub2;
	ArrayList<JRadioButton> blist;

	Unit u;
	Unit d;
	int index1, index2;
	boolean flag = false;
	JLabel army1, army2;
	JFrame backtomap;
	Game game;
	String cityname;
	CityView c;

	public battelView(Army a, Army b, JFrame x, CityView c, Game g, String cityname) {
		this.game = g;
		this.c = c;
		this.cityname = cityname;
		archer = new JLabel(new ImageIcon(this.getClass().getResource("/archer.jpg").getFile()));
		infantry = new JLabel(new ImageIcon(this.getClass().getResource("/infantry.jpg").getFile()));
		cavlary = new JLabel(new ImageIcon(this.getClass().getResource("/cavalary.jpg").getFile()));
		archer1 = new JLabel(new ImageIcon(this.getClass().getResource("/archer.jpg").getFile()));
		infantry1 = new JLabel(new ImageIcon(this.getClass().getResource("/infantry.jpg").getFile()));
		cavlary1 = new JLabel(new ImageIcon(this.getClass().getResource("/cavalary.jpg").getFile()));
		Font newFont = new Font("Arial", Font.BOLD, 15);
		Font newFont1 = new Font("Arial", Font.BOLD, 15);
		army1 = new JLabel(" Your Army");
		army1.setText("                         You");
		army1.setBounds(760, 0, 210, 30);
		army1.setFont(newFont1);
		army2 = new JLabel(" Enemy Units");
		army2.setText("                    Enemy");
		army2.setBounds(250, 0, 210, 30);
		army2.setFont(newFont1);
		defending = b;
		attack = a;
		backtomap = x;
		text = new JTextArea("");
		text.setEditable(false);
		text.setBounds(250, 360, 860, 380);
		text.setBackground(Color.red);
		text.setFont(newFont);
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame = new JFrame("Battel");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
		frame.add(text);
		frame.setLayout(null);
		playern = new JLabel();
		fillattacker();
		filldefending();
		attackbutton = new JButton("Attack");
		attackbutton.addActionListener(this);
		attackbutton.setBounds(630, 165, 106, 35);
		attackbutton.setFocusable(false);
		attackbutton.setFont(newFont);
		frame.add(attackbutton);
		JPanel panel8 = new JPanel();
		vs = new JLabel();
		vs.setText("VS");
		vs.setFont(new Font("MV Boli", Font.BOLD, 60));
		vs.setForeground(Color.red);
		panel8.setBounds(635, 30, 96, 96);
		vs.setFocusable(false);
		panel8.add(vs);

		frame.add(panel8);
		graph1 = new JPanel();
		graph1.setBackground(Color.black);
		graph1.setBounds(250, 30, 355, 330);
		frame.add(graph1);
		graph2 = new JPanel();
		graph2.setBackground(Color.black);
		graph2.setBounds(760, 30, 355, 330);
		frame.add(graph2);
		frame.add(army1);
		frame.add(army2);
		frame.setVisible(true);
//		frame.setUndecorated(true);
//		device.setFullScreenWindow(frame);

	}

	public void fillattacker() {
		alist = new ArrayList<JRadioButton>();
		panel1 = new JPanel();
		groub1 = new ButtonGroup();
		for (Unit u : attack.getUnits()) {
			rbutton = new JRadioButton();
			rbutton.addActionListener(this);
			if (u instanceof Archer) {
				rbutton.setText("Archer :   level" + " " + u.getLevel() + " " + "Current soldier  : "
						+ u.getCurrentSoldierCount());

				panel1.add(rbutton);
				groub1.add(rbutton);
				alist.add(rbutton);
			}
			if (u instanceof Infantry) {
				rbutton.setText("Infantry :   level" + " " + u.getLevel() + " " + "Current soldier  : "
						+ u.getCurrentSoldierCount());
				panel1.add(rbutton);
				groub1.add(rbutton);
				alist.add(rbutton);

			}
			if (u instanceof Cavalry) {
				rbutton.setText("Cavalary :   level" + " " + u.getLevel() + " " + "Current soldier  : "
						+ u.getCurrentSoldierCount());
				panel1.add(rbutton);
				groub1.add(rbutton);
				alist.add(rbutton);

			}

		}
		panel1.setBounds(1120, 0, 250, 768);
		panel1.setBackground(Color.black);
		panel1.setOpaque(true);
		frame.add(panel1);

	}

	public void filldefending() {
		blist = new ArrayList<JRadioButton>();
		panel2 = new JPanel();
		groub2 = new ButtonGroup();
		for (Unit u : defending.getUnits()) {
			rbutton = new JRadioButton();
			rbutton.addActionListener(this);
			rbutton.setSize(50, 15);
			if (u instanceof Archer) {
				rbutton.setText("Archer :   level" + " " + u.getLevel() + " " + "Current soldier  : "
						+ u.getCurrentSoldierCount());

				panel2.add(rbutton);
				groub2.add(rbutton);
				blist.add(rbutton);
			}
			if (u instanceof Infantry) {
				rbutton.setText("Infantry :   level" + " " + u.getLevel() + " " + "Current soldier  : "
						+ u.getCurrentSoldierCount());
				panel2.add(rbutton);
				groub2.add(rbutton);
				blist.add(rbutton);

			}
			if (u instanceof Cavalry) {
				rbutton.setText("Cavalary :   level" + " " + u.getLevel() + " " + "Current soldier  : "
						+ u.getCurrentSoldierCount());
				panel2.add(rbutton);
				groub2.add(rbutton);
				blist.add(rbutton);

			}

		}
		panel2.setBounds(0, 0, 250, 768);
		panel2.setBackground(Color.black);
		panel2.setOpaque(true);
		frame.add(panel2);

	}

	public void actionPerformed(ActionEvent e) {
		////////////////////
		text.setText("");

		if (e.getSource() != attackbutton) {
			for (int i = 0; i < alist.size(); i++) {

				if (alist.get(i).isSelected()) {
					u = attack.getUnits().get(i);
					if (u instanceof Archer) {

						text.append("\n");
						text.append("My unit\n");
						text.append(" AttackArmy : " + "Archer :   level" + " " + u.getLevel() + " "
								+ "Current soldier  : " + u.getCurrentSoldierCount());

						graph2.removeAll();
						graph2.revalidate();
						graph2.repaint();
						graph2.add(archer1);
					}
					if (u instanceof Infantry) {

						text.append("\n");
						text.append("My unit\n");
						text.append(" AttackArmy : " + "Infantry :   level" + " " + u.getLevel() + " "
								+ "Current soldier  : " + u.getCurrentSoldierCount());

						graph2.removeAll();
						graph2.revalidate();
						graph2.repaint();
						graph2.add(infantry1);
					}
					if (u instanceof Cavalry) {

						text.append("\n");
						text.append("My unit\n");
						text.append(" AttackArmy : " + "Cavalary :   level" + " " + u.getLevel() + " "
								+ "Current soldier  : " + u.getCurrentSoldierCount());

						graph2.removeAll();
						graph2.revalidate();
						graph2.repaint();
						graph2.add(cavlary1);

					}
					index1 = i;

				}
			}
			for (int i = 0; i < blist.size(); i++) {

				if (blist.get(i).isSelected()) {
					d = defending.getUnits().get(i);
					text.removeAll();
					if (d instanceof Archer) {

						text.append("\n");
						text.append("Enemy unit\n");
						text.append(" DefendArmy : " + "Archer :   level" + " " + d.getLevel() + " "
								+ "Current soldier  : " + d.getCurrentSoldierCount());
						graph1.removeAll();
						graph1.revalidate();
						graph1.repaint();
						graph1.add(archer);
					}
					if (d instanceof Infantry) {

						text.append("\n");
						text.append("Enemy unit\n");
						text.append(" DefendArmy : " + "Infantry :   level" + " " + d.getLevel() + " "
								+ "Current soldier  : " + d.getCurrentSoldierCount());
						graph1.removeAll();
						graph1.revalidate();
						graph1.repaint();
						graph1.add(infantry);
					}
					if (d instanceof Cavalry) {

						text.append("\n");
						text.append("Enemy unit\n");
						text.append(" DefendArmy : " + "Cavalary :   level" + " " + d.getLevel() + " "
								+ "Current soldier  : " + d.getCurrentSoldierCount());
						graph1.removeAll();
						graph1.revalidate();
						graph1.repaint();
						graph1.add(cavlary);

					}
					index2 = i;
				}
			}
		}

		if (e.getSource() == attackbutton) {
			if (d.getCurrentSoldierCount() == 0) {
				text.append("Unit is totaly dead choose another one");
			}
			if (d.getCurrentSoldierCount() != 0) {
				try {
					u.attack(d);
					if (d.getMaxSoldierCount() != 0) {

						if (d instanceof Archer) {
							text.append("\n");
							text.append("After attack\n");
							text.append(" EnemyUnit : " + "Archer :   level" + " " + d.getLevel() + " "
									+ "Current soldier  : " + d.getCurrentSoldierCount());
						}
						if (d instanceof Infantry) {
							text.append("\n");
							text.append("After attack\n");
							text.append(" EnemyUnit : " + "Infantry :   level" + " " + d.getLevel() + " "
									+ "Current soldier  : " + d.getCurrentSoldierCount());
						}
						if (d instanceof Cavalry) {
							text.append("\n");
							text.append("After attack\n");
							text.append(" EnemyUnit : " + "Cavalary :   level" + " " + d.getLevel() + " "
									+ "Current soldier  : " + d.getCurrentSoldierCount());
						}
					}
					if (d.getCurrentSoldierCount() == 0) {
						blist.get(index2).setEnabled(false);
						graph1.removeAll();
						graph1.revalidate();
						graph1.repaint();
						blist.remove(index2);
					}
					if (defending.getUnits().size() == 0) {
						frame.setVisible(false);
						backtomap.setVisible(true);
						JOptionPane.showMessageDialog(null, "Congratulation You Win This Battle .",
								"Information Message", JOptionPane.INFORMATION_MESSAGE);
						game.occupy(attack, cityname);
						if (game.isGameOver()) {
							backtomap.dispose();
							new EndGame(true);
						}
						else {
							c.map.doClick();
						}
					}

				} catch (FriendlyFireException e1) {
					JOptionPane.showMessageDialog(frame, "This is an error message", "Cannot attack a friendly unit",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
			if (u.getCurrentSoldierCount() != 0) {
				try {
					d.attack(u);
					if (u.getMaxSoldierCount() != 0) {

						if (u instanceof Archer) {
							text.append("\n");
							text.append("After attack\n");
							text.append(" Your Unit : " + "Archer :   level" + " " + u.getLevel() + " "
									+ "Current soldier  : " + u.getCurrentSoldierCount());
						}
						if (u instanceof Infantry) {
							text.append("\n");
							text.append("After attack\n");
							text.append(" Your Unit : " + "Infantry :   level" + " " + u.getLevel() + " "
									+ "Current soldier  : " + u.getCurrentSoldierCount());
						}
						if (u instanceof Cavalry) {
							text.append("\n");
							text.append("After attack\n");
							text.append(" Your Unit : " + "Cavalary :   level" + " " + u.getLevel() + " "
									+ "Current soldier  : " + u.getCurrentSoldierCount());
						}
					}
					if (u.getCurrentSoldierCount() == 0) {
						alist.get(index1).setEnabled(false);
						graph2.removeAll();
						graph2.revalidate();
						graph2.repaint();
						alist.remove(index1);
					}
					if (attack.getUnits().size() == 0) {
						frame.setVisible(false);
						backtomap.setVisible(true);
						JOptionPane.showMessageDialog(null, " You Lose This Battle .", "Information Message",
								JOptionPane.INFORMATION_MESSAGE);
						c.map.doClick();
					}

				} catch (FriendlyFireException e1) {
					JOptionPane.showMessageDialog(frame, "This is an error message", "Cannot attack a friendly unit",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}

		}

	}
}