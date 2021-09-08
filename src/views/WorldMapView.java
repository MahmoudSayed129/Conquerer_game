package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.KeyboardFocusManagerPeer;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import engine.City;
import engine.Game;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.TargetNotReachedException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class WorldMapView implements ActionListener{
	// static GraphicsDevice device =
	// GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	ArmyView av;
	JFrame worldmap, enemycity;
	JLabel l1, l2, l3, l4, l5, l6, turncount, gold, playern, food;
	JTextArea playername;
	JButton startbutton, rome, sparta, cairo, idleArmy, MarchingArmy, BeseigingArmies, relocateUnit, ra, endturn;
	JButton attackc, attackr, attacks, targetc, targetr, targets, besiegr, besiegs, besiegc, autoc, autor, autos;
	Game g;
	JPanel Information;
	boolean cair, spar, rom, ar, ac, as;
	JComboBox<JButton> cities;
	int width, height;
	int cf = 0;
	String[] yourUnit, yourArmies;
	JComboBox<JButton> units, armies;
	CityView cairoView, romeView, spartaView;
	battelView battelView;
	String[] unites;
	JLabel armJLabel, unJLabel;
	Image icon = new ImageIcon(this.getClass().getResource("/Icon.jpg")).getImage();

	public WorldMapView(Game g) {
		unites = new String[100];
		this.g = g;
		Image icon2 = new ImageIcon(this.getClass().getResource("/Map.jpg")).getImage();
		ImageIcon inIcon = new ImageIcon(icon2);
		JLabel label = new JLabel("", inIcon, JLabel.CENTER);
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		worldmap = new JFrame();
		worldmap.setExtendedState(JFrame.MAXIMIZED_BOTH);
		worldmap.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		worldmap.pack();
		worldmap.setLayout(null);
		cairo = new JButton("Cairo");
		cairo.addActionListener(this);
		cairo.setIcon(new ImageIcon(this.getClass().getResource("/Cairo.jpg").getFile()));
		cairo.setBounds(0, 6 * height / 8, width / 8, height / 8);
		rome = new JButton();
		rome.addActionListener(this);
		rome.setIcon(new ImageIcon(this.getClass().getResource("/Rome.jpg").getFile()));
		rome.setBounds(7 * width / 8, 6 * height / 8, width / 8, height / 8);
		sparta = new JButton("Sparta");
		sparta.addActionListener(this);
		sparta.setIcon(new ImageIcon(this.getClass().getResource("/Sparta.jpg").getFile()));
		sparta.setBounds(0, 2 * height / 32, width / 8, height / 8);
		attackc = new JButton("attack");
		attacks = new JButton("attack");
		attackr = new JButton("attack");
		targetc = new JButton("target");
		targets = new JButton("target");
		targetr = new JButton("target");
		autoc = new JButton("Auto Resolve");
		autor = new JButton("Auto Resolve");
		autos = new JButton("Auto Resolve");
		besiegr = new JButton("Besieg");
		besiegc = new JButton("Besieg");
		besiegs = new JButton("Besieg");
		attackc.addActionListener(this);
		attacks.addActionListener(this);
		attackr.addActionListener(this);
		targetc.addActionListener(this);
		targets.addActionListener(this);
		targetr.addActionListener(this);
		autoc.addActionListener(this);
		autos.addActionListener(this);
		autor.addActionListener(this);
		besiegc.addActionListener(this);
		besiegs.addActionListener(this);
		besiegr.addActionListener(this);
		attackc.setBounds(0, 11 * height / 16, width / 16, height / 16);
		worldmap.add(attackc);
		targetc.setBounds(width / 16, 11 * height / 16, width / 16, height / 16);
		worldmap.add(targetc);
		autoc.setBounds(0, 14 * height / 16, width / 16, height / 16);
		worldmap.add(autoc);
		besiegc.setBounds(width / 16, 14 * height / 16, width / 16, height / 16);
		worldmap.add(besiegc);
		///////////////////////////////////////////////////////////////////
		attacks.setBounds(0, 3 * height / 16, width / 16, height / 16);
		worldmap.add(attacks);
		targets.setBounds(width / 16, 3 * height / 16, width / 16, height / 16);
		worldmap.add(targets);
		autos.setBounds(width / 8, height / 16, width / 16, height / 16);
		worldmap.add(autos);
		besiegs.setBounds(width / 8, 2 * height / 16, width / 16, height / 16);
		worldmap.add(besiegs);
		attackr.setBounds(15 * width / 16, 11 * height / 16, width / 16, height / 16);
		worldmap.add(attackr);
		targetr.setBounds(14 * width / 16, 11 * height / 16, width / 16, height / 16);
		worldmap.add(targetr);
		autor.setBounds(15 * width / 16, 14 * height / 16, width / 16, height / 16);
		worldmap.add(autor);
		besiegr.setBounds(14 * width / 16, 14 * height / 16, width / 16, height / 16);
		worldmap.add(besiegr);
		ra = new JButton("Show My Armies");
		ra.setBounds(12 * width / 16, 0, 2 * width / 8, height / 32);
		ra.addActionListener(this);
		worldmap.add(ra);
		worldmap.add(sparta);
		worldmap.add(cairo);
		worldmap.add(rome);
		worldmap.repaint();
		worldmap.validate();

		enemycity = new JFrame();
		enemycity.setSize(width / 4, height / 4);
		enemycity.setLayout(null);
		Information = new JPanel(new GridLayout(1, 0));
		playern = new JLabel("Welcome : " + g.getPlayer().getName());
		turncount = new JLabel("Turns : " + g.getCurrentTurnCount() + "");
		gold = new JLabel("Gold : " + g.getPlayer().getTreasury() + "");
		food = new JLabel("Food : " + g.getPlayer().getFood() + "");
		Information.add(playern);
		Information.add(gold);
		Information.add(food);
		Information.add(turncount);
		Information.setBounds(0, 0, 5*width/8, height / 32);
		worldmap.add(Information);
		label.setPreferredSize(new Dimension(1000, 1000));
		label.setBounds(0, 2 * height / 32, 1366, 768);
		worldmap.add(label);
//		worldmap.setUndecorated(true);
//		device.setFullScreenWindow(worldmap);
//		device.setFullScreenWindow(worldmap);
		cairoView = new CityView(g, this, "Cairo");
		romeView = new CityView(g, this, "Rome");
		spartaView = new CityView(g, this, "Sparta");
		relocateUnit = new JButton("Relocate");
		relocateUnit.addActionListener(this);
		yourUnit = new String[100];
		yourArmies = new String[100];
		units = new JComboBox(yourUnit);
		armies = new JComboBox(yourArmies);
		relocateUnit.setBounds(14 * width / 16, height / 32, 2 * width / 16, height / 32);
		armies.setBounds(5 * width / 16, height / 32, 2 * width / 16, height / 32);
		units.setBounds(10 * width / 16, height / 32, width / 4, height / 32);
		armJLabel = new JLabel("Choose any army to relocate,target or attack any city you want : ");
		unJLabel = new JLabel("Choose any unit to relocate to another army : ");
		armJLabel.setBounds(0, height / 32, 5 * width / 16, height / 32);
		unJLabel.setBounds(7 * width / 16, height / 32, 3 * width / 16, height / 32);
		endturn = new JButton("End Turn");
		endturn.setBounds(10 * width / 16, 0, width / 8, height / 32);
		endturn.addActionListener(this);
		worldmap.add(endturn);
		worldmap.add(armJLabel);
		worldmap.add(unJLabel);
		worldmap.add(units);
		worldmap.add(armies);
		worldmap.setResizable(false);
		worldmap.add(relocateUnit);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == endturn) {
			g.endTurn();
			Information.removeAll();
			playern = new JLabel("Welcome : " + g.getPlayer().getName());
			turncount = new JLabel("Turns : " + g.getCurrentTurnCount() + "");
			gold = new JLabel("Gold : " + g.getPlayer().getTreasury() + "");
			food = new JLabel("Food : " + g.getPlayer().getFood() + "");
			Information.add(playern);
			Information.add(gold);
			Information.add(food);
			Information.add(turncount);
			worldmap.revalidate();
			worldmap.repaint();
			for (City c : g.getAvailableCities()) {
				if (c.getTurnsUnderSiege() == 3) {
					String[] options = new String[] { "Auto Resolve", "Attack" };
					int response = JOptionPane.showOptionDialog(null,
							"You Have To Attack OR Autoresilve " + "" + c.getName(), " Under Besiege For Three turns",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

					Army army = null;
					for (Army a : g.getPlayer().getControlledArmies()) {
						if (a.getCurrentLocation().equalsIgnoreCase(c.getName())
								&& a.getCurrentStatus() == Status.BESIEGING)
							army = a;
					}
					if (response == 0) {

						worldmap.dispose();
						try {
							g.autoResolve(army, c.getDefendingArmy());
							if (army.getUnits().size() > 0) {
								JOptionPane.showMessageDialog(null, "You Won The Battle", "Battle Review",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "You Lost The Battle", "Battle Review",
										JOptionPane.INFORMATION_MESSAGE);
								c.setTurnsUnderSiege(-1);
							}
//							worldmap.dispose();
//							worldmap.setVisible(true);
						} catch (FriendlyFireException e1) {
						}
					}
					if (response == 1) {
						worldmap.dispose();
						new battelView(army, c.getDefendingArmy(), worldmap, cairoView, g, c.getName());
					}
				}
			}
			if (g.isGameOver()) {
				worldmap.dispose();
				new EndGame(false);
			}

		}
		if (e.getSource() == cairo) {
			for (City city : g.getPlayer().getControlledCities()) {
				if (city.getName().equalsIgnoreCase("cairo"))
					cair = true;
			}
			if (cair) {
				worldmap.dispose();
				this.cairoView.Information.removeAll();
				this.cairoView.Information.add(playern);
				this.cairoView.Information.add(gold);
				this.cairoView.Information.add(food);
				this.cairoView.Information.add(turncount);
				this.cairoView.cityFrame.revalidate();
				this.cairoView.cityFrame.repaint();
				this.cairoView.cityFrame.setVisible(true);
			}
		}
		if (e.getSource() == rome) {
			for (City city : g.getPlayer().getControlledCities()) {
				if (city.getName().equalsIgnoreCase("rome"))
					rom = true;
			}
			if (rom) {
				worldmap.dispose();
				this.romeView.Information.removeAll();
				this.romeView.Information.add(playern);
				this.romeView.Information.add(gold);
				this.romeView.Information.add(food);
				this.romeView.Information.add(turncount);
				this.romeView.cityFrame.revalidate();
				this.romeView.cityFrame.repaint();
				this.romeView.cityFrame.setVisible(true);
			}
		}
		if (e.getSource() == sparta) {
			for (City city : g.getPlayer().getControlledCities()) {
				if (city.getName().equalsIgnoreCase("sparta"))
					spar = true;
			}
			if (spar) {
				worldmap.dispose();
				this.spartaView.Information.removeAll();
				this.spartaView.Information.add(playern);
				this.spartaView.Information.add(gold);
				this.spartaView.Information.add(food);
				this.spartaView.Information.add(turncount);
				this.spartaView.cityFrame.revalidate();
				this.spartaView.cityFrame.repaint();
				this.spartaView.cityFrame.setVisible(true);
			}
		}
		if (e.getSource() == relocateUnit && g.getPlayer().getControlledArmies().size() > 0) {
			// System.out.println("relocate");
			int y = 0;
			int x = 0;
			int counter = 0;
			String s = unites[units.getSelectedIndex()];
			if (s.charAt(s.length() - 1) == 'o' || s.charAt(s.length() - 1) == 'e' || s.charAt(s.length() - 1) == 'a') {
				for (City city : g.getPlayer().getControlledCities()) {
					if (city.getName().charAt(city.getName().length() - 1) == s.charAt(s.length() - 1)) {
						int count = 0;
						for (int i = 0; i < unites.length; i++) {
							if (unites[i] == s)
								break;
							if (unites[i].charAt(unites[i].length() - 1) == s.charAt(s.length() - 1))
								count++;
						}
						if (g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()).getCurrentLocation()
								.equalsIgnoreCase(city.getName())) {
							try {
								g.getPlayer().getControlledArmies().get(armies.getSelectedIndex())
										.relocateUnit(city.getDefendingArmy().getUnits().get(count));
							} catch (MaxCapacityException e1) {
								JOptionPane.showMessageDialog(worldmap, "this Army is Full", "Error",
										JOptionPane.WARNING_MESSAGE);
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(worldmap, "The Army and the Unit are in diffrent Locations",
									"Error", JOptionPane.WARNING_MESSAGE);
						}

					}
				}

			} else {
				String Index = "";
				for (int i = s.length() - 1; i >= 0; i--) {
					if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
						Index = s.charAt(i) + Index + "";
					} else
						break;
				}
				int count = 0;
				for (int i = 0; i < unites.length; i++) {
					if (s.equals(unites[i]))
						break;
					if (unites[i].substring(unites[i].length() - Index.length()).equals(Index))
						count++;
				}
				if (g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()).getCurrentLocation()
						.equalsIgnoreCase(g.getPlayer().getControlledArmies().get(Integer.parseInt(Index) - 1)
								.getCurrentLocation())) {
					try {
						g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()).relocateUnit(g.getPlayer()
								.getControlledArmies().get(Integer.parseInt(Index) - 1).getUnits().get(count));
					} catch (MaxCapacityException e1) {
						JOptionPane.showMessageDialog(worldmap, "this Army is Full", "Error",
								JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(worldmap, "The Army and the Unit are in diffrent Locations",
							"Error", JOptionPane.WARNING_MESSAGE);
				}

			}
			unites = new String[100];
			int i = 0;
			for (City city : g.getPlayer().getControlledCities()) {

				for (Unit unit : city.getDefendingArmy().getUnits()) {
					if (unit instanceof Archer) {
						unites[i] = "Archer : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
								+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " "
								+ city.getName();
					}
					if (unit instanceof Cavalry) {
						unites[i] = "Cavalry : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
								+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " "
								+ city.getName();

					}
					if (unit instanceof Infantry) {
						unites[i] = "Infantry : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
								+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " "
								+ city.getName();
					}
					i++;

				}
			}
			// this.worldmap.setUndecorated(true);
			int arm = 1;
			for (Army a : g.getPlayer().getControlledArmies()) {
				for (Unit unit : a.getUnits()) {
					if (unit instanceof Archer) {
						unites[i] = "Archer : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
								+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " " + "Army" + arm;
					}
					if (unit instanceof Cavalry) {
						unites[i] = "Cavalry : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
								+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " " + "Army" + arm;
					}
					if (unit instanceof Infantry) {
						unites[i] = "Infantry : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
								+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " " + "Army" + arm;
					}
					i++;
					// System.out.println(f);
				}
				arm++;
			}

			ComboBoxModel<JButton> modle1 = new DefaultComboBoxModel(unites);
			this.units.setModel(modle1);
		}

		if (e.getSource() == ra) {

			av = new ArmyView(g, worldmap);
			worldmap.setVisible(false);

		}
		if (e.getSource() == targetr) {
			if (g.getPlayer().getControlledArmies().size() > 0) {
				for (City c : g.getAvailableCities()) {
					if (c.getName().equalsIgnoreCase("rome")) {
						g.targetCity(g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()), "rome");
					}
				}

			} else {
				JOptionPane.showMessageDialog(worldmap, "You Must Have At Least One Army", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		if (e.getSource() == targetc) {

			if (g.getPlayer().getControlledArmies().size() > 0) {
				for (City c : g.getAvailableCities()) {
					if (c.getName().equalsIgnoreCase("cairo")) {
						g.targetCity(g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()), "cairo");
					}
				}

			} else {
				JOptionPane.showMessageDialog(worldmap, "You Must Have At Least One Army", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		if (e.getSource() == targets) {
			if (g.getPlayer().getControlledArmies().size() > 0) {
				for (City c : g.getAvailableCities()) {
					if (c.getName().equalsIgnoreCase("sparta")) {
						g.targetCity(g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()), "sparta");
					}
				}

			} else {
				JOptionPane.showMessageDialog(worldmap, "You Must Have At Least One Army", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		if (e.getSource() == attackr) {
			boolean a = true;
			for (City c : g.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase("rome"))
					a = false;
			}
			if (a && g.getPlayer().getControlledArmies().size() != 0 && (g.getPlayer().getControlledArmies()
					.get(armies.getSelectedIndex()).getCurrentLocation().equalsIgnoreCase("rome"))) {
				worldmap.dispose();
				if (g.getPlayer().getControlledArmies().size() > 0) {
					for (City c : g.getAvailableCities()) {
						if (c.getName().equalsIgnoreCase("rome")) {
							battelView = new battelView(
									g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()),
									c.getDefendingArmy(), worldmap, cairoView, g, "Rome");
						}
					}

				}
			} else if (!a) {
				JOptionPane.showMessageDialog(worldmap, "Rome Is One Of Your Cities", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (g.getPlayer().getControlledArmies().size() == 0) {
					JOptionPane.showMessageDialog(worldmap, "Your Army Is Empty", "This is an error message",
							JOptionPane.WARNING_MESSAGE);
				} else
					JOptionPane.showMessageDialog(worldmap, "You should reach Rome First", "This is an error message",
							JOptionPane.WARNING_MESSAGE);

			}
		}
		if (e.getSource() == attackc) {
			boolean a = true;
			for (City c : g.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase("cairo"))
					a = false;
			}
			if (a && g.getPlayer().getControlledArmies().size() != 0 && (g.getPlayer().getControlledArmies()
					.get(armies.getSelectedIndex()).getCurrentLocation().equalsIgnoreCase("cairo"))) {
				worldmap.dispose();


				for (City c : g.getAvailableCities()) {
					if (c.getName().equalsIgnoreCase("cairo")) {
						battelView = new battelView(g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()),
								c.getDefendingArmy(), worldmap, cairoView, g, "Cairo");
					}
				}

			} else if (!a) {
				JOptionPane.showMessageDialog(worldmap, "Cairo Is One Of Your Cities", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			} else if (g.getPlayer().getControlledArmies().size() == 0) {
				JOptionPane.showMessageDialog(worldmap, "Your Army Is Empty", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(worldmap, "You should reach Cairo First", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		if (e.getSource() == attacks) {
			boolean a = true;
			for (City c : g.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase("sparta"))
					a = false;
			}
			if (a && g.getPlayer().getControlledArmies().size() != 0 && (g.getPlayer().getControlledArmies()
					.get(armies.getSelectedIndex()).getCurrentLocation().equalsIgnoreCase("sparta"))) {
				if (g.getPlayer().getControlledArmies().size() > 0) {
					worldmap.dispose();
					for (City c : g.getAvailableCities()) {
						if (c.getName().equalsIgnoreCase("sparta")) {
							battelView = new battelView(
									g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()),
									c.getDefendingArmy(), worldmap, cairoView, g, "Sparta");
						}
					}

				}
			} else if (!a) {
				JOptionPane.showMessageDialog(worldmap, "Sparta Is One Of Your Cities", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (g.getPlayer().getControlledArmies().size() == 0) {
					JOptionPane.showMessageDialog(worldmap, "Your Army Is Empty", "This is an error message",
							JOptionPane.WARNING_MESSAGE);
				} else
					JOptionPane.showMessageDialog(worldmap, "You should reach Sparta First", "This is an error message",
							JOptionPane.WARNING_MESSAGE);

			}

		}
		if (e.getSource() == autor) {
			boolean a = true;
			for (City c : g.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase("rome"))
					a = false;
			}
			if (a && g.getPlayer().getControlledArmies().size() != 0 && (g.getPlayer().getControlledArmies()
					.get(armies.getSelectedIndex()).getCurrentLocation().equalsIgnoreCase("rome"))) {
				if (g.getPlayer().getControlledArmies().size() > 0) {
					for (City c : g.getAvailableCities()) {
						if (c.getName().equalsIgnoreCase("rome")) {
							try {
								int count = g.getPlayer().getControlledArmies().size();
								g.autoResolve(g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()),
										c.getDefendingArmy());
								if (count > g.getPlayer().getControlledArmies().size()) {
									JOptionPane.showMessageDialog(worldmap, "You Win The Figth", "Information Message",
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(worldmap, "You Lose The Figth", "Information Message",
											JOptionPane.INFORMATION_MESSAGE);
								}
							} catch (FriendlyFireException e1) {
								JOptionPane.showMessageDialog(worldmap, "You Attack Your City",
										"This is an error message", JOptionPane.WARNING_MESSAGE);
								e1.printStackTrace();
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(worldmap, "You Must Have At Least One Army",
							"This is an error message", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				if (!a)
					JOptionPane.showMessageDialog(worldmap, "Rome Is One Of Your Cities", "This is an error message",
							JOptionPane.WARNING_MESSAGE);
				else {
					if (g.getPlayer().getControlledArmies().size() == 0) {
						JOptionPane.showMessageDialog(worldmap, "Your Army Is Empty", "This is an error message",
								JOptionPane.WARNING_MESSAGE);
					} else
						JOptionPane.showMessageDialog(worldmap, "You should reach Rome First",
								"This is an error message", JOptionPane.WARNING_MESSAGE);

				}
			}
			if (g.isGameOver()) {
				worldmap.dispose();
				new EndGame(true);
			}
			cairoView.map.doClick();

		}
		if (e.getSource() == autoc) {
			boolean a = true;
			for (City c : g.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase("cairo"))
					a = false;
			}
			if (a && g.getPlayer().getControlledArmies().size() != 0 && (g.getPlayer().getControlledArmies()
					.get(armies.getSelectedIndex()).getCurrentLocation().equalsIgnoreCase("cairo"))) {
				if (g.getPlayer().getControlledArmies().size() > 0) {
					for (City c : g.getAvailableCities()) {
						if (c.getName().equalsIgnoreCase("cairo")) {
							try {
								int count = g.getPlayer().getControlledArmies().size();
								g.autoResolve(g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()),
										c.getDefendingArmy());
								if (count > g.getPlayer().getControlledArmies().size()) {
									JOptionPane.showMessageDialog(worldmap, "You Win The Figth", "Information Message",
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(worldmap, "You Lose The Figth", "Information Message",
											JOptionPane.INFORMATION_MESSAGE);
								}
							} catch (FriendlyFireException e1) {
								JOptionPane.showMessageDialog(worldmap, "You Attack Your City",
										"This is an error message", JOptionPane.WARNING_MESSAGE);
								e1.printStackTrace();
							}
						}
					}

				} else {
					JOptionPane.showMessageDialog(worldmap, "You Must Have At Least One Army",
							"This is an error message", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				if (!a)
					JOptionPane.showMessageDialog(worldmap, "Cairo Is One Of Your Cities", "This is an error message",
							JOptionPane.WARNING_MESSAGE);
				else {
					if (g.getPlayer().getControlledArmies().size() == 0) {
						JOptionPane.showMessageDialog(worldmap, "Your Army Is Empty", "This is an error message",
								JOptionPane.WARNING_MESSAGE);
					} else
						JOptionPane.showMessageDialog(worldmap, "You should reach Cairo First",
								"This is an error message", JOptionPane.WARNING_MESSAGE);

				}
			}

			if (g.isGameOver()) {
				worldmap.dispose();
				new EndGame(true);
			}
			cairoView.map.doClick();
		}

		if (e.getSource() == autos) {
			boolean a = true;
			for (City c : g.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase("sparta"))
					a = false;
			}
			if (a && g.getPlayer().getControlledArmies().size() != 0 && (g.getPlayer().getControlledArmies()
					.get(armies.getSelectedIndex()).getCurrentLocation().equalsIgnoreCase("sparta"))) {
				if (g.getPlayer().getControlledArmies().size() > 0) {
					for (City c : g.getAvailableCities()) {
						if (c.getName().equalsIgnoreCase("sparta")) {
							try {
							
								int count = g.getPlayer().getControlledArmies().size();
								g.autoResolve(g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()),
										c.getDefendingArmy());
								if (count > g.getPlayer().getControlledArmies().size()) {
									JOptionPane.showMessageDialog(worldmap, "You Win The Figth", "Information Message",
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(worldmap, "You Lose The Figth", "Information Message",
											JOptionPane.INFORMATION_MESSAGE);
								}
							} catch (FriendlyFireException e1) {
								JOptionPane.showMessageDialog(worldmap, "You Attack Your City",
										"This is an error message", JOptionPane.WARNING_MESSAGE);
								e1.printStackTrace();
							}
						}
					}

				} else {
					JOptionPane.showMessageDialog(worldmap, "You Must Have At Least One Army",
							"This is an error message", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				if (!a)
					JOptionPane.showMessageDialog(worldmap, "Sparta Is One Of Your Cities", "This is an error message",
							JOptionPane.WARNING_MESSAGE);
				else {
					if (g.getPlayer().getControlledArmies().size() == 0) {
						JOptionPane.showMessageDialog(worldmap, "Your Army Is Empty", "This is an error message",
								JOptionPane.WARNING_MESSAGE);
					} else
						JOptionPane.showMessageDialog(worldmap, "You should reach Sparta First",
								"This is an error message", JOptionPane.WARNING_MESSAGE);

				}
			}
			if (g.isGameOver()) {
				worldmap.dispose();
				new EndGame(true);
			}
			cairoView.map.doClick();

		}
		if (e.getSource() == besiegr) {
			if (g.getPlayer().getControlledArmies().size() > 0) {
				for (City c : g.getAvailableCities()) {
					if (c.getName().equalsIgnoreCase("rome")) {
						try {
							g.getPlayer().laySiege(g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()),
									c);
						} catch (TargetNotReachedException e2) {
							JOptionPane.showMessageDialog(worldmap, "You should reach Rome first",
									"This is an error message", JOptionPane.WARNING_MESSAGE);
							e2.printStackTrace();
						} catch (FriendlyCityException e1) {
							JOptionPane.showMessageDialog(worldmap, "You can't besige your city",
									"This is an error message", JOptionPane.WARNING_MESSAGE);
							e1.printStackTrace();
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(worldmap, "You Must Have At Least One Army", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		if (e.getSource() == besiegc) {
			if (g.getPlayer().getControlledArmies().size() > 0) {
				for (City c : g.getAvailableCities()) {
					if (c.getName().equalsIgnoreCase("cairo")) {
						try {
							g.getPlayer().laySiege(g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()),
									c);
						} catch (TargetNotReachedException e2) {
							JOptionPane.showMessageDialog(worldmap, "You should reach Cairo first",
									"This is an error message", JOptionPane.WARNING_MESSAGE);
							e2.printStackTrace();
						} catch (FriendlyCityException e1) {
							JOptionPane.showMessageDialog(worldmap, "You can't besige your city",
									"This is an error message", JOptionPane.WARNING_MESSAGE);
							e1.printStackTrace();
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(worldmap, "You Must Have At Least One Army", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		if (e.getSource() == besiegs) {
			if (g.getPlayer().getControlledArmies().size() > 0) {
				for (City c : g.getAvailableCities()) {
					if (c.getName().equalsIgnoreCase("sparta")) {
						try {
							g.getPlayer().laySiege(g.getPlayer().getControlledArmies().get(armies.getSelectedIndex()),
									c);
						} catch (TargetNotReachedException e2) {
							JOptionPane.showMessageDialog(worldmap, "You should reach Sparta first",
									"This is an error message", JOptionPane.WARNING_MESSAGE);
							e2.printStackTrace();
						} catch (FriendlyCityException e1) {
							JOptionPane.showMessageDialog(worldmap, "You can't besige your city",
									"This is an error message", JOptionPane.WARNING_MESSAGE);
							e1.printStackTrace();
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(worldmap, "You Must Have At Least One Army", "This is an error message",
						JOptionPane.WARNING_MESSAGE);
			}

		}
	}
}
