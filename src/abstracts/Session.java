package abstracts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.InputPeer;
import org.javagram.response.object.MessagesDialog;
import org.javagram.response.object.User;

import exceptions.NotConnectException;
import exceptions.WrongPhoneNumberException;

public class Session implements Connection {
	//private List<Account> accounts;
	private TelegramApiBridge bridge;
	private AuthAuthorization auth;
	
	
	public Session(String host, int id, String hash) throws NotConnectException {
		try {
			bridge = new TelegramApiBridge(host, id, hash);
		} catch (IOException ex) {
			throw new NotConnectException();
		}
	}


	
	public void logOut() throws NotConnectException{
		
		try {
		bridge.authLogOut();
		} catch (IOException ex) {
			throw new NotConnectException();
		}
	}

	@Override
	public AuthSentCode sendCode(String phone) throws WrongPhoneNumberException {
		if (bridge != null) {
			try {
				return bridge.authSendCode(phone.replaceAll("[^0-9]+", ""));
			} catch (IOException e) {
				throw new WrongPhoneNumberException(phone);
			}
		}
		return null;
	}

	@Override
	public AuthAuthorization logIn(String code) throws IOException {
		auth = bridge.authSignIn(code);
		return auth;
	}

	@Override
	public void sendMessage(InputPeer inputPeer, String message, long id) throws NotConnectException {
			try {
			bridge.messagesSendMessage(inputPeer, message, id);
			} catch (IOException ex) {
				throw new NotConnectException();
			}
	}

	@Override
	public User getCurrentUser() {
		if (auth == null)
			return null;
		return auth.getUser();
	}

	@Override
	public List<MessagesDialog> getDialogs() {
		List<MessagesDialog> extDialogs;
		try {
			extDialogs = bridge.messagesGetDialogs();
		} catch (IOException e) {
			extDialogs = new ArrayList<MessagesDialog>();
			//добавить возможность чтения из кэша
		}
		
		return extDialogs;
	}
}
