package frames;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;

import abstracts.Connection;
import abstracts.State;
import abstracts.Switcher;

public class SendCodePanel extends InactiveLoginPanel {
//QUESTS:
	//1: errors handing
	//2: layout management
	//3: sending messages

	public SendCodePanel(Switcher container, State state, Connection bridge) {
		super("Enter check-code", "Ok");
		initAction(container, state, bridge);
	}
	
	public SendCodePanel(Switcher container, State state, Connection bridge, LayoutManager layout) {
		super("Enter check-code", "Ok", layout);
		initAction(container, state, bridge);
	}
	
	private void initAction(Switcher container, State state, Connection bridge) {

		sendInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            	AuthAuthorization user;
                try {
                    user = bridge.logIn(((JTextField)logField).getText());
                } catch (IOException e1) {
                	error.setText("Code is incorrect. Please, try again.");
                	add(error);
                    return;
                }

                container.switchTo(new ProfilePanel(container, state, bridge));
                revalidate();
                repaint();
            }
        });
	}
}
