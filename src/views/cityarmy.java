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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import engine.City;
import engine.Game;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class cityarmy implements ActionListener {
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	Game game;
	City city;
	JComboBox<JButton> armiesBox;
	JFrame frame, map;
	JPanel Information, graph, info;
	JButton mycity, showButton;
	JLabel playern, archer, infantry, cavlary;
	JPanel ar, in, ca;
	int width, height;
	JScrollPane arscroll, cascroll, inscroll;

	public cityarmy(Game game, City city, JFrame f) {
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.game = game;
		this.city = city;
		this.map = f;
		Font newFont = new Font("Arial", Font.ROMAN_BASELINE, 25);
		frame = new JFrame("DefendingArmy");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
		int j = 1;
		int c = 0;
		frame.setLayout(null);
		String[] Armies = new String[100];
		for (int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {
			if (game.getPlayer().getControlledArmies().get(i).getCurrentLocation() == city.getName()) {
				if (game.getPlayer().getControlledArmies().get(i).getUnits().size() != 0) {
					Armies[c] = "Army" + j;
					c++;
					
				}
			}
			j++;
		}
		armiesBox = new JComboBox(Armies);
		playern = new JLabel();
		Information = new JPanel();
		armiesBox.setBounds(0, 0, width / 6, height / 32);
		showButton = new JButton("Show Army");
		showButton.addActionListener(this);
		showButton.setBounds(width / 6, 0, width / 12, height / 32);
		frame.add(showButton);
		frame.add(armiesBox);
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
		ar.setBackground(Color.black);
		info.add(arscroll);

		in = new JPanel(new GridLayout(0, 2));
		inscroll = new JScrollPane(in);
		inscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		in.setBackground(Color.gray);
		info.add(inscroll);

		ca = new JPanel(new GridLayout(0, 2));
		cascroll = new JScrollPane(ca);
		cascroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		cascroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ca.setBackground(Color.black);
		info.add(cascroll);

		frame.add(Information);
		frame.add(mycity);
		frame.add(info);
		frame.add(graph);
		frame.setUndecorated(true);
		device.setFullScreenWindow(frame);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mycity) {
			frame.dispose();
			map.setVisible(true);

		}

		else {
			if (e.getSource() == showButton) {
				ar.removeAll();
				in.removeAll();
				ca.removeAll();
				int y = 0;
				for (Army army : game.getPlayer().getControlledArmies()) {
					if (y == armiesBox.getSelectedIndex()) {
						int i = y + 1;
						System.out.println("get selcted  " + armiesBox.getSelectedIndex() + " Army " + i);
						for (Unit u : army.getUnits()) {
							if (u instanceof Archer) {
								System.out.println("ar");
								JButton arButton = new JButton("Level" + "" + u.getLevel() + "  Current Soldier:"
										+ u.getCurrentSoldierCount() + "/" + u.getMaxSoldierCount());
								ar.add(arButton);
							}
							if (u instanceof Infantry) {
								System.out.println("in");
								JButton inButton = new JButton("Level" + "" + u.getLevel() + "  Current Soldier:"
										+ u.getCurrentSoldierCount() + "/" + u.getMaxSoldierCount());
								in.add(inButton);
							}
							if (u instanceof Cavalry) {
								System.out.println("ca");
								JButton caButton = new JButton("Level" + "" + u.getLevel() + "  Current Soldier:"
										+ u.getCurrentSoldierCount() + "/" + u.getMaxSoldierCount());
								ca.add(caButton);
							}
						}
						break;
					} else
						y++;

				}
				info.removeAll();
				info.add(arscroll);
				info.add(inscroll);
				info.add(cascroll);
				frame.validate();
				frame.repaint();
			}

		}

	}

}
