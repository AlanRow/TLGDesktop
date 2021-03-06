package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.javagram.TelegramApiBridge;
import org.javagram.response.MessagesMessages;
import org.javagram.response.object.MessagesDialog;
import org.javagram.response.object.MessagesMessage;

import abstracts.Connection;
import abstracts.DialogInfo;
import abstracts.Switcher;
import exceptions.NotConnectException;

//������ ������
public class MessagePanel extends JPanel {

	public MessagePanel(Switcher switcher, Connection connect, DialogInfo dialog){
		
		super();

		setLayout(new BorderLayout());
		JLabel userName = new JLabel(dialog.getPeerUser().getFullName());
		add(userName, BorderLayout.NORTH);
		
		JTextField message = new JTextField();
		message.setText(dialog.getLastMessage());
		message.setEnabled(false);
		add(message, BorderLayout.CENTER);
		
		JPanel sending = new JPanel();
		sending.setLayout(new BorderLayout());
		
		JTextField sendMessage = new JTextField();
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					connect.sendMessage(dialog.getInputPeer(), sendMessage.getText(), (long)(Math.random() * Long.MAX_VALUE));
				} catch ( NotConnectException e1) {
					sendMessage.setBackground(Color.RED);
				}
			}
			
		});
		
		sending.add(sendMessage, BorderLayout.CENTER);
		sending.add(sendButton, BorderLayout.EAST);
		add(sending, BorderLayout.SOUTH);
		
		invalidate();
	}
}
