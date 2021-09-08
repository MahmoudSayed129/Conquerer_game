package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import engine.City;
import engine.Game;
import units.Archer;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class defendingArmy implements ActionListener {
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	Game game;
	City city;
	JFrame frame, map;
	ArrayList<JButton> arArrayList, caArrayList, inArrayList;
	JPanel Information, graph, info;
	JButton mycity, temp;
	JLabel playern, archer, infantry, cavlary, state;
	JPanel ar, in, ca;
	JScrollPane arscroll, inscroll, cascroll;
	int width, height;

	public defendingArmy(Game game, City city, JFrame f) {
		Font newFont1 = new Font("Arial", Font.BOLD, 35);
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.game = game;
		this.city = city;
		this.map = f;
		arArrayList = new ArrayList<JButton>();
		inArrayList = new ArrayList<JButton>();
		caArrayList = new ArrayList<JButton>();
		Font newFont = new Font("Arial", Font.ROMAN_BASELINE, 25);
		frame = new JFrame("DefendingArmy");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
		frame.setLayout(null);
		state = new JLabel("                                       Press any Unit To Intiate New Army");
		state.setFont(newFont1);
		state.setBackground(Color.black);
		state.setForeground(Color.red);
		state.setBounds(0, 17 * height / 32, width, 2 * height / 16);
		frame.add(state);
		playern = new JLabel();
		Information = new JPanel();
		playern.setText("Welcome :    " + game.getPlayer().getName() + "                 " + "Turns :    "
				+ game.getCurrentTurnCount() + "                 " + "Gold :    " + game.getPlayer().getTreasury()
				+ "                 " + "Food :    " + game.getPlayer().getFood() + "");
		Information.add(playern);
		Information.setBounds(0, 0, width, height / 32);
		Information.setOpaque(true);

		mycity = new JButton("Back to my city");
		mycity.setFont(newFont);
		mycity.addActionListener(this);
		mycity.setBounds(0, 15 * height / 16, width, 1 * height / 16);

		graph = new JPanel(new GridLayout(1, 0));
		graph.setBounds(0, height / 32, width, 19 * height / 32);
		graph.setBackground(Color.white);
		info = new JPanel(new GridLayout(1, 0));
		info.setBounds(0, 10 * height / 16, width, 5 * height / 16);
		info.setBackground(Color.white);

		archer = new JLabel(new ImageIcon(this.getClass().getResource("/archer.jpg").getFile()));
		graph.add(archer);

		infantry = new JLabel(new ImageIcon(this.getClass().getResource("/infantry.jpg").getFile()));
		graph.add(infantry);

		cavlary = new JLabel(new ImageIcon(this.getClass().getResource("/cavalary.jpg").getFile()));
		graph.add(cavlary);

		ar = new JPanel(new GridLayout(0, 2));
		arscroll = new JScrollPane(ar);
		arscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		arscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// ar.setFont(newFont);
		// ar.setForeground(Color.red);
		ar.setBackground(Color.black);
		info.add(arscroll);

		in = new JPanel(new GridLayout(0, 2));
		inscroll = new JScrollPane(in);
		inscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// in.setFont(newFont);
		// in.setForeground(Color.red);
		in.setBackground(Color.gray);
		info.add(inscroll);

		ca = new JPanel(new GridLayout(0, 2));
		cascroll = new JScrollPane(ca);
		cascroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		cascroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// ca.setFont(newFont);
		// ca.setForeground(Color.red);
		ca.setBackground(Color.black);
		info.add(cascroll);

		frame.add(Information);
		frame.add(mycity);
		frame.add(info);
		frame.add(graph);
		frame.setUndecorated(true);
		device.setFullScreenWindow(frame);
		frame.setVisible(true);

		for (City c : game.getPlayer().getControlledCities()) {
			if (c.equals(city)) {
				for (Unit u : city.getDefendingArmy().getUnits()) {
					if (u instanceof Archer) {
						JButton arButton = new JButton("Level" + "" + u.getLevel() + "  Current Soldier:"
								+ u.getCurrentSoldierCount() + "/" + u.getMaxSoldierCount());
						arButton.addActionListener(this);
						ar.add(arButton);
						arArrayList.add(arButton);
					}
					if (u instanceof Infantry) {
						JButton inButton = new JButton("Level" + "" + u.getLevel() + "  Current Soldier:"
								+ u.getCurrentSoldierCount() + "/" + u.getMaxSoldierCount());
						inButton.addActionListener(this);
						in.add(inButton);
						inArrayList.add(inButton);
					}
					if (u instanceof Cavalry) {
						JButton caButton = new JButton("Level" + "" + u.getLevel() + "  Current Soldier:"
								+ u.getCurrentSoldierCount() + "/" + u.getMaxSoldierCount());
						caButton.addActionListener(this);
						caArrayList.add(caButton);
						ca.add(caButton);
					}

				}

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == mycity) {
			frame.dispose();
			map.setVisible(true);
		} else {
			temp = (JButton) e.getSource();
			// int oo=city.getDefendingArmy().getUnits().size();
			for (City c : game.getPlayer().getControlledCities()) {
				if (c.equals(city)) {
					for (int a = 0; a < city.getDefendingArmy().getUnits().size(); a++) {
						if (city.getDefendingArmy().getUnits().get(a) instanceof Archer) {
							String arString = new String("Level" + ""
									+ city.getDefendingArmy().getUnits().get(a).getLevel() + "  Current Soldier:"
									+ city.getDefendingArmy().getUnits().get(a).getCurrentSoldierCount() + "/"
									+ city.getDefendingArmy().getUnits().get(a).getMaxSoldierCount());
							if (temp.getText().equals(arString)) {
								game.getPlayer().initiateArmy(city, city.getDefendingArmy().getUnits().get(a));
								for (int i = 0; i < arArrayList.size(); i++) {
									if (arArrayList.get(i) == temp) {
										arArrayList.remove(i);
									}
								}
								ar.removeAll();
								for (int j = 0; j < arArrayList.size(); j++) {
									ar.add(arArrayList.get(j));
								}
								break;
							}

						}
						if (city.getDefendingArmy().getUnits().get(a) instanceof Infantry) {
							String inString = new String("Level" + ""
									+ city.getDefendingArmy().getUnits().get(a).getLevel() + "  Current Soldier:"
									+ city.getDefendingArmy().getUnits().get(a).getCurrentSoldierCount() + "/"
									+ city.getDefendingArmy().getUnits().get(a).getMaxSoldierCount());
							if (temp.getText().equals(inString)) {

								game.getPlayer().initiateArmy(c, city.getDefendingArmy().getUnits().get(a));
								for (int i = 0; i < inArrayList.size(); i++) {
									if (inArrayList.get(i) == temp) {
										inArrayList.remove(i);
									}
								}
								in.removeAll();
								for (int j = 0; j < inArrayList.size(); j++) {
									in.add(inArrayList.get(j));
								}
								break;
							}
						}
						if (city.getDefendingArmy().getUnits().get(a) instanceof Cavalry) {
							String caString = new String("Level" + ""
									+ city.getDefendingArmy().getUnits().get(a).getLevel() + "  Current Soldier:"
									+ city.getDefendingArmy().getUnits().get(a).getCurrentSoldierCount() + "/"
									+ city.getDefendingArmy().getUnits().get(a).getMaxSoldierCount());
							if (temp.getText().equals(caString)) {
								game.getPlayer().initiateArmy(c, city.getDefendingArmy().getUnits().get(a));
								for (int i = 0; i < caArrayList.size(); i++) {
									if (caArrayList.get(i) == temp) {
										caArrayList.remove(i);
									}
								}
								ca.removeAll();
								for (int j = 0; j < caArrayList.size(); j++) {
									ca.add(caArrayList.get(j));
								}
								break;
							}
						}

					}

				}
			}
			frame.validate();
			frame.repaint();
		}

	}

}
