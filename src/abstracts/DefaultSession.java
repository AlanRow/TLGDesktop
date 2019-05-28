package abstracts;

import java.io.IOException;
import java.util.List;

import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.InputPeer;
import org.javagram.response.object.MessagesDialog;
import org.javagram.response.object.User;

import exceptions.NotConnectException;
import exceptions.WrongPhoneNumberException;

public class DefaultSession implements Connection {

	@Override
	public AuthSentCode sendCode(String replaceAll) throws WrongPhoneNumberException {
		return null;
	}

	@Override
	public AuthAuthorization logIn(String text) throws IOException {
		return null;
	}

	@Override
	public void logOut() throws NotConnectException {
	}

	@Override
	public void sendMessage(InputPeer inputPeer, String text, long l) throws NotConnectException {
		// TODO Auto-generated method stub

	}

	@Override
	public User getCurrentUser() {
		return null;
	}

	@Override
	public List<MessagesDialog> getDialogs() {
		// TODO Auto-generated method stub
		return null;
	}

}
