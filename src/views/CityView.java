package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyFireException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class CityView implements ActionListener {
	// static GraphicsDevice device =
	// GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	JFrame cityFrame;
	JLabel turncount, gold, playern, food, cityName;
	JLabel lmarket, lfarm, larcher, lbrracks, lstable;
	JButton umarket, ufarm, uarcher, ubrracks, ustable, rarcher, rbrracks, rstable;
	boolean f, m, s, b, a;
	JButton market, farm, archerRange, barracks, stable, endturn, map;
	JPanel economic, military, economicR, militaryR, economicS, militaryS, end, info, playerInfo, Information;
	Game g;
	WorldMapView worldMapView;
	int cf;
	JButton cityArmy;
	JButton defenda;
	JScrollPane scrollPane;
	defendingArmy defendingArmyView;
	cityarmy cityarmy;
	String Name;

	public CityView(Game g, WorldMapView worldMapView, String Name) {
		Font newFont1 = new Font("Arial", Font.BOLD, 12);
		f = true;
		b = true;
		m = true;
		a = true;
		s = true;
		this.g = g;
		this.Name = Name;
		this.worldMapView = worldMapView;
		gold = new JLabel("Gold : " + g.getPlayer().getTreasury());
		food = new JLabel("Food : " + g.getPlayer().getFood());
		turncount = new JLabel("Turns : " + g.getCurrentTurnCount());

		cityFrame = new JFrame(Name);
		cityFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		cityFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		economic = new JPanel(new GridLayout(0, 2));
		market = new JButton("Build Market , Cost 1500");
		market.addActionListener(this);
		farm = new JButton("Build Farm  , Cost 1000");
		farm.addActionListener(this);
		umarket = new JButton("Upgrade market");
		umarket.addActionListener(this);
		ufarm = new JButton("Upgrade farm");
		ufarm.addActionListener(this);
		ufarm.setVisible(false);
		umarket.setVisible(false);
		economic.add(farm);
		economic.add(ufarm);
		economic.add(market);
		economic.add(umarket);
		// economic.setSize(460, 660);
		cityFrame.add(economic, BorderLayout.WEST);
		// economic.setBounds(0, 31, 1000, 600);
		military = new JPanel(new GridLayout(0, 3));
		archerRange = new JButton("Build ArcherRange , Cost 1500");
		archerRange.addActionListener(this);
		barracks = new JButton("Build Barracks , Cost 2000");
		barracks.addActionListener(this);
		stable = new JButton("Build Stable , Cost 2500");
		stable.addActionListener(this);
		rarcher = new JButton("Recruit Archer");
		rarcher.addActionListener(this);
		rarcher.setVisible(false);
		rbrracks = new JButton("Recruit Infantry");
		rbrracks.addActionListener(this);
		rbrracks.setVisible(false);
		rstable = new JButton("Recruit Cavalry");
		rstable.addActionListener(this);
		rstable.setVisible(false);
		uarcher = new JButton("Update ArcherRange");
		uarcher.addActionListener(this);
		uarcher.setVisible(false);
		ubrracks = new JButton("Update Barracks");
		ubrracks.addActionListener(this);
		ubrracks.setVisible(false);
		ustable = new JButton("Update Stable");
		ustable.addActionListener(this);
		ustable.setVisible(false);

		military.add(archerRange);
		military.add(uarcher);
		military.add(rarcher);
		military.add(barracks);
		military.add(ubrracks);
		military.add(rbrracks);
		military.add(stable);
		military.add(ustable);
		military.add(rstable);
//			scrollPane=new JScrollPane(military);
//			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		cityFrame.add(military, BorderLayout.CENTER);
		Information = new JPanel(new GridLayout(1, 0));
		playern = new JLabel("Welcome : " + g.getPlayer().getName());
		turncount = new JLabel("Turns : " + g.getCurrentTurnCount() + "");
		gold = new JLabel("Gold : " + g.getPlayer().getTreasury() + "");
		food = new JLabel("Food : " + g.getPlayer().getFood() + "");
		Information.add(playern);
		Information.add(gold);
		Information.add(food);
		Information.add(turncount);

		Information.setBackground(Color.WHITE);

		cityFrame.add(Information, BorderLayout.NORTH);
		end = new JPanel(new GridLayout(1, 0));
		endturn = new JButton("EndTurn");
		map = new JButton("Map");
		map.addActionListener(this);
		endturn.addActionListener(this);
		end.add(endturn);
		end.add(map);
		cityArmy = new JButton("City Army");
		cityArmy.addActionListener(this);
		defenda = new JButton("Defending Army");
		defenda.addActionListener(this);
		end.add(cityArmy);
		end.add(defenda);
		cityFrame.add(end, BorderLayout.SOUTH);
		// cityFrame.setUndecorated(true);
		// device.setFullScreenWindow(cityFrame);
		uarcher.setFont(newFont1);
		rarcher.setFont(newFont1);
		ubrracks.setFont(newFont1);
		rbrracks.setFont(newFont1);
		ustable.setFont(newFont1);
		rstable.setFont(newFont1);
		ufarm.setFont(newFont1);
		market.setFont(newFont1);
		farm.setFont(newFont1);
		archerRange.setFont(newFont1);
		stable.setFont(newFont1);
		barracks.setFont(newFont1);
		umarket.setFont(newFont1);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == map) {
			cityFrame.dispose();
			this.worldMapView.Information.removeAll();
			playern.setText("Welcome : " + g.getPlayer().getName());
			turncount.setText("Turns : " + g.getCurrentTurnCount() + "");
			gold.setText("Gold : " + g.getPlayer().getTreasury() + "");
			food.setText("Food : " + g.getPlayer().getFood() + "");
			this.worldMapView.Information.add(playern);
			this.worldMapView.Information.add(turncount);
			this.worldMapView.Information.add(gold);
			this.worldMapView.Information.add(food);
			String[] armies = new String[100];
			String[] unites = new String[100];
			int c = 0;
			for (int i = 0; i < g.getPlayer().getControlledArmies().size(); i++) {
				if (g.getPlayer().getControlledArmies().get(i).getUnits().size() != 0) {
					int j = i+1;
					armies[c] = "Army" + j;
					c++;
				}
			}
			ComboBoxModel<JButton> modle = new DefaultComboBoxModel(armies);
			this.worldMapView.armies.setModel(modle);
			int i = 0;
			for (City city : g.getPlayer().getControlledCities()) {
				for (Unit unit : city.getDefendingArmy().getUnits()) {
					if (unit instanceof Archer) {
						unites[i] = "Archer : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
								+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " "
								+ city.getName();
						this.worldMapView.unites[i] = "Archer : " + "level : " + unit.getLevel()
								+ " CurrenSoldiersCount : " + unit.getCurrentSoldierCount() + "/"
								+ unit.getMaxSoldierCount() + " " + city.getName();
						i++;
					}
					if (unit instanceof Cavalry) {
						unites[i] = "Cavalry : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
								+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " "
								+ city.getName();
						this.worldMapView.unites[i] = "Cavalry : " + "level : " + unit.getLevel()
								+ " CurrenSoldiersCount : " + unit.getCurrentSoldierCount() + "/"
								+ unit.getMaxSoldierCount() + " " + city.getName();
						i++;
					}
					if (unit instanceof Infantry) {
						unites[i] = "Infantry : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
								+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " "
								+ city.getName();
						this.worldMapView.unites[i] = "Infantry : " + "level : " + unit.getLevel()
								+ " CurrenSoldiersCount : " + unit.getCurrentSoldierCount() + "/"
								+ unit.getMaxSoldierCount() + " " + city.getName();
						i++;
					}
				}
				int arm = 1;
				for (Army a : g.getPlayer().getControlledArmies()) {
					for (Unit unit : a.getUnits()) {
						if (unit instanceof Archer) {
							unites[i] = "Archer : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
									+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " " + "Army"
									+ arm;
							this.worldMapView.unites[i] = "Archer : " + "level : " + unit.getLevel()
									+ " CurrenSoldiersCount : " + unit.getCurrentSoldierCount() + "/"
									+ unit.getMaxSoldierCount() + " " + "Army" + arm;
						}
						if (unit instanceof Cavalry) {
							unites[i] = "Cavalry : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
									+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " " + "Army"
									+ arm;
							this.worldMapView.unites[i] = "Cavalry : " + "level : " + unit.getLevel()
									+ " CurrenSoldiersCount : " + unit.getCurrentSoldierCount() + "/"
									+ unit.getMaxSoldierCount() + " " + "Army" + arm;
						}
						if (unit instanceof Infantry) {
							unites[i] = "Infantry : " + "level : " + unit.getLevel() + " CurrenSoldiersCount : "
									+ unit.getCurrentSoldierCount() + "/" + unit.getMaxSoldierCount() + " " + "Army"
									+ arm;
							this.worldMapView.unites[i] = "Infantry : " + "level : " + unit.getLevel()
									+ " CurrenSoldiersCount : " + unit.getCurrentSoldierCount() + "/"
									+ unit.getMaxSoldierCount() + " " + "Army" + arm;
						}
						i++;
						// System.out.println(f);
					}
					arm++;
				}
				ComboBoxModel<JButton> modle2 = new DefaultComboBoxModel(unites);
				this.worldMapView.units.setModel(modle2);

			}
			// System.out.println("Map Button is Pressed"+ playern.getText()+"
			// "+turncount.getText()+" "+food.getText()+" "+gold.getText());
			// this.worldMapView.worldmap.setUndecorated(true);
//			device.setFullScreenWindow(this.worldMapView.worldmap);
//			device.setFullScreenWindow(this.worldMapView.worldmap);
			this.worldMapView.worldmap.setVisible(true);
			this.worldMapView.worldmap.repaint();
			this.worldMapView.worldmap.validate();

		}
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
			cityFrame.setVisible(true);
			for (City c : g.getAvailableCities()) {
				if (c.getTurnsUnderSiege() == 3) { //// kosom Mahmoud
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

						cityFrame.dispose();
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
							//cityFrame.dispose();
//							cityFrame.setVisible(true);
						} catch (FriendlyFireException e1) {
						}
						map.doClick();
					}
					if (response == 1) {
						cityFrame.dispose();
						new battelView(army, c.getDefendingArmy(), cityFrame, this, g, c.getName());
					}
				}
			}
			if (g.isGameOver()) {
				cityFrame.dispose();
				new EndGame(false);
			}

		}
		if (e.getSource() == farm && f) {
			try {
				g.getPlayer().build("Farm", Name);
				farm.setText(null);
				farm.setBackground(Color.WHITE);
				farm.setIcon(new ImageIcon(this.getClass().getResource("/farm2.jpg").getFile()));
				f = false;
				ufarm.setVisible(true);
				ufarm.setText("current level is 1 ,upgrade cost 500");
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "Not Enough gold", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			Information.removeAll();
			playern = new JLabel("Welcome : " + g.getPlayer().getName());
			turncount = new JLabel("Turns : " + g.getCurrentTurnCount() + "");
			gold = new JLabel("Gold : " + g.getPlayer().getTreasury() + "");
			food = new JLabel("Food : " + g.getPlayer().getFood() + "");
			Information.add(playern);
			Information.add(gold);
			Information.add(food);
			Information.add(turncount);
		}
		if (e.getSource() == ufarm) {
			try {
				int cost = 0;
				for (City city : g.getPlayer().getControlledCities()) {
					if (city.getName().toLowerCase().equalsIgnoreCase(Name)) {
						for (EconomicBuilding f : city.getEconomicalBuildings()) {
							if (f instanceof Farm) {
								g.getPlayer().upgradeBuilding(f);
								cf = f.getLevel();
								cost = f.getUpgradeCost();
							}
						}
					}

				}
				if (cf == 3) {
					ufarm.setText("Farm is Max Level");
				} else {
					ufarm.setText("current Level is" + " " + cf + "\n" + " " + "UpgradeCost" + " " + cost);
				}
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(cityFrame, "City is in cooldown ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (MaxLevelException e1) {
				JOptionPane.showMessageDialog(cityFrame, "This is the maxLevel ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "Not Enough Gold", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			gold.setText("Gold : " + g.getPlayer().getTreasury());

		}
		if (e.getSource() == market && m) {
			try {
				g.getPlayer().build("Market", Name);
				market.setText(null);
				market.setBackground(Color.WHITE);
				market.setIcon(new ImageIcon(this.getClass().getResource("/market3.jpg").getFile()));
				m = false;
				umarket.setVisible(true);
				umarket.setText("current level is 1 ,upgrade cost 700");
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "No Enough gold ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			Information.removeAll();
			playern = new JLabel("Welcome : " + g.getPlayer().getName());
			turncount = new JLabel("Turns : " + g.getCurrentTurnCount() + "");
			gold = new JLabel("Gold : " + g.getPlayer().getTreasury() + "");
			food = new JLabel("Food : " + g.getPlayer().getFood() + "");
			Information.add(playern);
			Information.add(gold);
			Information.add(food);
			Information.add(turncount);
		}
		if (e.getSource() == umarket) {
			try {
				int cost = 0;
				for (City city : g.getPlayer().getControlledCities()) {
					if (city.getName().toLowerCase().equalsIgnoreCase(Name)) {
						for (EconomicBuilding f : city.getEconomicalBuildings()) {
							if (f instanceof Market) {
								g.getPlayer().upgradeBuilding(f);
								cf = f.getLevel();
								cost = f.getUpgradeCost();
							}
						}
					}
				}
				if (cf == 3) {
					umarket.setText("Market is Max Level");
				} else {
					umarket.setText("Update Market" + " " + "\n" + "Level" + " " + cf + "\n" + " " + "UpgradeCost" + " "
							+ cost);
				}
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(cityFrame, "City is in cooldown ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (MaxLevelException e1) {
				JOptionPane.showMessageDialog(cityFrame, "This is the maxLevel ", "Erro", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "Not Enough Gold", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			gold.setText("Gold : " + g.getPlayer().getTreasury());
		}
		if (e.getSource() == barracks && b) {
			try {
				g.getPlayer().build("Barracks", Name);
				barracks.setText(null);
				barracks.setIcon(new ImageIcon(this.getClass().getResource("/infantry4.jpg").getFile()));
				b = false;
				ubrracks.setVisible(true);
				rbrracks.setVisible(true);
				ubrracks.setText("current level is 1 ,upgrade cost 1000");
				rbrracks.setText("Recruit Cost is 500");
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "No Enough gold ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			Information.removeAll();
			playern = new JLabel("Welcome : " + g.getPlayer().getName());
			turncount = new JLabel("Turns : " + g.getCurrentTurnCount() + "");
			gold = new JLabel("Gold : " + g.getPlayer().getTreasury() + "");
			food = new JLabel("Food : " + g.getPlayer().getFood() + "");
			Information.add(playern);
			Information.add(gold);
			Information.add(food);
			Information.add(turncount);
		}
		if (e.getSource() == stable && s) {
			try {
				g.getPlayer().build("Stable", Name);
				stable.setText(null);
				stable.setIcon(new ImageIcon(this.getClass().getResource("/cavalary2.jpg").getFile()));
				s = false;
				ustable.setVisible(true);
				rstable.setVisible(true);

				ustable.setText("current level is 1 ,upgrade cost 1500");
				rstable.setText("Recruit Cost is 600");
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "This is an error message", "No Enough gold ",
						JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			Information.removeAll();
			playern = new JLabel("Welcome : " + g.getPlayer().getName());
			turncount = new JLabel("Turns : " + g.getCurrentTurnCount() + "");
			gold = new JLabel("Gold : " + g.getPlayer().getTreasury() + "");
			food = new JLabel("Food : " + g.getPlayer().getFood() + "");
			Information.add(playern);
			Information.add(gold);
			Information.add(food);
			Information.add(turncount);
		}
		if (e.getSource() == archerRange && a) {
			try {
				g.getPlayer().build("ArcheryRange", Name);
				archerRange.setText(null);
				archerRange.setIcon(new ImageIcon(this.getClass().getResource("/archer2.jpg").getFile()));
				a = false;
				uarcher.setVisible(true);
				rarcher.setVisible(true);

				uarcher.setText("current level is 1 ,upgrade cost 800");
				rarcher.setText("Recruit Cost is 400");

			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "No Enough gold ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			Information.removeAll();
			playern = new JLabel("Welcome : " + g.getPlayer().getName());
			turncount = new JLabel("Turns : " + g.getCurrentTurnCount() + "");
			gold = new JLabel("Gold : " + g.getPlayer().getTreasury() + "");
			food = new JLabel("Food : " + g.getPlayer().getFood() + "");
			Information.add(playern);
			Information.add(gold);
			Information.add(food);
			Information.add(turncount);
		}
		if (e.getSource() == ubrracks) {
			try {

				int cost = 0;
				int a = 0;
				for (City city : g.getPlayer().getControlledCities()) {
					if (city.getName().toLowerCase().equalsIgnoreCase(Name)) {
						for (MilitaryBuilding f : city.getMilitaryBuildings()) {
							if (f instanceof Barracks) {
								g.getPlayer().upgradeBuilding(f);
								cf = f.getLevel();
								cost = f.getUpgradeCost();
								a = f.getRecruitmentCost();
							}
						}
					}
				}

				if (cf == 3) {
					ubrracks.setText("Barracks is Max Level");
				} else {
					ubrracks.setText("Update Barracks" + " " + "\n" + "Level" + " " + cf + "\n" + " " + "UpgradeCost"
							+ " " + cost);
				}
				rbrracks.setText("Recruit Cost" + " " + a);

			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(cityFrame, "City is in cooldown ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (MaxLevelException e1) {
				JOptionPane.showMessageDialog(cityFrame, "This is the maxLevel ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "Not Enough Gold", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			gold.setText("Gold : " + g.getPlayer().getTreasury());
		}
		if (e.getSource() == ustable) {
			try {
				int cost = 0;
				int a = 0;
				for (City city : g.getPlayer().getControlledCities()) {
					if (city.getName().toLowerCase().equalsIgnoreCase(Name)) {
						for (MilitaryBuilding f : city.getMilitaryBuildings()) {
							if (f instanceof Stable) {
								g.getPlayer().upgradeBuilding(f);
								cf = f.getLevel();
								cost = f.getUpgradeCost();
								a = f.getRecruitmentCost();
							}
						}
					}
				}

				if (cf == 3) {
					ustable.setText("Stable is Max Level");
				} else {
					ustable.setText("Update Stable" + " " + "\n" + "Level" + " " + cf + "\n" + " " + "UpgradeCost" + " "
							+ cost);
				}
				rstable.setText("Recruit Cost" + " " + a);

			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(cityFrame, "City is in cooldown ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (MaxLevelException e1) {
				JOptionPane.showMessageDialog(cityFrame, "Not Enough Gold", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "Not Enough Gold", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			gold.setText("Gold : " + g.getPlayer().getTreasury());
		}
		if (e.getSource() == uarcher) {
			try {
				int cost = 0;
				int a = 0;
				for (City city : g.getPlayer().getControlledCities()) {
					if (city.getName().toLowerCase().equalsIgnoreCase(Name)) {
						for (MilitaryBuilding f : city.getMilitaryBuildings()) {
							if (f instanceof ArcheryRange) {
								g.getPlayer().upgradeBuilding(f);
								cf = f.getLevel();
								cost = f.getUpgradeCost();
								a = f.getRecruitmentCost();
							}
						}
					}
				}

				if (cf == 3) {
					uarcher.setText("ArcheryRange is Max Level");
				} else {
					uarcher.setText("Update ARange" + " " + "\n" + "Level" + " " + cf + "\n" + " " + "UpgradeCost" + " "
							+ cost);
				}
				rarcher.setText("Recruit Cost" + " " + a);

			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(cityFrame, "City is in cooldown ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (MaxLevelException e1) {
				JOptionPane.showMessageDialog(cityFrame, "This is the maxLevel ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "Not Enough Gold", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			gold.setText("Gold : " + g.getPlayer().getTreasury());
		}
		if (e.getSource() == rbrracks) {
			try {
				g.getPlayer().recruitUnit("Infantry", Name);

			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(cityFrame, "City is inCooldown ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (MaxRecruitedException e1) {
				JOptionPane.showMessageDialog(cityFrame, "Can not recruite extra units ", "Error",
						JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "No enough gold", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			gold.setText("Gold : " + g.getPlayer().getTreasury());
		}
		if (e.getSource() == rarcher) {
			try {
				g.getPlayer().recruitUnit("Archer", Name);

			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(cityFrame, "City is inCooldown ", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (MaxRecruitedException e1) {
				JOptionPane.showMessageDialog(cityFrame, "Can not recruite extra units ", "Error",
						JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "No enough gold", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			gold.setText("Gold : " + g.getPlayer().getTreasury());
		}
		if (e.getSource() == rstable) {
			try {
				g.getPlayer().recruitUnit("Cavalry", Name);

			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(cityFrame, "City is in Cooldown", "Error ", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (MaxRecruitedException e1) {
				JOptionPane.showMessageDialog(cityFrame, "Can not recruite extra units ", "Error ",
						JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(cityFrame, "No enough gold", "Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			gold.setText("Gold : " + g.getPlayer().getTreasury());
		}
		if (e.getSource() == defenda) {
			cityFrame.dispose();
			for (City c : g.getPlayer().getControlledCities()) {
				if (c.getName().toLowerCase().equalsIgnoreCase(Name))
					defendingArmyView = new defendingArmy(g, c, cityFrame);
			}

		}
		if (e.getSource() == cityArmy) {
			cityFrame.dispose();
			for (City c : g.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(Name))
					cityarmy = new cityarmy(g, c, cityFrame);
			}

		}
	}
}
