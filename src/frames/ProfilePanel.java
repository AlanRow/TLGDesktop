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

import abstracts.Connection;
import abstracts.DialogInfo;
import abstracts.Dialogs;
import abstracts.State;
import abstracts.Switcher;
import abstracts.UserInfo;
import exceptions.NotConnectException;

public class ProfilePanel extends JPanel {
	private String profileName;
	private String phone;
	private List<UserContact> contacts;
	private Component content;
	private Switcher cont;
	//Bitmap photo;
	
	public ProfilePanel(Switcher switcher, State state, Connection connect) {

		cont = switcher;
		setLayout(new BorderLayout());
		//try {
			//contacts = bridge.contactsGetContacts();
		//} catch (IOException e3) {
			//contacts = null;
		//}
		
		//исправить на ввод с постановкой (как на лекции было)
		UserInfo user = state.getCurrentUser();
		try {
			profileName = new String((user.getFirstName() + " " + user.getLastName()).getBytes(), "Cp1251");
		} catch (UnsupportedEncodingException e) {
			profileName = user.getFirstName() + " " + user.getLastName();
		}
		
		
		//making your info-labels
		JPanel info = new JPanel();
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		info.setAlignmentX(LEFT_ALIGNMENT);
		
		JLabel name = new JLabel(profileName);
		name.setFont(new Font("Arial", 1, 20));
		info.add(name);
		
		//making the user-information panel
		phone = user.getPhone();
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
            			connect.logOut();
            		} catch (NotConnectException ex) {}
            		switcher.exit();
					System.exit(0);
            }
        });
		
		
		JPanel dialogsPanel = new ContactsPanel(switcher, state, connect);
		content = dialogsPanel;
		add(content, BorderLayout.CENTER);
		
        invalidate();
	}
	
	//where does it use?
	//public void OpenDialog(){
		//cont.switchTo(new ContactsPanel(switcher, state, connect));
        //revalidate();
        //repaint();
	//}
}
