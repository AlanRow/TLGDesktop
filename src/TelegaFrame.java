
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.*;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthSentCode;

public class TelegaFrame extends Frame {

    TelegramApiBridge bridge;
    JPanel mainPanel;

    public TelegaFrame() {

        try {
            bridge = new TelegramApiBridge("149.154.167.50:443", 460089, "43e09d7d74956467ea21152facf3c328");
        } catch (IOException e) {
            e.printStackTrace();
        }

        setBounds(0, 0, 800, 640);
        setBackground(Color.blue);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                TelegaFrame.this.setVisible(false);
                TelegaFrame.this.dispose();
            }
        });

        /*phoneInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                phoneInput.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (phoneInput.getText().equals("")) {
                    phoneInput.setText(defaultNumber);
                }
            }

        });*/

        mainPanel = new SendPhonePanel(this, bridge);
        add(mainPanel);
        

        /*logInBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                numberPh = phoneInput.getText().replaceAll("[^0-9]+", "");
                try {
                    AuthSentCode confirmationCode = bridge.authSendCode(numberPh);
                } catch (IOException e1) {
                    //to do проверка корректности телефона, повторная отправка
                    return;
                }

                phoneInput.setVisible(false);
                logInBut.setVisible(false);
                phoneLabel.setVisible(false);

                isAuthorizing = true;

                add(codeInput);
                add(sendCode);
                add(codeLabel);

                invalidate();
            }
		});*/
    }
    
    public void Switch(JPanel switchingPanel) {
    	remove(mainPanel);
    	mainPanel = switchingPanel;
    	add(mainPanel);
    	
    	invalidate();
    }
}