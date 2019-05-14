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

public class SendCodePanel extends InactiveLoginPanel {
//QUESTS:
	//1: errors handing
	//2: layout management
	//3: sending messages

	public SendCodePanel(TelegaFrame container, TelegramApiBridge bridge) {
		super("Enter check-code", "Ok");
		initAction(container, bridge);
	}
	
	public SendCodePanel(TelegaFrame container, TelegramApiBridge bridge, LayoutManager layout) {
		super("Enter check-code", "Ok", layout);
		initAction(container, bridge);
	}
	
	private void initAction(TelegaFrame container, TelegramApiBridge bridge) {

		sendInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            	AuthAuthorization user;
                try {
                    user = bridge.authSignIn(((JTextField)logField).getText());
                } catch (IOException e1) {
                	error.setText("Code is incorrect. Please, try again.");
                	add(error);
                    return;
                }

                container.switchToUserProfile();
                revalidate();
                repaint();
            }
        });
	}
}
