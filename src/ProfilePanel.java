import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.*;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.object.User;
import org.javagram.response.object.UserContact;

public class ProfilePanel extends JPanel {
	String profileName;
	String phone;
	//Bitmap photo;
	
	public ProfilePanel(TelegramApiBridge bridge, AuthAuthorization user, TelegaFrame container) {

		setLayout(new BorderLayout());
		User userInfo = user.getUser();
		
		//исправить на ввод с постановкой (как на лекции было)
		try {
			profileName = new String((userInfo.getFirstName() + " " + userInfo.getLastName()).getBytes(), "Cp1251");
		} catch (UnsupportedEncodingException e) {
			profileName = userInfo.getFirstName() + " " + userInfo.getLastName();
		}
		
		JPanel info = new JPanel();
		info.setLayout(new BorderLayout());
		
		JLabel name = new JLabel(profileName);
		name.setFont(new Font("Arial", 1, 20));
		info.add(name, BorderLayout.PAGE_START);
		
		phone = userInfo.getPhone();
		JLabel phoneLabel = new JLabel(phone);
		name.setFont(new Font("Arial", 1, 8));
		info.add(phoneLabel, BorderLayout.PAGE_END);
		
		add(info, BorderLayout.PAGE_START);
		
		JButton exit = new JButton("Log out");
		add(exit, BorderLayout.PAGE_END);
		
		JScrollPane contacts = new JScrollPane();
		
		try {
			for (UserContact friend : bridge.contactsGetContacts()) {
				contacts.add(new JLabel(friend.getFirstName() + " " + friend.getLastName()));
				System.out.println(new JLabel(friend.getFirstName() + " " + friend.getLastName()));
			}
			
			add(contacts, BorderLayout.CENTER);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                	try {
						bridge.authLogOut();
						container.setVisible(false);
						container.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            }
        });

        container.invalidate();
	}
}
