import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;

public class SendCodePanel extends InactiveLoginPanel {


	public SendCodePanel(TelegaFrame container, TelegramApiBridge bridge) {
		super("Enter check-code", "Ok");
		
		sendInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            	AuthAuthorization user;
                try {
                    user = bridge.authSignIn(logField.getText());
                } catch (IOException e1) {
                	JLabel error = new JLabel("Code is incorrect. Please, try again.");
                	error.setForeground(Color.RED);
                	add(error);
                    return;
                }
                
                container.Switch(new ProfilePanel(bridge, user, container));
                container.invalidate();
            }
        });
	}
}
