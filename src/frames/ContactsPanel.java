package frames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.OnCenterLayout;

import org.javagram.TelegramApiBridge;
import org.javagram.response.object.MessagesDialog;
import org.javagram.response.object.User;
import org.javagram.response.object.UserContact;

import abstracts.DialogInfo;
import abstracts.UserInfo;

//������ ������� ������ ���������
public class ContactsPanel extends JPanel {
	private ProfilePanel container;
	private TelegramApiBridge bridge;
	
	public ContactsPanel(TelegramApiBridge connection, ProfilePanel cont){
		super();
		
		container = cont;
		bridge = connection;
		
		//making the contacts-list
		JPanel contactList = new JPanel();
		contactList.setLayout(new BoxLayout(contactList, BoxLayout.Y_AXIS));
		int friendsCount = 0;
		
		
		try {
			for (MessagesDialog dialog : bridge.messagesGetDialogs()) {
				friendsCount++;
				User user = dialog.getPeerUser();
				JPanel dialogButton = getDialogBrief(new DialogInfo(dialog));
				contactList.add(dialogButton);
			}
		} catch (IOException e2) {
				e2.printStackTrace();
		}
				
		//show the contacts count in the top of screen
		JLabel friendsNumber = new JLabel("You have " + String.valueOf(friendsCount) + " dialogs.");
		JPanel contactsInfoPanel = new JPanel(new OnCenterLayout());
		contactsInfoPanel.add(friendsNumber);
		add(friendsNumber, BorderLayout.NORTH);
				
		//show the contacts-list
		JScrollPane contacts = new JScrollPane(contactList);
		add(contacts, BorderLayout.CENTER);
				
	}
	
	//��������� �������� �������� ���������� �������
	private JPanel getDialogBrief(DialogInfo dialog){
		//������ ��������
		JPanel dialogBrief = new JPanel();
		dialogBrief.setLayout(new BorderLayout());
		
		//���������� ������ ��� ������������ � ������� ������� ������
		UserInfo user = dialog.getUser();
		JLabel userName = new JLabel(user.getFirstName() + " " + user.getLastName());
		userName.setFont(new Font("Arial", 10, 10));
		dialogBrief.add(userName, BorderLayout.NORTH);
		
		//��������� ��������� �� ���� (����������� �� ����� - 50 �������)
		String lastMes = dialog.getLastMessage();
		String visText = (lastMes.length() > 50) ? lastMes.substring(0, 47) + "..." : lastMes;
		JLabel lastMessage = new JLabel(visText);
		dialogBrief.add(lastMessage, BorderLayout.CENTER);
		
		//������ ������� �������
		String dialogRefText = "<html>Go to <br>dialog";
		if (dialog.getUnreadCount() > 0)
			dialogRefText += " (����������� + " + dialog.getUnreadCount() + " �����."; 
		JButton goDialog = new JButton(dialogRefText);
		dialogBrief.add(goDialog, BorderLayout.SOUTH);
		

		goDialog.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				container.OpenDialog(dialog);
			}
		});
		
		return dialogBrief;
	}
}
