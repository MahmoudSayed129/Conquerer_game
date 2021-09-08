package views;

import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import engine.City;
import engine.Game;
import units.Archer;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class ArmyView implements ActionListener {
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	Game game;
	JFrame map, armyview;
	int width, height;
	JTextArea march, idle, besi;
	JButton btmap;
	JScrollPane ms, is, bs;

	public ArmyView(Game game, JFrame map) {
		Font newFont = new Font("Arial", Font.BOLD, 25);
		btmap = new JButton("Back to Map");
		btmap.addActionListener(this);
		btmap.setFont(newFont);
		this.game = game;
		this.map = map;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		armyview = new JFrame();
		armyview.setExtendedState(JFrame.MAXIMIZED_BOTH);
		armyview.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		armyview.setLayout(null);
		march=new JTextArea();
		march.setText("Marching Armies :-\n");
		march.setFont(newFont);
		march.setEditable(false);
		ms = new JScrollPane(march, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		ms.setBounds(0, 0, width,2* height/7);
		armyview.add(ms);
		idle=new JTextArea();
		idle.setText("Idel Armies :-\n");
		idle.setFont(newFont);
		idle.setEditable(false);
		is = new JScrollPane(idle, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		is.setBounds(0, 2*height/7,width,2* height/7);
		armyview.add(is);
		besi=new JTextArea();
		besi.setText("Besieging Armies :-\n");
		besi.setFont(newFont);
		besi.setEditable(false);
		bs = new JScrollPane(besi, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		bs.setBounds(0,4*height/7,width,2* height/7);
		armyview.add(bs);
		btmap.setBounds(0,6*height/7,width, height/7);
		armyview.add(btmap);
		for (int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {
			int b=i+1;
			if(game.getPlayer().getControlledArmies().get(i).getUnits().size() != 0) {
				if (game.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.IDLE) ) {
				idle.append("Army"+ b +"  "+game.getPlayer().getControlledArmies().get(i).getCurrentLocation()+"\n");
				//System.out.println("id"+ " "+ game.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("onRoad"));
				for(Unit u : game.getPlayer().getControlledArmies().get(i).getUnits()) {
					if (u instanceof Archer) {
						idle.append("Archer :   level" + " " + u.getLevel() + " " + "Current soldier  : "
								+ u.getCurrentSoldierCount()+"\n");

						
					}
					if (u instanceof Infantry) {
						idle.append("Infantry :   level" + " " + u.getLevel() + " " + "Current soldier  : "
								+ u.getCurrentSoldierCount()+"\n");
						

					}
					if (u instanceof Cavalry) {
						idle.append("Cavalary :   level" + " " + u.getLevel() + " " + "Current soldier  : "
								+ u.getCurrentSoldierCount()+"\n");
						

					}

			              
				}
             
			}
			if (game.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING)) {
				march.append("Army"+ b+"  To "+game.getPlayer().getControlledArmies().get(i).getTarget()+" and still "+game.getPlayer().getControlledArmies().get(i).getDistancetoTarget()+" to reach"+"\n");
				//System.out.println("ma");
				for(Unit u : game.getPlayer().getControlledArmies().get(i).getUnits()) {
					if (u instanceof Archer) {
						march.append("Archer :   level" + " " + u.getLevel() + " " + "Current soldier  : "
								+ u.getCurrentSoldierCount()+"\n");

						
					}
					if (u instanceof Infantry) {
						march.append("Infantry :   level" + " " + u.getLevel() + " " + "Current soldier  : "
								+ u.getCurrentSoldierCount()+"\n");
						

					}
					if (u instanceof Cavalry) {
						march.append("Cavalary :   level" + " " + u.getLevel() + " " + "Current soldier  : "
								+ u.getCurrentSoldierCount()+"\n");
						

					}
		              
				}
			}
			if (game.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.BESIEGING) ) {
				City city = null;
				for(City c : game.getAvailableCities()) {
					
					 if(c.getName().equalsIgnoreCase(game.getPlayer().getControlledArmies().get(i).getCurrentLocation()) ) {
						 city = c;
					 }
				}
				besi.append("Army"+ b+" besige "+city.getName()+" for "+city.getTurnsUnderSiege()+" turns "+ "\n");
				for(Unit u : game.getPlayer().getControlledArmies().get(i).getUnits()) {
					if (u instanceof Archer) {
						besi.append("Archer :   level" + " " + u.getLevel() + " " + "Current soldier  : "
								+ u.getCurrentSoldierCount()+"\n");

						
					}
					if (u instanceof Infantry) {
						besi.append("Infantry :   level" + " " + u.getLevel() + " " + "Current soldier  : "
								+ u.getCurrentSoldierCount()+"\n");
						

					}
					if (u instanceof Cavalry) {
						besi.append("Cavalary :   level" + " " + u.getLevel() + " " + "Current soldier  : "
								+ u.getCurrentSoldierCount()+"\n");
						

					}
		              
				}
	            
	              
			}
			}
		}
		armyview.setUndecorated(true);
		device.setFullScreenWindow(armyview);
		armyview.setVisible(true);
        
		

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btmap) {
//			device.setFullScreenWindow(map);
			map.setVisible(true);
			armyview.setVisible(false);
						
		}

	}



}
