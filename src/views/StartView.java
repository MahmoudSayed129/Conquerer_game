package views;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.synth.SynthOptionPaneUI;

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
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;

public class StartView implements ActionListener {
	JLabel l1, l2, l3, l4, l5, l6, turncount, gold, playern, food, cityName;
	JTextArea playername;
	JButton startbutton;
	Game g;
	JComboBox<JButton> cities, Armies;
	int width, height;
	JFrame s = new JFrame();
	Image icon = new ImageIcon(this.getClass().getResource("/Icon.jpg")).getImage();
	Image icon2 = new ImageIcon(this.getClass().getResource("/Wallpaper.jpg")).getImage();
	ImageIcon inIcon = new ImageIcon(icon2);
	JLabel label = new JLabel("", inIcon, JLabel.CENTER);
	WorldMapView worldMap;

	public StartView() {
		s.pack();
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		label.setPreferredSize(new Dimension(1000, 1000));
		s.setExtendedState(JFrame.MAXIMIZED_BOTH);
		s.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		label.setBounds(0, 0, 1366, 768);
		s.add(label);
		// s.setResizable(false);

		s.setLayout(null);
		l1 = new JLabel("Please Enter your name");
		l1.setFont(new Font("", Font.BOLD, 20));
		l1.setForeground(Color.BLUE);
		l1.setBounds(width / 12, height / 16, width / 4, height / 32);
		playername = new JTextArea();
		playername.setFont(new Font("", Font.ITALIC, 20));
		playername.setForeground(Color.RED);
		playername.setBounds(5 * width / 12, height / 16, width / 4, height / 32);
		startbutton = new JButton();
		startbutton.addActionListener(this);
		startbutton.setIcon(new ImageIcon(this.getClass().getResource("/Start.png").getFile()));
		startbutton.setBounds(13 * width / 32, 15 * height / 32, 3 * width / 16, height / 16);
		String[] cit = { "Rome", "Cairo", "Sparta" };
		cities = new JComboBox(cit);
		s.setIconImage(icon);
//		cities.add(Armies, 4);
		cities.setBounds(5 * width / 12, 3 * height / 16, width / 4, height / 32);
		l2 = new JLabel("Please Choose The City You want");
		l2.setFont(new Font("", Font.BOLD, 20));
		l2.setForeground(Color.BLUE);
		l2.setBounds(width / 12, 3 * height / 16, width / 4, height / 32);
		s.add(l2);
		s.add(l1);
		s.add(cities);
		s.add(startbutton);
		s.add(playername);
		s.setVisible(true);
		s.add(label);
		s.repaint();
		s.validate();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startbutton) {
			if (playername.getText().isEmpty()) {
				// s.dispose();
				JOptionPane.showMessageDialog(s, "You must enter a name before starting", "Error",
						JOptionPane.WARNING_MESSAGE);

			} else {

				try {

					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
							new File("i/Come On Boy Move That Body -Would You Die For Me.wav").getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
				} catch (UnsupportedAudioFileException | IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String city = new String();
				if (cities.getSelectedIndex() == 2) {
					city = "Sparta";
				} else {
					if (cities.getSelectedIndex() == 1) {
						city = "Cairo";
					} else {
						city = "Rome";
					}
				}
				try {
					g = new Game(playername.getText(), city);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				s.dispose();
				worldMap = new WorldMapView(g);
				worldMap.worldmap.setVisible(true);

			}
		}
	}

	public static void main(String[] args) {
		new StartView();
	}
}