
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

//This is standart form for logging somewhere.
public class InactiveLoginPanel extends JPanel {
	protected JLabel label;
	protected JTextField logField;
	protected JButton sendInfo;
	
	protected InactiveLoginPanel(String labelText, String buttonText){
		label = new JLabel(labelText);
		logField = new JTextField();
		//Default it do nothing when you're clicking at button,
		sendInfo = new JButton(buttonText);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setAlignmentY(CENTER_ALIGNMENT);
		
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		
		logField.setMaximumSize(new Dimension(200, 20));
		logField.setAlignmentX(CENTER_ALIGNMENT);
		add(logField);
		
		sendInfo.setAlignmentX(CENTER_ALIGNMENT);
		add(sendInfo);
		
		invalidate();
	}
}
