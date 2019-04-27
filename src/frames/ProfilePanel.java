package frames;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.*;

import main.OnCenterLayout;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.MessagesAffectedHistory;
import org.javagram.response.MessagesMessages;
import org.javagram.response.object.InputPeer;
import org.javagram.response.object.MessagesDialog;
import org.javagram.response.object.User;
import org.javagram.response.object.UserContact;

import abstracts.DialogInfo;

public class ProfilePanel extends JPanel {
	TelegramApiBridge bridge;
	private String profileName;
	private String phone;
	private List<UserContact> contacts;
	private Component content;
	//Bitmap photo;
	
	public ProfilePanel(TelegramApiBridge connection, AuthAuthorization user, TelegaFrame container) {

		bridge = connection;
		setLayout(new BorderLayout());
		User userInfo = user.getUser();
		try {
			contacts = bridge.contactsGetContacts();
		} catch (IOException e3) {
			contacts = null;
		}
		
		//исправить на ввод с постановкой (как на лекции было)
		try {
			profileName = new String((userInfo.getFirstName() + " " + userInfo.getLastName()).getBytes(), "Cp1251");
		} catch (UnsupportedEncodingException e) {
			profileName = userInfo.getFirstName() + " " + userInfo.getLastName();
		}
		
		
		//making your info-labels
		JPanel info = new JPanel();
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		info.setAlignmentX(LEFT_ALIGNMENT);
		
		JLabel name = new JLabel(profileName);
		name.setFont(new Font("Arial", 1, 20));
		info.add(name);
		
		//making the user-information panel
		phone = userInfo.getPhone();
		JLabel phoneLabel = new JLabel(phone);
		name.setFont(new Font("Arial", 1, 20));
		info.add(phoneLabel);
		add(info, BorderLayout.WEST);
		
		//making the log_out button in the bottom of scren
		JButton exit = new JButton("Log out");
		add(exit, BorderLayout.SOUTH);
		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                	try {
						bridge.authLogOut();
						container.setVisible(false);
						container.dispose();
						System.exit(0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            }
        });
		
		
		JPanel dialogs = new ContactsPanel(bridge, this);
		content = dialogs;
		add(content, BorderLayout.CENTER);
		
        container.invalidate();
	}
	
	public void OpenDialog(DialogInfo dialog){
		remove(content);
		
		content = new MessagePanel(bridge, dialog);
		add(content, BorderLayout.CENTER);
	}
	
	public ProfilePanel(String userName, String userPhone, String[] userContacts) {

		setLayout(new BorderLayout());
		
		//исправить на ввод с постановкой (как на лекции было)
		profileName = userName;
		
		JPanel info = new JPanel();
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		info.setAlignmentX(LEFT_ALIGNMENT);
		
		JLabel name = new JLabel(profileName);
		name.setFont(new Font("Arial", 1, 20));
		info.add(name);
		
		phone = userPhone;
		JLabel phoneLabel = new JLabel(phone);
		name.setFont(new Font("Arial", 1, 20));
		info.add(phoneLabel);
		
		add(info, BorderLayout.WEST);
		
		JButton exit = new JButton("Log out");
		add(exit, BorderLayout.SOUTH);
		
		JPanel contactList = new JPanel();
		
		for (String friend : userContacts) {
			contactList.add(new JButton(friend));
		}
		
		JScrollPane contacts = new JScrollPane(contactList);
		
		add(contacts, BorderLayout.CENTER);

        invalidate();
	}
}
