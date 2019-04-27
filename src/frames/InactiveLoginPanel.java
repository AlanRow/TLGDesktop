package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javafx.scene.input.KeyCode;

import javax.swing.*;

//This is standart form for logging somewhere.
public class InactiveLoginPanel extends JPanel {
	protected JLabel label;
	protected JLabel error;//описание ошибок ввода.
	protected Component logField;
	protected JButton sendInfo;
	protected JLabel errorText;
	
	protected InactiveLoginPanel(String labelText, String buttonText){
		initFrame(labelText, buttonText);
		invalidate();
	}
	

	protected InactiveLoginPanel(String labelText, String buttonText, LayoutManager layout){
		super(layout);
		initFrame(labelText, buttonText);
		invalidate();
	}
	
	private void initFrame(String labelText, String buttonText) {
		label = new JLabel(labelText);
		error = new JLabel();
    	error.setForeground(Color.RED);
		logField = new JTextField();
		logField.setPreferredSize(new Dimension(200, 20));
		//Default it do nothing when you're clicking at button,
		sendInfo = new JButton(buttonText);
		
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setAlignmentY(BOTTOM_ALIGNMENT);
		
		//label.setAlignmentX(CENTER_ALIGNMENT);
		
		logField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 10)//EnterCode
					sendInfo.doClick();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		
		add(label);
		add(logField);
		add(sendInfo);
	}
}
