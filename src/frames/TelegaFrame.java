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
import java.util.ArrayList;

import javax.swing.*;

import org.javagram.TelegramApiBridge;
//import org.javagram.response.AuthSentCode;

import abstracts.AccountInfo;
import abstracts.Connection;
import abstracts.DialogInfo;
import exceptions.SessionInterruptedException;
import main.OnCenterLayout;

public class TelegaFrame extends Frame {

    private TelegramApiBridge bridge;
    private AccountInfo info;
    private JPanel mainPanel;
    private boolean isConnect;

    public TelegaFrame(/*TelegramApiBridge bridge*/) {

    	isConnect = true;
        try {
            //bridge = new TelegramApiBridge("149.154.167.50:443", 460089, "43e09d7d74956467ea21152facf3c328");
        	throw new IOException();
        } catch (IOException e) {
        	System.out.println("It's done!");
        	switchToNotConnectPanel();
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
        
        //setBackground(Color.blue);
        /*setLayout(new BorderLayout());
        java.util.List<String> phones = new ArrayList<String>();
        phones.add("8 (950) 657 05-33");
        switchToLogInPanel(phones);*/
        //mainPanel = new ProfilePanel("Alan", "88888888888", new String[] {"Inga", "Olga", "Andrew", "Dora"});
        //add(mainPanel);
    }
    
    public void setDefaultOptions() {
    	isConnect = false;
    	info = AccountInfo.getDefault();
    }
    
    public void switchToLogInPanel(java.util.List<String> phones) {
    	switchMainPanel(new SendPhonePanel(this, bridge, phones, new OnCenterLayout()));
    }
    
    public void switchToAuthenticatePanel() {
    	switchMainPanel((new SendCodePanel(this, bridge, new OnCenterLayout())));
    }
    
    public void switchToContactsPanel() {
		try {
			switchMainPanel(new ContactsPanel(this, info.getDialogs()));
		} catch (SessionInterruptedException e) {
			switchMainPanel(new ContactsPanel(this, info.getLastDialogs()));
		}
    }
    
    public void switchToUserProfile() {
    	try {
			switchMainPanel(new ProfilePanel(this, info.getUser(), info.getDialogs()));
		} catch (SessionInterruptedException e) {
			switchMainPanel(new ProfilePanel(this, info.getUser(),info.getLastDialogs()));
		}
    }
    public void switchToDialog(DialogInfo dialog) {
    	switchMainPanel(new MessagePanel(this, bridge, dialog));
    }
    
    public void switchToNotConnectPanel(){
    	switchMainPanel(new NotConnectPanel(this, new OnCenterLayout()));
    }
    
    public void logOut() {
    	try {
			bridge.authLogOut();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void switchMainPanel(JPanel switchingPanel) {
    	try {
    		remove(mainPanel);
    	} catch (NullPointerException ex) {}
    	mainPanel = switchingPanel;
    	add(mainPanel);
    	
    	invalidate();
    	revalidate();
    	repaint();
    }
}