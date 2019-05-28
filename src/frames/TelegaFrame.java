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
import abstracts.Session;
import abstracts.State;
import abstracts.Switcher;
import abstracts.DialogInfo;
import abstracts.NormState;
import exceptions.NotConnectException;
import exceptions.SessionInterruptedException;
import main.OnCenterLayout;

public class TelegaFrame extends Frame implements Switcher {

    private Connection bridge;
    private AccountInfo info;
    private JPanel mainPanel;
    private boolean isConnect;

    public TelegaFrame() throws IOException {

    	isConnect = true;
        try {
        	//bridge = new TelegramApiBridge("82.102.22.48:443", 460089, "43e09d7d74956467ea21152facf3c328");
            bridge = new Session("149.154.167.40:443", 638443, "6999a27be540e72b2f8ddc8e04810c50");
        } catch (NotConnectException e) {
        	switchTo(new NotConnectPanel(this, new OnCenterLayout()));
        	return;
        } finally {
            setBounds(0, 0, 800, 640);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                	try {
                		exit();
						bridge.logOut();
					} catch (NotConnectException e1) {
						e1.printStackTrace();
					}
                }
            });

        }
        
        State state = new NormState(bridge);
        
        setLayout(new BorderLayout());
        java.util.List<String> phones = new ArrayList<String>();
        phones.add("8 (950) 657 05-33");
        switchTo(new SendPhonePanel(this, state, bridge, phones, new OnCenterLayout()));
    }
    
    @Override
    public void exit() {
		TelegaFrame.this.setVisible(false);
		TelegaFrame.this.dispose();
    }
    
    public void setDefaultOptions() {
    	isConnect = false;
    	info = AccountInfo.getDefault();
    }

	@Override
	public void switchTo(JPanel switching) {
    	try {
    		remove(mainPanel);
    	} catch (NullPointerException ex) {}
    	mainPanel = switching;
    	add(mainPanel);
    	
    	invalidate();
    	revalidate();
    	repaint();
	}
}