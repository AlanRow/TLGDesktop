package frames;

import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.*;

import org.javagram.TelegramApiBridge;
//import org.javagram.response.AuthSentCode;

import main.OnCenterLayout;

public class TelegaFrame extends Frame {

    TelegramApiBridge bridge;
    JPanel mainPanel;

    public TelegaFrame() {

        try {
            bridge = new TelegramApiBridge("149.154.167.50:443", 460089, "43e09d7d74956467ea21152facf3c328");
        } catch (IOException e) {
        	add(new JLabel("We have some problem with Telegram!"));
            e.printStackTrace();
        	return;
        } finally {
            setBounds(0, 0, 800, 640);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
					TelegaFrame.this.setVisible(false);
					TelegaFrame.this.dispose();
                	try {
						bridge.authLogOut();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                }
            });

        }
        setBackground(Color.blue);
        setLayout(new BorderLayout());
        mainPanel = new SendPhonePanel(this, bridge, new OnCenterLayout());
        //mainPanel = new ProfilePanel("Alan", "88888888888", new String[] {"Inga", "Olga", "Andrew", "Dora"});
        add(mainPanel);
    }
    
    public void Switch(JPanel switchingPanel) {
    	remove(mainPanel);
    	mainPanel = switchingPanel;
    	add(mainPanel);
    	
    	invalidate();
    	revalidate();
    	repaint();
    }
}