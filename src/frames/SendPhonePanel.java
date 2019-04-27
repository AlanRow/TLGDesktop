package frames;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import main.OnCenterLayout;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthSentCode;

import files.ConfSaver;

//панель отправки телефона
public class SendPhonePanel extends InactiveLoginPanel {

	//недавно введенные номера
	ConfSaver recentlyNums;
	JComboBox<String> phonesScroll;
	
	public SendPhonePanel(TelegaFrame container, TelegramApiBridge bridge) {
		super("Enter your phone number", "Send code");
		initAction(container, bridge);
	}
	
	public SendPhonePanel(TelegaFrame container, TelegramApiBridge bridge, LayoutManager layout) {
		super("Enter your phone number", "Send code", layout);
		initAction(container, bridge);
	}
	
	private void initAction(TelegaFrame container, TelegramApiBridge bridge) {
		

		recentlyNums = new ConfSaver();
		
		List<String> phonesList = recentlyNums.getRecentlyPhones();
		String[] phones = new String[phonesList.size()];
		
		for (int i = 0; i < phones.length; i++)
			phones[i] = phonesList.get(i);
		
		//String[] phones = (String[]) recentlyNums.getRecentlyPhones().toArray();
		phonesScroll = new JComboBox<String>(phones);
		phonesScroll.setEditable(true);
		Component[] comps = getComponents();
		
		for (int i = 0; i < comps.length; i++)
			if (comps[i] == logField)
			{
				remove(i);
				add(phonesScroll, i);
			}
		
		sendInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    AuthSentCode confirmationCode = bridge.authSendCode(phonesScroll.getSelectedItem().toString().replaceAll("[^0-9]+", ""));
                } catch (IOException e1) {
                	error.setText("Phone unfound. Please, try again.");
                	add(error);
                	container.invalidate();
                	container.revalidate();
                	container.repaint();
                    return;
                }
                //catch (Exception ex) {
                	//System.out.println(ex.getClass().toString() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                //}
                recentlyNums.addPhone(phonesScroll.getModel().getSelectedItem().toString());
                recentlyNums.save();
                container.Switch(new SendCodePanel(container, bridge, new OnCenterLayout()));
                revalidate();
                repaint();
            }
        });
	}
	
}
