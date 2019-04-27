import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthSentCode;


public class SendPhonePanel extends InactiveLoginPanel {

	public SendPhonePanel(TelegaFrame container, TelegramApiBridge bridge) {
		super("Enter your phone number", "Send code");
		
		sendInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    AuthSentCode confirmationCode = bridge.authSendCode(logField.getText().replaceAll("[^0-9]+", ""));
                } catch (IOException e1) {
                	JLabel error = new JLabel("Phone unfound. Please, try again.");
                	error.setForeground(Color.RED);
                	add(error);
                    return;
                }

                container.Switch(new SendCodePanel(container, bridge));
                container.invalidate();
            }
        });
	}
	
}
