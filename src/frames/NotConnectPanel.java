package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import abstracts.Connection;
import abstracts.DefaultSession;
import abstracts.DefaultState;
import abstracts.NormState;
import abstracts.State;
import abstracts.Switcher;

public class NotConnectPanel extends JPanel {
	
	public NotConnectPanel(Switcher switcher, LayoutManager layout) {

		super(layout);
    	add(new JLabel("We have some problem with Telegram!"));
    	
    	JPanel choosePan = new JPanel();
    	choosePan.setLayout(new BorderLayout());
    	
    	JButton next = new JButton("Work offline");
    	next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection defaultSession = new DefaultSession();
				switcher.switchTo(new ProfilePanel(switcher, new DefaultState(), 
						defaultSession));
			}
		});
    	choosePan.add(next, BorderLayout.WEST);
    	
    	JButton exit = new JButton("Exit");
    	exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.exit();
			}
		});
    	choosePan.add(exit, BorderLayout.EAST);
    	
    	add(choosePan);
	}
}
