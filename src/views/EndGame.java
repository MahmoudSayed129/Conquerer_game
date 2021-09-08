package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class EndGame implements ActionListener {
	//static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	boolean win;
	JFrame frame;
	JButton newgame;
	JPanel message , ba;
	JLabel winner, loser;
	int width, height;
	
	public EndGame(boolean win) {
		Font newFont = new Font("MV Boli", Font.BOLD, 30);
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		winner = new JLabel(new ImageIcon(this.getClass().getResource("/win2.jpg").getFile()));
		loser = new JLabel(new ImageIcon(this.getClass().getResource("/over.jpg").getFile()));
		this.win = win;
		frame = new JFrame();
		ba = new JPanel();
		ba.setBounds(0, 0, width, height);
		ba.setBackground(Color.BLACK);
		//frame.setBackground(Color.CYAN);
		frame.setSize(width, height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(null);
		message = new JPanel();
		ba.setLayout(null);
		message.setBackground(Color.BLACK);
		//message.setBackground(Color.black);
		message.setBounds(250, 100, 866, 300);
		newgame = new JButton("Start new Game");
		newgame.setBackground(Color.CYAN);
		newgame.addActionListener(this);
		newgame.setFont(newFont);
		newgame.setBounds(3*width/8,22*height/32, 2*width/8, height / 16);
		ba.add(newgame);

		if (win) {
			message.add(winner);
		} else {
			message.add(loser);
		}
		ba.add(message);
		frame.add(ba);
		//frame.setUndecorated(true);
		//device.setFullScreenWindow(frame);
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		new StartView();

	}

	public static void main(String[] args) {
		new EndGame(true);
	}

}
