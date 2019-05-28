package abstracts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.javagram.TelegramApiBridge;
import org.javagram.response.object.MessagesDialog;

import exceptions.WrongPhoneNumberException;

public class Connection {
	//private List<Account> accounts;
	private List<DialogInfo> dialogs;
	TelegramApiBridge bridge;
	
	public Connection(TelegramApiBridge extBridge) {
		bridge = extBridge;
		dialogs = new ArrayList<DialogInfo>();
		List<MessagesDialog> extDialogs;
		try {
			extDialogs = bridge.messagesGetDialogs();
		} catch (IOException e) {
			extDialogs = new ArrayList<MessagesDialog>();
			//добавить возможность чтения из кэша
		}
		
		for (MessagesDialog dialog : extDialogs) {
			dialogs.add(new DialogInfo(dialog));
		}
	}
	
	public Connection(List<DialogInfo> extDialogs) {
		dialogs = extDialogs;
	}
	
	public void sendPhone(String phone) throws WrongPhoneNumberException {
		if (bridge != null) {
				try {
					bridge.authSendCode(phone.replaceAll("[^0-9]+", ""));
				} catch (IOException e) {
					throw new WrongPhoneNumberException(phone);
				}
		}
		else {
			//проверка номера на существование в кэше
		}
	}
	
	public void logOut() {
		//выход из аккаунта
	}
}
